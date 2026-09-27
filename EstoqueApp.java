
public class EstoqueApp {

    public static void main(String[] args) {

        Estoque estoque = new Estoque();


        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║        SISTEMA DE ESTOQUE DE PRODUTOS            ║");
        System.out.println("╚══════════════════════════════════════════════════╝\n");

        System.out.println("── 1. CADASTRANDO PRODUTOS ──────────────────────────");
        try {
 
            ProdutoComum caneta = new ProdutoComum("Caneta Azul", 2.50, 100);
            ProdutoComum caderno = new ProdutoComum("Caderno 96fls", 18.90, 50);

            ProdutoPerecivel leite = new ProdutoPerecivel("Leite Integral 1L", 6.80, 30, 10);
            ProdutoPerecivel iogurte = new ProdutoPerecivel("Iogurte Natural", 4.50, 20, 2); // vence em breve!

            estoque.adicionarProduto(caneta);
            estoque.adicionarProduto(caderno);
            estoque.adicionarProduto(leite);
            estoque.adicionarProduto(iogurte);

        } catch (QuantidadeInvalidaException e) {
            System.out.println("  ERRO ao cadastrar: " + e.getMessage());
        }

    
        System.out.println("\n── 2. TENTANDO CADASTRAR PRODUTO COM VALOR INVÁLIDO ─");
        try {
            ProdutoComum produtoInvalido = new ProdutoComum("Borracha", -5.00, -10);
            estoque.adicionarProduto(produtoInvalido);
        } catch (QuantidadeInvalidaException e) {
      
            System.out.println("  ✘ QuantidadeInvalidaException capturada: " + e.getMessage());
        } catch (EstoqueException e) {
           
            System.out.println("  ✘ EstoqueException capturada: " + e.getMessage());
        }

  
        System.out.println("\n── 3. REALIZANDO VENDA VÁLIDA ───────────────────────");
        try {
            System.out.println("  Tentando vender 10 unidades de Caneta Azul (índice 0)...");
            estoque.venderProduto(0, 10); 

        } catch (ProdutoIndisponivelException e) {
            System.out.println("  ✘ ProdutoIndisponivelException: " + e.getMessage());
        } catch (EstoqueException e) {
            System.out.println("  ✘ EstoqueException: " + e.getMessage());
        }

        System.out.println("\n── 4. TENTANDO VENDER ALÉM DO ESTOQUE ───────────────");
        try {
            System.out.println("  Tentando vender 500 unidades de Caderno (índice 1)...");
            estoque.venderProduto(1, 500); 

        } catch (ProdutoIndisponivelException e) {
  
            System.out.println("  ✘ ProdutoIndisponivelException capturada: " + e.getMessage());
        } catch (EstoqueException e) {
            System.out.println("  ✘ EstoqueException capturada: " + e.getMessage());
        }


        System.out.println("\n── 5. APLICANDO DESCONTOS (SOBRECARGA) ──────────────");
        try {
            ProdutoComum mochila = new ProdutoComum("Mochila Escolar", 120.00, 5);


            mochila.aplicarDesconto(10.0);

            mochila.aplicarDesconto(50.0, 30.00); 

        } catch (QuantidadeInvalidaException e) {
            System.out.println("  ERRO: " + e.getMessage());
        }

    
        System.out.println("\n── 6. VALOR TOTAL DO ESTOQUE ────────────────────────");

        estoque.listarProdutos();

        System.out.println("\n  Calculando valor total (polimorfismo dinâmico):");
        for (int i = 0; i < estoque.getProdutos().size(); i++) {
            Product p = estoque.getProdutos().get(i);
            System.out.printf("  [%d] %-22s → R$ %8.2f%n",
                    i, p.getNome(), p.calcularValorTotal());
        }

        double totalGeral = estoque.calcularValorTotalEstoque();
        System.out.println("  ──────────────────────────────────────────────");
        System.out.printf("  VALOR TOTAL DO ESTOQUE: R$ %.2f%n", totalGeral);
        System.out.println("  (iogurte com 2 dias para vencer teve 20%% de desconto automático)");

        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println("║              PROGRAMA ENCERRADO                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
