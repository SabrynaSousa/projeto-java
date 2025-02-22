package ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import ecommerce.model.ProdutoConcreto;
import ecommerce.model.Produto;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            System.out.println("=== E-Commerce ===");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner, produtos);
                    break;
                case 2:
                    listarProdutos(produtos);
                    break;
                case 3:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

 
    private static void cadastrarProduto(Scanner scanner, List<Produto> produtos) {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();  

        Produto produto = new ProdutoConcreto(nome, preco);
        produtos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

  
    private static void listarProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            System.out.println("Lista de Produtos:");
            for (Produto produto : produtos) {
                produto.exibirInformacoes();
            }
        }
    }
}
