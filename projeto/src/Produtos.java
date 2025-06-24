import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Produtos {
    private String nomeProduto;
    private Integer codigoProduto;
    private Double precoProduto;
    private String tamanhoProduto;
    private String corProduto;

    public Produtos(Integer codigoProduto, String nomeProduto, Double precoProduto, String tamanhoProduto, String corProduto) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
        this.tamanhoProduto = tamanhoProduto;
        this.corProduto = corProduto;
    }

    // Getters e Setters
    public String getNomeProduto() {
        return nomeProduto;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    // Métodos de negócio
    public void exibirDetalhes() {
        System.out.printf("Código: %d | Nome: %s | Preço: R$ %.2f | Tamanho: %s | Cor: %s%n",
                codigoProduto, nomeProduto, precoProduto, tamanhoProduto, corProduto);
    }

    public static Produtos cadastrarProduto(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE NOVO PRODUTO ---");
        System.out.print("Código do Produto: ");
        Integer codigo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço: ");
        Double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Tamanho: ");
        String tamanho = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        return new Produtos(codigo, nome, preco, tamanho, cor);
    }

    public static void listarProdutos(List<Produtos> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        produtos.forEach(Produtos::exibirDetalhes);
    }

    public static void alterarPrecoProduto(List<Produtos> produtos, Scanner scanner) {
        System.out.println("\n--- ALTERAR PREÇO DE PRODUTO ---");
        System.out.print("Digite o código do produto: ");
        Integer codigo = scanner.nextInt();
        scanner.nextLine();

        Produtos produto = produtos.stream()
                .filter(p -> p.getCodigoProduto().equals(codigo))
                .findFirst()
                .orElse(null);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.printf("Produto: %s | Preço Atual: R$ %.2f%n", produto.getNomeProduto(), produto.getPrecoProduto());
        System.out.print("Novo Preço: ");
        Double novoPreco = scanner.nextDouble();
        scanner.nextLine();

        produto.setPrecoProduto(novoPreco);
        System.out.printf("Preço atualizado com sucesso! Novo preço: R$ %.2f%n", produto.getPrecoProduto());
    }

    public String getCor() {
        this.corProduto = corProduto;
        return corProduto;
    }
    public String getTamanho() {
        this.tamanhoProduto = tamanhoProduto;
        return tamanhoProduto;
    }
}
