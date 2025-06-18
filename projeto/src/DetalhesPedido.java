public class DetalhesPedido {
    Integer codPedido;
    Integer codProduto;
    String nomeProduto;
    String cor;
    String tamanho;
    Double preco;
    Double desconto;
    Double valorLiquido;
    Integer quantidade;

    public DetalhesPedido(Integer codPedido, Integer quantidade) {

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
