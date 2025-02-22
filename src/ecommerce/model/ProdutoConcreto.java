package ecommerce.model;

public class ProdutoConcreto extends Produto {

    public ProdutoConcreto(String nome, double preco) {
        super(nome, preco);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Produto: " + getNome() + " | Preço: R$" + String.format("%.2f", getPreco()));
    }
}
