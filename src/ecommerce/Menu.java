package ecommerce;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import ecommerce.model.Produto;
import ecommerce.model.ProdutoConcreto;

public class Menu {
    private static Map<String, Produto> produtos = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== E-Commerce ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Remover Produto");
            System.out.println("4. Atualizar Produto");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();  

                switch (opcao) {
                    case 1:
                        cadastrarProduto(scanner);
                        break;
                    case 2:
                        listarProdutos();
                        break;
                    case 3:
                        removerProduto(scanner);
                        break;
                    case 4:
                        atualizarProduto(scanner);
                        break;
                    case 5:
                        continuar = false;
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite um número.");
                scanner.nextLine(); 
            }
        }

        scanner.close();
    }

    // Cadastrar Produto
    private static void cadastrarProduto(Scanner scanner) {
        try {
            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();

            if (produtos.containsKey(nome)) {
                System.out.println("Produto já cadastrado!");
                return;
            }

            System.out.print("Digite o preço do produto: ");
            double preco = scanner.nextDouble();
            scanner.nextLine(); 

            Produto produto = new ProdutoConcreto(nome, preco);
            produtos.put(nome, produto);

            System.out.println("Produto cadastrado com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Erro: Preço inválido! Digite um número decimal.");
            scanner.nextLine(); 
        }
    }

    // Listar Produtos
    private static void listarProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("\n Lista de Produtos:");
            for (Produto produto : produtos.values()) {
                produto.exibirInformacoes();
            }
        }
    }

    // Remover Produto
    private static void removerProduto(Scanner scanner) {
    	 if (produtos.isEmpty()) {
             System.out.println("Nenhum produto cadastrado.");
         } else {
        System.out.print("Digite o nome do produto a ser removido: ");
        String nome = scanner.nextLine();

        if (produtos.containsKey(nome)) {
            produtos.remove(nome);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
    }

    // Atualizar Produto
    private static void atualizarProduto(Scanner scanner) {
    	 if (produtos.isEmpty()) {
             System.out.println("Nenhum produto cadastrado.");
         } else {
        System.out.print("Digite o nome do produto a ser atualizado: ");
        String nome = scanner.nextLine();
         
        if (produtos.containsKey(nome)) {
            try {
                System.out.print("Digite o novo preço: ");
                double novoPreco = scanner.nextDouble();
                scanner.nextLine(); 

                Produto produtoAtualizado = new ProdutoConcreto(nome, novoPreco);
                produtos.put(nome, produtoAtualizado);

                System.out.println("Produto atualizado com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Erro: Preço inválido! Digite um número decimal.");
                scanner.nextLine(); 
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
}
