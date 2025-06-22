import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static List<Cliente> clientes = new ArrayList<>();
    private final static List<Produtos> produtos = new ArrayList<>();
    private final static List<Pedido> pedidos = new ArrayList<>();
    private final static CarrinhoCompras carrinhoAtual = new CarrinhoCompras();

    private final static Scanner scanner = new Scanner(System.in);
    private static Cliente clienteLogado;
    private static int contadorPedidos = 1;

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
        // Adiciona produtos diretamente aqui
        produtos.add(new Produtos(101, "Camiseta Básica", 45.00, "M", "Branca"));
        produtos.add(new Produtos(102, "Calça Jeans", 130.00, "42", "Azul Escuro"));
        produtos.add(new Produtos(103, "Meia Esportiva", 15.00, "Único", "Preta"));
        produtos.add(new Produtos(104, "Boné Estiloso", 60.00, "Único", "Vermelho"));
        // Configura a lista de produtos na classe DetalhesPedido
        DetalhesPedido.setListaProdutos(produtos);
        clientes.add(new Cliente("Ana Silva", "Rua A, 100", "SP", "ana@email.com", "11987654321"));
        clientes.add(new Cliente("Bruno Costa", "Av. B, 200", "RJ", "bruno@email.com", "21987654321"));
        System.out.println("Dados iniciais carregados: " + clientes.size() + " clientes e " + produtos.size() + " produtos.");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("--- MENU PRINCIPAL E-COMMERCE ---");
        System.out.println("1. Cliente");
        System.out.println("2. Produto (Administrador)");
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
                case 1:
                    Cliente novoCliente = Cliente.cadastrarCliente();
                    if (novoCliente != null) {
                        clientes.add(novoCliente);
                    }
                    break;
                case 2:
                    clienteLogado = Cliente.fazerLogin(clientes);
                    break;
                case 3:
                    Cliente.exibirClientes(clientes);
                    break;
                case 4:
                    if (clienteLogado != null) {
                        clienteLogado.alterarCliente();
                    } else {
                        System.out.println("Nenhum cliente logado. Faça login primeiro.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
            if (opcao != 0) {
                System.out.println("Pressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private static void menuProduto() {
        if (clienteLogado == null || !clienteLogado.getEmailCliente().equals("admin@gmail.com")) {
            System.out.println("Acesso negado. Apenas administradores podem gerenciar produtos.");
            return;
        }

        int opcao;
        do {
            System.out.println("--- MENU PRODUTO (ADMIN) ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Alterar Preço de Produto");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1:
                    produtos.add(Produtos.cadastrarProduto(scanner));
                    break;
                case 2:
                    Produtos.listarProdutos(produtos);
                    break;
                case 3:
                    Produtos.alterarPrecoProduto(produtos, scanner);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                System.out.println("Pressione Enter para continuar...");
                scanner.nextLine();
            }
        } while (opcao != 0);
    }

    private static void menuCarrinho() {
        if (clienteLogado == null) {
            System.out.println("Você precisa estar logado para usar o carrinho");
            return;
        }

        int opcao;
        do {
            System.out.println("--- MENU CARRINHO ---");
            System.out.println("1. Adicionar produto");
            System.out.println("2. Atualizar quantidade");
            System.out.println("3. Remover item");
            System.out.println("4. Ver carrinho");
            System.out.println("5. Calcular total");
            System.out.println("6. Limpar carrinho");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            opcao = lerOpcaoMenu();

            switch (opcao) {
                case 1: adicionarProdutoAoCarrinho(); break;
                case 2: atualizarQuantidadeCarrinho(); break;
                case 3: removerItemCarrinho(); break;
                case 4: carrinhoAtual.exibirCarrinho(); break;
                case 5: System.out.printf("Total: R$ %.2f%n", carrinhoAtual.calcularTotal()); break;
                case 6: carrinhoAtual.limparCarrinho(); break;
                case 0: System.out.println("Retornando..."); break;
                default: System.out.println("Opção inválida"); break;
            }
        } while (opcao != 0);
    }

    private static void adicionarProdutoAoCarrinho() {
        Produtos.listarProdutos(produtos);

        System.out.print("Código do produto: ");
        Integer codigo = lerInteger();

        Produtos produto = produtos.stream()
                .filter(p -> p.getCodigoProduto().equals(codigo))
                .findFirst()
                .orElse(null);

        if (produto == null) {
            System.out.println("Produto não encontrado");
            return;
        }

        System.out.print("Quantidade: ");
        Integer quantidade = lerInteger();

        carrinhoAtual.adicionarProduto(
                produto.getCodigoProduto(),
                produto.getNomeProduto(),
                quantidade,
                produto.getPrecoProduto()
        );
    }

    private static void atualizarQuantidadeCarrinho() {
        carrinhoAtual.exibirCarrinho();

        System.out.print("Código do produto: ");
        Integer codigo = lerInteger();
        System.out.print("Nova quantidade: ");
        Integer quantidade = lerInteger();

        carrinhoAtual.atualizarQuantidade(codigo, quantidade);
    }

    private static void removerItemCarrinho() {
        carrinhoAtual.exibirCarrinho();

        System.out.print("Código do produto: ");
        Integer codigo = lerInteger();

        carrinhoAtual.removerItem(codigo);
    }

    private static void menuPedido() {
        if (clienteLogado == null) {
            System.out.println("Você precisa estar logado para fazer um pedido.");
            return;
        }
        int opcao;
        do {
            System.out.println("\n--- MENU PEDIDOS ---");
            System.out.println("1. Finalizar Pedido");
            System.out.println("2. Exibir Último Pedido");
            System.out.println("3. Informações de envio");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoMenu();
            switch (opcao) {
                case 1:
                    if (carrinhoAtual.getItens().isEmpty()) {
                        System.out.println("O carrinho está vazio. Adicione produtos antes de finalizar o pedido.");
                        return;
                    }
                    String codigo = "PED-" + contadorPedidos++;
                    Pedido novoPedido = new Pedido(codigo, clienteLogado);

                    InformacoesEnvio envio = new InformacoesEnvio();
                    envio.setTipoEnvio("Padrão");
                    envio.setCustoEnvio(15.90); // Valor exemplo
                    envio.setCodIbge(123456); // Código exemplo
                    novoPedido.setInformacoesEnvio(envio);

                    if (novoPedido.finalizarPedido(carrinhoAtual)) {
                        pedidos.add(novoPedido);
                        carrinhoAtual.limparCarrinho();
                    }
                    break;

                case 2:
                    if (!pedidos.isEmpty()) {
                        pedidos.get(pedidos.size() - 1).exibirResumoPedido();
                    } else {
                        System.out.println("Nenhum pedido realizado.");
                    }
                    break;

                case 3:
                    if (!pedidos.isEmpty()) {
                        Pedido ultimoPedido = pedidos.get(pedidos.size() - 1);
                        if (ultimoPedido.getInformacoesEnvio() != null) {
                            ultimoPedido.getInformacoesEnvio().exibirInformacoes();
                        } else {
                            System.out.println("Nenhuma informação de envio configurada para este pedido.");
                        }
                    } else {
                        System.out.println("Nenhum pedido realizado.");
                    }
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
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
