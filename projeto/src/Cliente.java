import java.util.List;
import java.util.Scanner;

public class Cliente {
    private Integer codCliente;
    private String nomeCliente;
    private String enderecoCliente;
    private String ufCliente;
    private String emailCliente;
    private String telefoneCliente;
    private String senha;

    private static final Scanner scanner = new Scanner(System.in);

    public Cliente(String nomeCliente, String enderecoCliente, String emailCliente, String telefoneCliente, String ufCliente) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.emailCliente = emailCliente;
        this.telefoneCliente = telefoneCliente;
        this.ufCliente = ufCliente;
    }

    public Cliente() {
    }

    // Getters e Setters
    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
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

    public String getUfCliente() {
        return ufCliente;
    }

    public void setUfCliente(String ufCliente) {
        this.ufCliente = ufCliente;
    }

    public String getSenhaCliente() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static Cliente cadastrarCliente() {
        System.out.println("--- CADASTRO DE NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        if (nome == null || nome.isEmpty()) {
            System.out.println("Erro: Nome do cliente é obrigatório para cadastro.");
            return null;
        }
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("UF: ");
        String uf = scanner.nextLine();
        System.out.print("Email (será seu login): ");
        String email = scanner.nextLine();
        if (email == null || !email.contains("@")) {
            System.out.println("Erro: Email inválido para cadastro.");
            return null;
        }
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        Cliente novoCliente = new Cliente(nome, endereco, email, telefone, uf);
        System.out.print("Defina uma senha: ");
        novoCliente.setSenha(scanner.nextLine());
        System.out.println("Cliente cadastrado com sucesso!");
        return novoCliente;
    }

    public void alterarCliente() {
        System.out.println("Dados do cliente " + this.nomeCliente + " (Email: " + this.emailCliente + ") prontos para serem alterados.");
        System.out.print("Novo Endereço: ");
        this.enderecoCliente = scanner.nextLine();
        System.out.print("Nova UF: ");
        this.ufCliente = scanner.nextLine();
        System.out.print("Novo Telefone: ");
        this.telefoneCliente = scanner.nextLine();
        System.out.println("Cliente alterado com sucesso!");
    }

    public boolean logarCliente(String email, String senha) {
        return this.emailCliente.equals(email) && this.senha.equals(senha);
    }

    public static Cliente fazerLogin(List<Cliente> clientes) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Cliente c : clientes) {
            if (c.logarCliente(email, senha)) {
                System.out.println("Login realizado.");
                return c;
            }
        }

        System.out.println("Login falhou. Email ou senha incorretos.");
        return null;
    }

    public static void exibirClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("--- CLIENTES CADASTRADOS ---");
        System.out.printf("%-6s | %-30s | %-25s | %-4s%n", "Código", "Nome", "E-mail", "UF");
        System.out.println("---------------------------------------------------------------------");

        for (Cliente cliente : clientes) {
            System.out.printf("%-6d | %-30s | %-25s | %-4s%n",
                    cliente.getCodCliente(),
                    cliente.getNomeCliente(),
                    cliente.getEmailCliente(),
                    cliente.getUfCliente());
        }
    }

    @Override
    public String toString() {
        return String.format("Cliente [Código: %d, Nome: %s, Email: %s, UF: %s]",
                this.codCliente != null ? this.codCliente : 0,
                this.nomeCliente != null ? this.nomeCliente : "N/A",
                this.emailCliente != null ? this.emailCliente : "N/A",
                this.ufCliente != null ? this.ufCliente : "N/A");
    }
}
