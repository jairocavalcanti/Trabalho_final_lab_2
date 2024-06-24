package CRUD;

import java.util.Date;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Principal {

    private static String senhaadm = "12345";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Date date = new Date();
        Random rand = new Random();
        ClienteController cc = new ClienteController();
        JogoController jc = new JogoController();
        boolean sair = true;

        while (sair == true) {
            boolean i = false;
            boolean verifadm = true;
            System.out.println("Escolha uma opção de cadastro // 1 - ADM // 2 - Cliente");
            int opcao = scanner.nextInt();

            try {
                while (i != true) {

                    // MENU ADMIN
                    if (opcao == 1) {
                        while (verifadm != false) {
                            System.out.println("Insira senha de administrador: ");
                            String senha = scanner.next();

                            if (senha.equals(senhaadm)) {
                                verifadm = false;
                            } else {
                                System.out.println("Senha inserida incorreta !");
                            }
                        }
                        System.out.println("Insira a operação que deseja realizar: " +
                                "\n1 -Visualizar Cadastros // \n" +
                                "2 - Excluir Cadastro // \n" +
                                "3 - Alterar Cadastro // \n" +
                                "4 - Cadastrar Jogos // \n" +
                                "0 - Sair \n");

                        System.out.printf(">>");
                        String escolha = scanner.next();

                        switch (escolha) {

                            case "1":
                                cc.mostrar_cadastros(cc);
                                break;
                            case "2":
                                cc.excluir_usuario(cc, scanner);
                                break;
                            case "3":
                                cc.atualizar_cadastro(scanner, cc);
                                break;
                            case "4":
                                jc.cadastro_jogos(jc, scanner, rand);
                                break;
                            case "0":
                                System.out.println("Menu ADM encerrado... \n");
                                i = true;
                                break;

                            default:
                                System.out.println("Insira uma informação válida! \n");
                                break;
                        }
                        // MENU CLIENTE
                    } else if (opcao == 2) { // else para else if (opcao == 2) ; XUNIL

                        System.out.println("Opções: 1 - Login // 2 - Cadastro");
                        String opcao_cliente = scanner.next();
                        boolean verificacao = false;

                        if (opcao_cliente.equals("1")) {
                            verificacao = cc.verificacao_de_usuario(scanner, cc, verificacao);
                        } else {
                            cc.cadastro(cc, scanner, rand);
                        }

                        while (verificacao != false) {
                            System.out.println("Insira a operação que deseja realizar: " +
                                    "\n1 - Visualização de jogos // \n" +
                                    "2 - Pesquisa jogo por nome // \n" +
                                    "3 - Comprar jogo // \n" +
                                    "0 - Sair // \n");

                            System.out.printf(">>");
                            String opcao_2 = scanner.next();

                            switch (opcao_2) {
                                case "1":
                                    jc.mostrar_jogos(jc);
                                    break;

                                case "2":
                                    jc.pesquisar_jogo_por_nome(scanner, jc);
                                    break;

                                case "3":
                                    jc.retirar_jogo(scanner, jc,date);
                                    break;

                                case "0":
                                    System.out.println("Menu cliente Encerrado... \n");
                                    verificacao = false;
                                    i = true;
                                    break;

                                default:
                                    System.out.println("Insira uma informação válida! \n");
                                    break;
                            }

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
