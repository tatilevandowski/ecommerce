import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    // Listas para "simular" um banco de dados em memória
    private final static List<Cliente> clientes = new ArrayList<>();
    private final static List<Produtos> produtos = new ArrayList<>();
    private final static List<Pedido> pedidos = new ArrayList<>();
    private final static CarrinhoCompras carrinhoAtual = new CarrinhoCompras();

    private final static Scanner scanner = new Scanner(System.in);
    private static Cliente clienteLogado = null;

    public static void main(String[] args) {

        inicializarDados();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuProduto();
                    break;
                case 3:
                    menuCarrinho();
                    break;
                case 4:
                    menuPedido();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        } while (opcao != 0);

        scanner.close();
    }

    private static void inicializarDados() {

        clientes.add(new Cliente("Ana Silva", "Rua A, 100", "SP", "ana@email.com", "11987654321"));
        clientes.add(new Cliente("Bruno Costa", "Av. B, 200", "RJ", "bruno@email.com", "21987654321"));

        produtos.add(new Produtos(101, "Camiseta Básica", 45.00, "M", "Branca"));
        produtos.add(new Produtos(102, "Calça Jeans", 130.00, "42", "Azul Escuro"));
        produtos.add(new Produtos(103, "Meia Esportiva", 15.00, "Único", "Preta"));
        produtos.add(new Produtos(104, "Boné Estiloso", 60.00, "Único", "Vermelho"));

        System.out.println("Dados iniciais carregados: " + clientes.size() + " clientes e " + produtos.size() + " produtos.");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("--- MENU PRINCIPAL E-COMMERCE ---");
        System.out.println("1. Funções de Cliente");
        System.out.println("2. Funções de Produto (Administrador)");
        System.out.println("3. Carrinho de Compras");
        System.out.println("4. Pedidos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int lerOpcaoMenu() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    private static void menuCliente() {
        int opcao;
        do {
            System.out.println("--- MENU CLIENTE ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Fazer Login");
            System.out.println("3. Exibir Clientes Cadastrados");
            System.out.println("4. Alterar Dados de Cliente (após login)");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1: cadastrarCliente(); break;
                case 2: fazerLogin(); break;
                case 3: exibirClientes(); break;
                case 4: alterarClienteLogado(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
            if (opcao != 0) {
                System.out.println("Pressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private static void cadastrarCliente() {
        System.out.println("--- CADASTRO DE NOVO CLIENTE ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("UF: ");
        String uf = scanner.nextLine();
        System.out.print("Email (será seu login): ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        try {

            Cliente novoCliente = new Cliente(nome, endereco, uf, email, telefone);
            clientes.add(novoCliente);

            System.out.println("Cliente cadastrado com sucesso: " + nome + " (email: " + email + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    private static void exibirClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("\n--- CLIENTES CADASTRADOS ---");
        for (Cliente c : clientes) {
            System.out.println("Nome: " + c.getNomeCliente() + ", Email: " + c.getEmailCliente() + ", UF: " + c.getUfCliente());
        }
    }

    private static void fazerLogin() {
        if (clienteLogado != null) {
            System.out.println("Você já está logado como: " + clienteLogado.getNomeCliente());
            return;
        }

        System.out.println("\n--- LOGIN DO CLIENTE ---");
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Cliente c : clientes) {
            if (c.logarCliente(email, senha)) {
                clienteLogado = c;
                System.out.println("Login bem-sucedido! Bem-vindo(a), " + clienteLogado.getNomeCliente() + ".");
                return;
            }
        }
        System.out.println("Login falhou: E-mail ou senha incorretos.");
    }

    private static void alterarClienteLogado() {
        if (clienteLogado == null) {
            System.out.println("Nenhum cliente logado. Faça login primeiro.");
            return;
        }
        System.out.println("\n--- ALTERAR DADOS DO CLIENTE LOGADO ---");
        System.out.println("Cliente atual: " + clienteLogado.getNomeCliente());

        System.out.print("Novo endereço (deixe em branco para manter): ");
        String novoEndereco = scanner.nextLine();
        if (!novoEndereco.isEmpty()) {
            clienteLogado.setEnderecoCliente(novoEndereco);
        }

        System.out.print("Nova UF (deixe em branco para manter): ");
        String novaUf = scanner.nextLine();
        if (!novaUf.isEmpty()) {
            clienteLogado.setUfCliente(novaUf);
        }

        System.out.print("Novo telefone (deixe em branco para manter): ");
        String novoTelefone = scanner.nextLine();
        if (!novoTelefone.isEmpty()) {
            clienteLogado.setTelefoneCliente(novoTelefone);
        }

        clienteLogado.alterarCliente();
        System.out.println("Dados alterados com sucesso para " + clienteLogado.getNomeCliente() + ".");
    }


    // --- Menu Produto (Admin) ---
    private static void menuProduto() {
        if (clienteLogado == null || !clienteLogado.getEmailCliente().equals("admin@email.com")) { // Simples checagem de admin
            System.out.println("Acesso negado. Apenas administradores podem gerenciar produtos.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- MENU PRODUTO (ADMIN) ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Alterar Preço de Produto");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1: cadastrarProduto(); break;
                case 2: listarProdutos(); break;
                case 3: alterarPrecoProduto(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private static void cadastrarProduto() {
        System.out.println("\n--- CADASTRO DE NOVO PRODUTO ---");
        System.out.print("Código do Produto: ");
        Integer cod = lerInteger();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        Double preco = lerDouble();
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        try {
            Produtos novoProduto = new Produtos(cod, nome, preco, tamanho, cor);
            produtos.add(novoProduto);
            System.out.println("Produto '" + nome + "' cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        for (Produtos p : produtos) {
            p.exibirDetalhes();
        }
    }

    private static void alterarPrecoProduto() {
        System.out.println("\n--- ALTERAR PREÇO DE PRODUTO ---");
        System.out.print("Digite o código do produto para alterar: ");
        Integer cod = lerInteger();

        Produtos produtoEncontrado = null;
        for (Produtos p : produtos) {
            if (p.getCodigoProduto().equals(cod)) {
                produtoEncontrado = p;
                break;
            }
        }

        if (produtoEncontrado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Produto: " + produtoEncontrado.getNomeProduto() + ", Preço Atual: R$ " + produtoEncontrado.getPrecoProduto());
        System.out.print("Novo Preço: ");
        Double novoPreco = lerDouble();
        produtoEncontrado.setPrecoProduto(novoPreco); // Usa o setter do Produto
        System.out.println("Preço do produto '" + produtoEncontrado.getNomeProduto() + "' atualizado para R$ " + produtoEncontrado.getPrecoProduto());
    }

    // --- Menu Carrinho ---
    private static void menuCarrinho() {
        if (clienteLogado == null) {
            System.out.println("Você precisa estar logado para usar o carrinho de compras.");
            return;
        }

        int opcao;
        do {
            System.out.println("--- MENU CARRINHO DE COMPRAS ---");
            System.out.println("1. Adicionar Produto ao Carrinho");
            System.out.println("2. Atualizar Quantidade de Item no Carrinho");
            System.out.println("3. Remover Item do Carrinho");
            System.out.println("4. Exibir Carrinho");
            System.out.println("5. Calcular Total do Carrinho");
            System.out.println("6. Limpar Carrinho");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1: adicionarProdutoAoCarrinho(); break;
                case 2: atualizarQuantidadeCarrinho(); break;
                case 3: removerItemCarrinho(); break;
                case 4: carrinhoAtual.exibirCarrinho(); break;
                case 5: System.out.printf("Total do Carrinho: R$ %.2f%n", carrinhoAtual.calcularTotalCarrinho()); break;
                case 6: carrinhoAtual.limparCarrinho(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private static void adicionarProdutoAoCarrinho() {
        listarProdutos(); // Mostra os produtos disponíveis
        System.out.print("Digite o código do produto que deseja adicionar: ");
        Integer codProduto = lerInteger();
        System.out.print("Digite a quantidade: ");
        Integer quantidade = lerInteger();

        Integer adicionarProduto = null;
        for (Produtos p : produtos) {
            if (p.getCodigoProduto().equals(codProduto)) {
                adicionarProduto = p.getCodigoProduto();
                break;
            }
        }

        if (adicionarProduto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        carrinhoAtual.ItemCarrinho(adicionarProduto, quantidade);
    }

    private static void atualizarQuantidadeCarrinho() {
        carrinhoAtual.exibirCarrinho();
        if (carrinhoAtual.getItensDoPedido().isEmpty()) {
            System.out.println("Carrinho vazio. Nada para atualizar.");
            return;
        }

        System.out.print("Digite o código do produto para atualizar a quantidade: ");
        Integer codProduto = lerInteger();
        System.out.print("Digite a nova quantidade (0 para remover): ");
        Integer novaQuantidade = lerInteger();

        carrinhoAtual.atualizarQuantidade(codProduto, novaQuantidade);
    }

    private static void removerItemCarrinho() {
        carrinhoAtual.exibirCarrinho();
        if (carrinhoAtual.getItensDoPedido().isEmpty()) {
            System.out.println("Carrinho vazio. Nada para remover.");
            return;
        }

        System.out.print("Digite o código do produto para remover: ");
        Integer codProduto = lerInteger();

        carrinhoAtual.removerProdutoCarrinho(codProduto);
    }

    private static void menuPedido() {
        if (clienteLogado == null) {
            System.out.println("Você precisa estar logado para gerenciar pedidos.");
            return;
        }

        int opcao;
        do {
            System.out.println("\n--- MENU PEDIDOS ---");
            System.out.println("1. Finalizar Pedido (a partir do carrinho)");
            System.out.println("2. Ver Meus Pedidos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1: finalizarNovoPedido(); break;
                case 2: verMeusPedidos(); break;
                case 0: break;
                default: System.out.println("Opção inválida.");
            }
            if (opcao != 0) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private static void finalizarNovoPedido() {
        if (carrinhoAtual.getItensDoPedido().isEmpty()) {
            System.out.println("Seu carrinho está vazio. Adicione produtos antes de finalizar um pedido.");
            return;
        }
        if (clienteLogado == null) {
            System.out.println("Você precisa estar logado para finalizar um pedido.");
            return;
        }

        // Gerar um código de pedido simples para demonstração
        Integer novoCodPedido = pedidos.size() + 1; // ID sequencial simples
        Pedido novoPedido = new Pedido(novoCodPedido, clienteLogado);

        boolean sucesso = novoPedido.finalizarPedido(carrinhoAtual);
        if (sucesso) {
            pedidos.add(novoPedido);
            carrinhoAtual.limparCarrinho(); // Limpa o carrinho após a finalização do pedido
            System.out.println("Pedido finalizado e carrinho esvaziado.");
        } else {
            System.out.println("Não foi possível finalizar o pedido.");
        }
    }

    private static void verMeusPedidos() {
        if (clienteLogado == null) {
            System.out.println("Por favor, faça login para ver seus pedidos.");
            return;
        }
        boolean encontrouPedidos = false;
        System.out.println("\n--- SEUS PEDIDOS (" + clienteLogado.getNomeCliente() + ") ---");
        for (Pedido p : pedidos) {
            if (p.getCliente().getEmailCliente().equals(clienteLogado.getEmailCliente())) {
                p.exibirResumoPedido();
                encontrouPedidos = true;
            }
        }
        if (!encontrouPedidos) {
            System.out.println("Você não tem nenhum pedido registrado ainda.");
        }
    }


    private static Integer lerInteger() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            scanner.next();
            System.out.print("Digite novamente: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static Double lerDouble() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor, digite um número decimal.");
            scanner.next();
            System.out.print("Digite novamente: ");
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}