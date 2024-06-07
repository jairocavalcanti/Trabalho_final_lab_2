package CRUD;

import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class CRUD_Cliente {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        ClienteController pm = new ClienteController();

        try {

            while (true) {
                System.out.println("Insira a operação que deseja realizar: " +
                        "\n1 - Cadastro // \n" +
                        "2 - Visualizar Cadastros // \n" +
                        "3 - Alterar Cadastro // \n" +
                        "4 - Excluir Cadastro // \n");
                System.out.printf(">>");
                String escolha = scanner.next();

                switch (escolha) {
                    case "1":

                        System.out.println("\n-- CADASTROS DE CLIENTES --");

                        int id = rand.nextInt(1000);

                        System.out.printf("Insira o nome: ");
                        String nome = scanner.next();

                        System.out.printf("Insira o endereço: ");
                        String endereço = scanner.next();

                        System.out.printf("Insira o email: ");
                        String email = scanner.next();

                        System.out.printf("Insira a idade: ");
                        int idade = scanner.nextInt();

                        pm.addPessoa(new Cliente(nome, endereço, email, idade, id));

                        System.out.println("Cliente cadastrado com sucesso!");

                        System.out.println("\n");

                        break;

                    case "2":

                        List<Cliente> clientes = pm.getPessoas();
                        System.out.println("\n-- CADASTROS EXISTENTES --");
                        int cont = 0;
                        for (Cliente p : clientes) {
                            cont++;
                            System.out.println("# -" + (cont) + " " + p);
                        }
                        System.out.println("------------------------------- \n");
                        break;

                    case "3":




                        break;

                    case "4":

                        System.out.println("Insira o id da pessoa que deseja excluir: ");
                        int id_0 = scanner.nextInt();
                        pm.deletePessoa(id_0);
                        System.out.println("Cliente deletado com sucesso!");
                        break;

                    case "0":
                        System.out.println("Programa encerrado...");
                        scanner.close();
                        return;

                    default:

                        System.out.println("Insira uma informação válida! ");

                        break;
                }

            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
