package CRUD;

import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Principal {

    private static String ctt;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        ClienteController pm = new ClienteController();
        boolean o = true;
        
        while (o == true) {
            boolean i = false;
            System.out.println("Escolha uma opção de cadastro // 1 - Cliente // 2 - Jogos  ");
            int opcao = scanner.nextInt();

            try {
                while (i != true) {

                    if (opcao == 1) {

                        System.out.println("Insira a operação que deseja realizar: " +
                                "\n1 -Visualizar Cadastros // \n" +
                                "2 - Excluir Cadastro // \n" +
                                "3 - Alterar Cadastro // \n" +
                                "0 - Sair \n");

                        System.out.printf(">>");
                        String escolha = scanner.next();

                        switch (escolha) {

                            case "1":

                                List<Cliente> clientes = pm.getPessoas();
                                System.out.println("\n-- CADASTROS EXISTENTES --");
                                int cont = 0;
                                for (Cliente p : clientes) {
                                    cont++;
                                    System.out.println("# -" + (cont) + " " + p);
                                }
                                System.out.println("------------------------------- \n");
                                break;

                            case "2":

                                System.out.println("Insira o id da pessoa que deseja excluir: ");
                                int id_0 = scanner.nextInt();
                                pm.deletePessoa(id_0);
                                break;

                            case "3":

                                break;

                            case "0":
                                System.out.println("Cadastro cliente encerrado... \n");
                                i = true;

                                // default:

                                // System.out.println("Insira uma informação válida! ");
                                break;
                        }

                    } else {
                        System.out.println("Logado como: " + ctt);
                        System.out.println("Insira a operação que deseja realizar: " +
                                "\n1 - Login // \n" +
                                "2 - Cadastrar \n");
                        System.out.printf(">>");
                        String opcao_1 = scanner.next();

                        switch (opcao_1) {
                            case "1":

                                System.out.println("Insira o nome do login:");
                                String nome_1 = scanner.next();

                                List<Cliente> clientes_1 = pm.getPessoas();
                                for (Cliente p : clientes_1) {
                                    if (p.getNome().equals(nome_1)) {
                                        ctt = p.getNome();
                                        break;
                                    } else {
                                        System.out.println("Nome não existente nos cadastros !");
                                        break;
                                    }
                                }
                                System.out.println("------------------------------- \n");

                                break;

                            case "2":

                                System.out.println("\n-- CADASTRE-SE AQUI --");

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

                            case "0":
                                System.out.println("Seleção de jogos encerrada \n");
                                i = true;
                                break;

                            default:
                                System.out.println("Insira uma informação válida!");

                                break;
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }

        }
        scanner.close();
    }

}
