public class Produtos {
    String nomeProduto;
    Integer codigoProduto;
    Double precoProduto;
    String tamanhoProduto;
    String corProduto;

    public  Produtos(int i, String camisetaBásica, double v, String m, String branca) {
        this.codigoProduto = i;
        this.nomeProduto = camisetaBásica;
        this.precoProduto = v;
        this.tamanhoProduto = m;
        this.corProduto = branca;
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

    public void exibirDetalhes() {

    }
}

