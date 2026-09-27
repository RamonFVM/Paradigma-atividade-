public class ProdutoPerecivel extends Product {

    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double valorBase = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            return valorBase * 0.80; // 20% de desconto automático
        }
        return valorBase;
    }

    /**
     * Sobrescreve getDescricao() para incluir a validade.
     */
    @Override
    public String getDescricao() {
        String descricaoBase = super.getDescricao();
        String aviso = diasParaVencer <= 3 ? " ⚠ VENCE EM BREVE (desconto 20%)" : "";
        return String.format("%s | Vence em: %d dia(s)%s",
                descricaoBase, diasParaVencer, aviso);
    }

    public int getDiasParaVencer() { return diasParaVencer; }
}
