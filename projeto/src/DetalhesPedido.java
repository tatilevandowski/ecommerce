import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DetalhesPedido {
    private static List<Produtos> listaProdutos;

    Integer codPedido;
    Integer codProduto;
    String nomeProduto;
    String cor;
    String tamanho;
    Double preco;
    Double desconto = 0.0;
    Double valorLiquido = 0.0;
    Integer quantidade = 0;

    public static void setListaProdutos(List<Produtos> produtos) {
        listaProdutos = produtos;
    }

    public DetalhesPedido(Integer codProduto, Integer quantidade) {
        Objects.requireNonNull(codProduto, "Código do produto não pode ser nulo");
        Objects.requireNonNull(quantidade, "Quantidade não pode ser nula");

        this.codProduto = codProduto;
        this.quantidade = quantidade;

        // Busca o produto na lista
        Produtos produto = buscarProdutoPorCodigo(codProduto);

        if (produto != null) {
            this.nomeProduto = produto.getNomeProduto();
            this.cor = produto.getCor();
            this.tamanho = produto.getTamanho();
            this.preco = produto.getPrecoProduto();
            this.desconto = calcularDesconto();
            this.valorLiquido = calcularValorLiquido();
        } else {
            throw new IllegalArgumentException("Produto com código " + codProduto + " não encontrado");
        }
    }

    // Métodos restantes...

    private Produtos buscarProdutoPorCodigo(Integer codProduto) {
        if (listaProdutos == null) {
            throw new IllegalStateException("Lista de produtos não foi configurada");
        }

        // Implementação segura usando Optional
        Optional<Produtos> produto = listaProdutos.stream()
                .filter(p -> p.getCodigoProduto().equals(codProduto))
                .findFirst();

        return produto.orElse(null);
    }

    public double calcularPrecoBruto() {
        return preco;
    }

    public double calcularDesconto() {
        return desconto;
    }

    public double calcularValorLiquido() {
        return valorLiquido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getCor() {
        return cor;
    }
    public String getTamanho() {
        return tamanho;
    }

    public Integer getCodigoProduto() {
        return codProduto;
    }

    public Double getPrecoUnitario() {
        return preco;
    }

    public Object getPrecoLiquido() {
        return valorLiquido;
    }

}
