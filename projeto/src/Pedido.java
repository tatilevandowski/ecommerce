import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
    private String codPedido;
    private Cliente cliente;
    private List<DetalhesPedido> itensDoPedido;
    private Date dataEmissao;
    private Date dataEnvio;
    private String status; // Status do pedido (ex: "PENDENTE", "APROVADO", "ENVIADO", "ENTREGUE", "CANCELADO")
    private Double valorTotal;
    private String ufEnvio;
    private InformacoesEnvio informacoesEnvio;


    public Pedido(String codPedido, Cliente cliente) {
        if (codPedido == null) {
            throw new IllegalArgumentException("Código do pedido deve ser positivo.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("Pedido deve estar associado a um cliente.");
        }
        this.codPedido = codPedido;
        this.cliente = cliente;
        this.itensDoPedido = new ArrayList<>();
        this.dataEmissao = new Date();
        this.status = "PENDENTE";
        this.valorTotal = 0.0;
        this.ufEnvio = cliente.getUfCliente();
    }

    // Getters e Setters
    public String getCodPedido() { return codPedido; }
    public Cliente getCliente() { return cliente; }
    public List<DetalhesPedido> getItensDoPedido() { return itensDoPedido; }
    public Date getDataEmissao() { return dataEmissao; }
    public Date getDataEnvio() { return dataEnvio; }
    public String getStatus() { return status; }
    public Double getValorTotal() { return valorTotal; }
    public String getUfEnvio() { return ufEnvio; }

    public void setDataEnvio(Date dataEnvio) { this.dataEnvio = dataEnvio; }
    public void setStatus(String status) { this.status = status; }
    public void setUfEnvio(String ufEnvio) { this.ufEnvio = ufEnvio; }

    public void adicionarItem(DetalhesPedido detalhe) {
        if (detalhe != null) {
            itensDoPedido.add(detalhe);
            this.valorTotal += detalhe.getPrecoUnitario();
            System.out.println("Item '" + detalhe.getCodigoProduto() + "' adicionado ao pedido " + codPedido);
        } else {
            System.out.println("Não é possível adicionar item nulo ao pedido.");
        }
    }

    public boolean finalizarPedido(CarrinhoCompras carrinho) {
        if (carrinho == null || carrinho.getItens().isEmpty()) {
            System.out.println("Erro: Não é possível finalizar um pedido com um carrinho vazio ou nulo.");
            return false;
        }

        for (CarrinhoCompras.ItemCarrinho itemCarrinho : carrinho.getItens()) {
            if (itemCarrinho != null) {
                DetalhesPedido detalhe = new DetalhesPedido(itemCarrinho.codProduto, itemCarrinho.quantidade);
                this.itensDoPedido.add(detalhe);
                this.valorTotal += detalhe.getPrecoUnitario();
            }
        }

        this.setStatus("APROVADO");
        this.dataEmissao = new Date();

        System.out.println("Pedido " + this.codPedido + " do cliente " + this.cliente.getNomeCliente() + " finalizado com sucesso!");
        System.out.printf("Valor Total: R$ %.2f%n", this.valorTotal);
        System.out.println("Status: " + this.status);
        System.out.println("Itens no pedido:");
        for (DetalhesPedido item : itensDoPedido) {
            System.out.printf("  - %s (cód: %d) | Qtd: %d | Preço no Pedido: R$ %.2f | Subtotal: R$ %.2f%n",
                    item.getNomeProduto(),
                    item.getCodigoProduto(),
                    item.getQuantidade(),
                    item.getPrecoUnitario(),
                    item.getPrecoLiquido());
        }
        System.out.println("------------------------------------------");

        return true;
    }

    public void exibirResumoPedido() {
        System.out.println("\n--- RESUMO DO PEDIDO " + codPedido + " ---");
        System.out.println("Cliente: " + cliente.getNomeCliente() + " (Cód: " + cliente.getCodCliente() + ")");
        System.out.println("Data de Emissão: " + dataEmissao);
        System.out.println("Status: " + status);
        System.out.printf("Valor Total: R$ %.2f%n", valorTotal);
        System.out.println("UF de Envio: " + ufEnvio);
        if (dataEnvio != null) {
            System.out.println("Data de Envio: " + dataEnvio);
        } else {
            System.out.println("Ainda não enviado.");
        }
        System.out.println("-------------------------------\n");
    }

    public InformacoesEnvio getInformacoesEnvio() {
        if (informacoesEnvio == null) {
            informacoesEnvio = new InformacoesEnvio();
            informacoesEnvio.setTipoEnvio("Padrão");
            informacoesEnvio.setCustoEnvio(0.0);
            informacoesEnvio.setCodIbge(0);
        }
        return informacoesEnvio;
    }

    public void setInformacoesEnvio(InformacoesEnvio informacoesEnvio) {
        this.informacoesEnvio = informacoesEnvio;
    }
}
