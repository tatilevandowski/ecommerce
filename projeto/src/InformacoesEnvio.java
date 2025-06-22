public class InformacoesEnvio {
    private Integer numeroEnvio = 0;
    private String tipoEnvio;
    private Double custoEnvio = 100.00;
    private Integer codIbge;



    public InformacoesEnvio() {
    }

    // Getters e Setters
    public Integer getNumeroEnvio() {
        return numeroEnvio;
    }

    public void setNumeroEnvio(Integer numeroEnvio) {
        this.numeroEnvio = numeroEnvio;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public Double getCustoEnvio() {
        return custoEnvio;
    }

    public void setCustoEnvio(Double custoEnvio) {
        this.custoEnvio = custoEnvio;
    }

    public Integer getCodIbge() {
        return codIbge;
    }

    public void setCodIbge(Integer codIbge) {
        this.codIbge = codIbge;
    }

    public void exibirInformacoes() {
        numeroEnvio = numeroEnvio++;
        System.out.println("--- INFORMAÇÕES DE ENVIO ---");
        System.out.println("Número de Envio: " + (numeroEnvio != null ? numeroEnvio : "Não disponível"));
        System.out.println("Tipo de Envio: " + (tipoEnvio != null ? tipoEnvio : "Não disponível"));
        System.out.printf("Custo de Envio: R$ %.2f%n", (custoEnvio != null ? custoEnvio : 0.0));
        System.out.println("Código IBGE: " + (codIbge != null ? codIbge : "Não disponível"));
        System.out.println("-----------------------------");
    }

    public Double calcularCustoEnvio() {
        return custoEnvio;
    }
}
