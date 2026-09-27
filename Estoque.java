import java.util.ArrayList;
import java.util.List;


public class Estoque {

    private List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
        System.out.println("  ✔ Produto adicionado: " + p.getDescricao());
    }

    public void venderProduto(int indice, int quantidade)
            throws ProdutoIndisponivelException {

        if (indice < 0 || indice >= produtos.size()) {
            throw new ProdutoIndisponivelException(
                "Índice inválido: " + indice + ". O estoque tem "
                + produtos.size() + " produto(s).");
        }
   
        produtos.get(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product p : produtos) {
            total += p.calcularValorTotal();
        }
        return total;
    }

    public void listarProdutos() {
        System.out.println("\n  === PRODUTOS NO ESTOQUE ===");
        if (produtos.isEmpty()) {
            System.out.println("  (nenhum produto cadastrado)");
            return;
        }
        for (int i = 0; i < produtos.size(); i++) {
            System.out.printf("  [%d] %s%n", i, produtos.get(i).getDescricao());
        }
    }

    public List<Product> getProdutos() { return produtos; }
}
