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
        Controller pm = new Controller();
        boolean o = true;

        while (o == true) {
            boolean i = false;
            System.out.println("Escolha uma opção de cadastro // 1 - ADM // 2 - Cliente");
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
                                System.out.println("--------------------------------- \n");
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

                        System.out.println("Insira a operação que deseja realizar: " +
                                "\n1 - Visualização de jogos // \n" +
                                "2 - Pesquisa jogo por nome // \n" +
                                "3 - Comprar jogo // \n");

                        System.out.printf(">>");
                        String opcao_2 = scanner.next();

                        switch (opcao_2) {
                            case "1":

                                List<Jogo> jogos = pm.getJogos();
                                System.out.println("\n -- JOGOS DISPONIVEIS -- ");
                                int cont = 0;
                                for (Jogo j : jogos) {
                                    cont++;
                                    System.out.println("# -" + (cont) + " " + j);
                                }
                                System.out.println("------------------------------- \n");

                                break;

                            case "2":

                                System.out.println("Insira o nome do jogo que deseja achar: ");
                                String nome = scanner.next();

                                List<Jogo> jogos_1 = pm.getJogo_por_nome(nome);
                                int cont_1 = 0;
                                for (Jogo j : jogos_1) {
                                    cont_1++;
                                    System.out.println("# -" + (cont_1) + " " + j);
                                }
                                System.out.println("---------------------------------- \n");

                                break;

                            case "3":


                                break;

                            default:

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
