package service;

import dao.ProdutoDAO;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class TestaProdutoDAO {
    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha uma opção:");
        System.out.println("1 - Inserir produto");
        System.out.println("2 - Listar produtos");
        System.out.print("Opção: ");
        int opcao = sc.nextInt();
        sc.nextLine(); // consumir quebra de linha

        switch (opcao) {
            case 1:
                System.out.print("Nome do produto: ");
                String nome = sc.nextLine();

                System.out.print("Preço do produto: ");
                double preco = sc.nextDouble();

                System.out.print("Quantidade: ");
                int qtd = sc.nextInt();

                Produto novo = new Produto(nome, preco, qtd);
                dao.inserirProduto(novo);
                break;

            case 2:
                List<Produto> produtos = dao.listarTodosProdutos();
                for (Produto p : produtos) {
                    System.out.printf("ID: %d | Nome: %s | Preço: %.2f | Qtd: %d%n",
                            p.getId(), p.getNome(), p.getPreco(), p.getQuantidade());
                }
                break;

            default:
                System.out.println("Opção inválida.");
        }

        sc.close();
    }
}
