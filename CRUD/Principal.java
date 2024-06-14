package CRUD;

import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Principal {

    private static String ctt;
    private static int senhaadm = 123;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Random rand = new Random();
        Controller pm = new Controller(); // sugestão: gameController ou game_controller
        boolean o = true; // sugestão: sair ou quit
        boolean verifadm = true;

        while (o == true) {
            boolean i = false;
            System.out.println("Escolha uma opção de cadastro // 1 - ADM // 2 - Cliente");
            int opcao = scanner.nextInt();

            try {
                while (i != true) {

                    // MENU ADMIN
                    if (opcao == 1) {

                        while (verifadm != false) {
                            System.out.println("Insira senha de administrador: ");
                            int senha = scanner.nextInt();

                            if (senha == senhaadm) {
                                verifadm = false;
                            } else {
                                System.out.println("Senha inserida incorreta !");
                            }
                        }

                        System.out.println("Insira a operação que deseja realizar: " +
                                "\n1 -Visualizar Cadastros // \n" +
                                "2 - Excluir Cadastro // \n" +
                                "3 - Alterar Cadastro // \n" +
                                "0 - Sair \n");

                        System.out.printf(">>");
                        String escolha = scanner.next();

                        switch (escolha) {

                            case "1":

                                List<Cliente> clientes = pm.getPessoas(); // sugestão: criar um clientManager ou
                                                                          // client_manager e método de getPessoas para
                                                                          // getClients() ou get_clients()
                                System.out.println("\n-- CADASTROS EXISTENTES --");
                                int cont = 0;
                                for (Cliente cliente : clientes) {
                                    cont++;
                                    System.out.println("# -" + (cont) + " " + cliente);
                                }
                                System.out.println("--------------------------------- \n");
                                break;

                            case "2":

                                System.out.println("Insira o id da pessoa que deseja excluir: ");
                                int id_0 = scanner.nextInt(); // sugestão: id, client_id, clientID ou client_ID
                                pm.deletePessoa(id_0); // sugestão: clientManager.delClient()
                                break;

                            case "3":
                                // TODO
                                break;

                            case "0":
                                System.out.println("Cadastro cliente encerrado... \n");
                                i = true;

                                // default:

                                // System.out.println("Insira uma informação válida! ");
                                break;
                        }
                        // MENU CLIENTE
                    } else if (opcao == 2) { // else para else if (opcao == 2) ; XUNIL

                        
                        



                        
                        System.out.println("Insira a operação que deseja realizar: " +
                                "\n1 - Visualização de jogos // \n" +
                                "2 - Pesquisa jogo por nome // \n" +
                                "3 - Comprar jogo // \n");

                        System.out.printf(">>");
                        String opcao_2 = scanner.next();

                        switch (opcao_2) {
                            case "1":

                                List<Jogo> jogos = pm.getJogos(); // sugestão: gameManager.getJogos()
                                System.out.println("\n -- JOGOS DISPONIVEIS -- ");
                                int cont = 0;
                                for (Jogo j : jogos) { // sugestão: Jogo jogo : jogos
                                    cont++;
                                    // System.out.println("# -" + (cont) + " " + j.getNome() + j.getGenero() +
                                    // j.getQuantidade());
                                    System.out.printf("# - %s %s %d \n", j.getNome(), j.getGenero(), j.getQuantidade());
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

                                System.out.println("-- MENU DE COMPRAS --");

                                List<Jogo> jogos_2 = pm.getJogos();
                                System.out.println("\n -- JOGOS DISPONIVEIS -- ");
                                int cont_2 = 0;
                                for (Jogo j : jogos_2) {
                                    cont_2++;
                                    // System.out.println("# -" + (cont_2) + " " + j);
                                    System.out.printf("%d - %s %s %d \n", cont_2, j.getNome(), j.getGenero(),
                                            j.getQuantidade());
                                }
                                System.out.println("------------------------------- \n");

                                System.out.println("Selecione uma opcao: ");
                                String nome_1 = scanner.next();

                                pm.comprar_jogo(nome_1);

                                break;

                            case "4":

                                break;

                            case "0":
                                System.out.println("Encerrado... \n");
                                i = true;
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
