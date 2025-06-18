import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CarrinhoCompras {
    Integer numeroCarrinho;
    Integer codProduto;
    Integer quantidade;
    Date dataEmissao;

    public CarrinhoCompras(Integer numeroCarrinho, Integer codProduto, Integer quantidade, Date dataEmissao) {
        this.numeroCarrinho = numeroCarrinho;
        this.codProduto = codProduto;
        this.quantidade = quantidade;
        this.dataEmissao = dataEmissao;
    }

    public Integer getNumeroCarrinho() {
        return numeroCarrinho;
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        if (quantidade != null && quantidade >= 0) { // Permitir 0 para remover ou reduzir
            this.quantidade = quantidade;
        } else {
            System.out.println("Atenção: Quantidade não pode ser negativa.");
        }
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        if (dataEmissao != null) {
            this.dataEmissao = dataEmissao;
        } else {
            System.out.println("Atenção: Data de emissão não pode ser nula.");
        }
    }


    public Integer atualizarQuantidade(Integer quantidadeAdicional) {
        if (quantidadeAdicional == null || quantidadeAdicional <= 0) {
            System.out.println("Erro: Quantidade adicional deve ser positiva.");
            return -1; // Indica erro
        }

        this.quantidade += quantidadeAdicional;
        System.out.println("Adicionado " + quantidadeAdicional + " unidades ao produto " + this.codProduto + ". Nova quantidade: " + this.quantidade);
        return this.quantidade;

    }

    public void ItemCarrinho(Integer produto, Integer quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo para ItemCarrinho.");
        }
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva para ItemCarrinho.");
        }
        this.codProduto = produto;
        this.quantidade = quantidade;
    }
    public List<CarrinhoCompras> getItensDoPedido() {
        List<CarrinhoCompras> itensDoPedido = Collections.emptyList();
        return itensDoPedido; }

}
