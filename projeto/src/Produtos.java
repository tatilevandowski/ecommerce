public class Produtos {
    String nomeProduto;
    Integer codigoProduto;
    Double precoProduto;
    String tamanhoProduto;
    String corProduto;

    public Integer Produto(Integer id, String nome, Double preco, String tamanho, String cor) {
        this.codigoProduto = id;
        this.nomeProduto = nome;
        this.precoProduto = preco;
        this.tamanhoProduto = tamanho;
        this.corProduto = cor;
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public String getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(String tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

    public String getCorProduto() {
        return corProduto;
    }

    public void setCorProduto(String corProduto) {
        this.corProduto = corProduto;
    }
}

