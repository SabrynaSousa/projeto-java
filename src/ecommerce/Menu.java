package ecommerce;

import java.io.IOException;
import java.util.*;

public class Menu {

	public static Scanner leia = new Scanner(System.in);
	public static List<Produto> produtos = new ArrayList<>();
	public static List<ItemCarrinho> carrinho = new ArrayList<>();

	public static void main(String[] args) {

		int opcao = 0;

		// Teste de produtos
		produtos.add(new Produto(1, "Cadeira Gamer", 749.99f));
		produtos.add(new Produto(2, "Teclado", 199.99f));
		produtos.add(new Produto(3, "Mouse", 99.90f));

		while (true) {

			System.out.println("*****************************************************");
			System.out.println("                E-COMMERCE                           ");
			System.out.println("*****************************************************");
			System.out.println("            1 - Listar Produtos                     ");
			System.out.println("            2 - Adicionar Produto ao Carrinho       ");
			System.out.println("            3 - Ver Carrinho                        ");
			System.out.println("            4 - Finalizar Compra                    ");
			System.out.println("            5 - Sair                                ");
			System.out.println("*****************************************************");
			System.out.print("Entre com a opção desejada: ");

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			switch (opcao) {
			case 1:
				listarProdutos();
				keyPress();
				break;
			case 2:
				comprarProduto();
				keyPress();
				break;
			case 3:
				visualizarCarrinho();
				keyPress();
				break;
			case 4:
				finalizarCompra();
				keyPress();
				break;
			case 5:
				System.out.println("\nObrigado por usar nosso e-commerce! Volte sempre.");
				leia.close();
				System.exit(0);
				break;
			default:
				System.out.println("\nOpção Inválida");
				keyPress();
				break;
			}
		}
	}

	public static void listarProdutos() {
		System.out.println("\nLista de Produtos:");
		for (Produto produto : produtos) {
			System.out.println(produto);
		}
	}

	public static void comprarProduto() {
		listarProdutos();
		System.out.print("Digite o ID do produto que deseja adicionar ao carrinho: ");

		int produtoId;
		try {
			produtoId = leia.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("ID inválido! Digite um número inteiro.");
			leia.nextLine();
			return;
		}

		Produto produtoSelecionado = null;
		for (Produto produto : produtos) {
			if (produto.getId() == produtoId) {
				produtoSelecionado = produto;
				break;
			}
		}

		if (produtoSelecionado == null) {
			System.out.println("Produto não encontrado!");
			return;
		}

		System.out.println("Produto selecionado: " + produtoSelecionado);
		System.out.print("Digite a quantidade desejada: ");

		int quantidade;
		try {
			quantidade = leia.nextInt();
			if (quantidade <= 0) {
				System.out.println("A quantidade deve ser maior que zero!");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Quantidade inválida! Digite um número inteiro.");
			leia.nextLine();
			return;
		}

		// Adicionando ao carrinho
		carrinho.add(new ItemCarrinho(produtoSelecionado, quantidade));
		System.out.println("Produto adicionado ao carrinho com sucesso!");
	}

	public static void visualizarCarrinho() {
		if (carrinho.isEmpty()) {
			System.out.println("\nSeu carrinho está vazio!");
			return;
		}

		System.out.println("\nItens no Carrinho:");
		float total = 0;
		for (ItemCarrinho item : carrinho) {
			System.out.println(item);
			total += item.getSubtotal();
		}
		System.out.printf("Total: R$ %.2f%n", total);
	}

	public static void finalizarCompra() {
		if (carrinho.isEmpty()) {
			System.out.println("\nSeu carrinho está vazio! Adicione produtos antes de finalizar a compra.");
			return;
		}

		visualizarCarrinho();
		System.out.print("Deseja confirmar a compra? (S/N): ");
		char confirmacao = leia.next().toUpperCase().charAt(0);

		if (confirmacao == 'S') {
			System.out.println("Compra finalizada com sucesso! Obrigado por comprar conosco.");
			carrinho.clear(); // Limpa o carrinho após a compra
		} else {
			System.out.println("Compra cancelada.");
		}
	}

	public static void sobre() {
		   System.out.println("\n*********************************************************");
		   System.out.println("Projeto Desenvolvido por: ");
		   System.out.println("Lays S - layss@genstudents.org");
		   System.out.println("github.com/conteudoGeneration");
		   System.out.println("*********************************************************");
	}

	public static void keyPress() {
		try {
			System.out.println("\nPressione Enter para continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de enter!");
		}
	}

	// Classe Produto
	static class Produto {
		private int id;
		private String nome;
		private float preco;

		public Produto(int id, String nome, float preco) {
			this.id = id;
			this.nome = nome;
			this.preco = preco;
		}

		public int getId() {
			return id;
		}

		public String getNome() {
			return nome;
		}

		public float getPreco() {
			return preco;
		}

		@Override
		public String toString() {
			return "ID: " + id + " | Nome: " + nome + " | Preço: R$ " + preco;
		}
	}

	// Classe ItemCarrinho para armazenar produtos com quantidade
	static class ItemCarrinho {
		private Produto produto;
		private int quantidade;

		public ItemCarrinho(Produto produto, int quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
		}

		public float getSubtotal() {
			return quantidade * produto.getPreco();
		}

		@Override
		public String toString() {
			return produto.getNome() + " | Quantidade: " + quantidade + " | Subtotal: R$ " + getSubtotal();
		}
	}
}
