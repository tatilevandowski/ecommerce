import java.util.Scanner;

public class Cliente {
    Integer codCliente;
    String nomeCliente;
    String enderecoCliente;
    String ufCliente;
    String emailCliente;
    String telefoneCliente;
    String senha;

    public Cliente(String nomeCliente, String enderecoCliente, String emailCliente, String telefoneCliente, String ufCliente) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.emailCliente = emailCliente;
        this.telefoneCliente = telefoneCliente;
        this.ufCliente = ufCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(String telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public String getUFCliente() {
        return ufCliente;
    }
    public void setUFCliente(String ufCliente) {
        this.ufCliente = ufCliente;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public void cadastrarCliente() {
        if (this.nomeCliente == null || this.nomeCliente.isEmpty()) {
            System.out.println("Erro: Nome do cliente é obrigatório para cadastro.");
            return;
        }
        if (this.emailCliente == null || !this.emailCliente.contains("@")) {
            System.out.println("Erro: Email inválido para cadastro.");
            return;
        }

        System.out.println("Cliente " + this.nomeCliente + " (Email: " + this.emailCliente + ") pronto para ser cadastrado.");
        System.out.println("Detalhes: Endereço: " + this.enderecoCliente + ", UF: " + this.ufCliente + ", Telefone: " + this.telefoneCliente);


        System.out.println("Cliente cadastrado com sucesso! (Lógica de DB externa)");
    }

    public void alterarCliente() {
        if (this.emailCliente == null || !this.emailCliente.contains("@")) {
            System.out.println("Erro: Email inválido ao tentar alterar cliente.");
            return;
        }

        System.out.println("Dados do cliente " + this.nomeCliente + " (Email: " + this.emailCliente + ") prontos para serem alterados.");
        System.out.println("Novos Detalhes: Endereço: " + this.enderecoCliente + ", UF: " + this.ufCliente + ", Telefone: " + this.telefoneCliente);

        System.out.println("Cliente alterado com sucesso! (Lógica de DB externa)");
    }

    public boolean logarCliente() {
        Scanner scanner = new Scanner(System.in);
        String emailDigitado;
        String senhaDigitada;

        System.out.print("Digite seu e-mail: ");
        emailDigitado = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        senhaDigitada = scanner.nextLine();


        System.out.println("Tentando logar cliente com Email: " + emailDigitado);

        if (this.emailCliente != null && this.emailCliente.equals(emailDigitado) && this.senha.equals(senhaDigitada)) {
            System.out.println("Login de " + this.nomeCliente + " bem-sucedido!");
            return true;
        } else {
            System.out.println("Falha no login para " + emailDigitado + ": Email ou senha incorretos.");
            return false;
        }

    }

    public String getUfCliente(){
     return ufCliente;
    }

    public void setUfCliente(String ufCliente){
        this.ufCliente = ufCliente;
    }
}


