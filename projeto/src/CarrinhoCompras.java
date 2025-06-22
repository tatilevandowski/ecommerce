import java.util.ArrayList;
import java.util.List;

public class CarrinhoCompras {
    private List<ItemCarrinho> itens;

    public CarrinhoCompras() {
        this.itens = new ArrayList<>();
    }

    public class ItemCarrinho {
        Integer codProduto;
        String nomeProduto;
        Integer quantidade;
        Double precoUnitario;

        public ItemCarrinho(Integer codProduto, String nomeProduto, Integer quantidade, Double precoUnitario) {
            this.codProduto = codProduto;
            this.nomeProduto = nomeProduto;
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
        }

        public Double getSubtotal() {
            return quantidade * precoUnitario;
        }
    }

    // 1. Adicionar produto
    public void adicionarProduto(Integer codProduto, String nomeProduto, Integer quantidade, Double precoUnitario) {
        // Verifica se o produto já existe no carrinho
        for (ItemCarrinho item : itens) {
            if (item.codProduto.equals(codProduto)) {
                item.quantidade += quantidade;
                System.out.printf("Quantidade atualizada: %s (Total: %d)%n",
                        nomeProduto, item.quantidade);
                return;
            }
        }

        // Se não existir, adiciona novo item
        itens.add(new ItemCarrinho(codProduto, nomeProduto, quantidade, precoUnitario));
        System.out.printf("%s adicionado ao carrinho%n", nomeProduto);
    }

    // 2. Atualizar quantidade
    public void atualizarQuantidade(Integer codProduto, Integer novaQuantidade) {
        for (ItemCarrinho item : itens) {
            if (item.codProduto.equals(codProduto)) {
                if (novaQuantidade <= 0) {
                    itens.remove(item);
                    System.out.println("Item removido do carrinho");
                } else {
                    item.quantidade = novaQuantidade;
                    System.out.printf("Quantidade atualizada para %d%n", novaQuantidade);
                }
                return;
            }
        }
        System.out.println("Produto não encontrado no carrinho");
    }

    // 3. Remover item
    public void removerItem(Integer codProduto) {
        if (itens.removeIf(item -> item.codProduto.equals(codProduto))) {
            System.out.println("Item removido do carrinho");
        } else {
            System.out.println("Produto não encontrado no carrinho");
        }
    }

    // 4. Exibir carrinho
    public void exibirCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio");
            return;
        }

        System.out.println("\n--- CARRINHO DE COMPRAS ---");
        itens.forEach(item ->
                System.out.printf("Cód: %d | %s | %d x R$%.2f = R$%.2f%n",
                        item.codProduto,
                        item.nomeProduto,
                        item.quantidade,
                        item.precoUnitario,
                        item.getSubtotal()));

        System.out.printf("TOTAL: R$%.2f%n", calcularTotal());
    }

    // 5. Calcular total
    public Double calcularTotal() {
        return itens.stream()
                .mapToDouble(ItemCarrinho::getSubtotal)
                .sum();
    }

    // 6. Limpar carrinho
    public void limparCarrinho() {
        itens.clear();
        System.out.println("Carrinho limpo");
    }

    public List<ItemCarrinho> getItens() {
        return new ArrayList<>(itens); // Retorna cópia para evitar alterações externas
    }
}
