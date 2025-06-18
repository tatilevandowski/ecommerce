public class Administrador extends Usuario { // Administrador herda de Usuario

    public Administrador(String nome, String email) {
        super("Administrador", nome, email);
    }

    public void atualizaCatalogo() {
        System.out.println(getNomeUsuario() + " está atualizando o catálogo de produtos.");
    }

    public void gerenciarUsuarios() {
        System.out.println(getNomeUsuario() + " está gerenciando contas de usuários.");
    }

}