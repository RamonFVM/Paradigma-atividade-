public abstract class Product implements Vendavel {

    private String nome;
    private double preco;
    private int    quantidade;

 
    public Product(String nome, double preco, int quantidade)
            throws QuantidadeInvalidaException {

        if (preco < 0) {
            throw new QuantidadeInvalidaException(
                "Preço inválido: o preço não pode ser negativo. Valor informado: " + preco);
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException(
                "Quantidade inválida: a quantidade não pode ser negativa. Valor informado: " + quantidade);
        }

        this.nome       = nome;
        this.preco      = preco;
        this.quantidade = quantidade;
    }

  
    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format(
            "Produto: %-20s | Preço: R$ %8.2f | Quantidade: %d",
            nome, preco, quantidade
        );
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada > this.quantidade) {
            throw new ProdutoIndisponivelException(
                "Estoque insuficiente para \"" + nome + "\". "
                + "Solicitado: " + quantidadeDesejada
                + " | Disponível: " + this.quantidade
            );
        }
        this.quantidade -= quantidadeDesejada;
        System.out.printf("  ✔ Venda realizada: %d unidade(s) de \"%s\". "
                + "Estoque restante: %d%n",
                quantidadeDesejada, nome, this.quantidade);
    }



    /**
     * Versão 1 — aplica o percentual de desconto diretamente no preço.
     * @param percentual valor entre 0 e 100
     */
    public void aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            System.out.println("  ⚠ Percentual inválido: " + percentual);
            return;
        }
        double desconto = preco * (percentual / 100.0);
        preco -= desconto;
        System.out.printf("  Desconto de %.1f%% aplicado em \"%s\". Novo preço: R$ %.2f%n",
                percentual, nome, preco);
    }

    /**
     * Versão 2 — aplica o percentual, mas limita o valor máximo de desconto.
     * @param percentual   valor entre 0 e 100
     * @param descontoMaximo t
     */
    public void aplicarDesconto(double percentual, double descontoMaximo) {
        if (percentual < 0 || percentual > 100) {
            System.out.println("  ⚠ Percentual inválido: " + percentual);
            return;
        }
        double desconto = Math.min(preco * (percentual / 100.0), descontoMaximo);
        preco -= desconto;
        System.out.printf("  Desconto de %.1f%% (máx R$ %.2f) aplicado em \"%s\". "
                + "Novo preço: R$ %.2f%n",
                percentual, descontoMaximo, nome, preco);
    }

    // ── Getters ───────────────────────────────────────────────────
    public String getNome()       { return nome;       }
    public double getPreco()      { return preco;      }
    public int    getQuantidade() { return quantidade; }

    // ── Setter de preço (usado internamente pelas subclasses) ─────
    protected void setPreco(double preco) { this.preco = preco; }
}
