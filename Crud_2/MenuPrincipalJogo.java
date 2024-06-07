package Crud_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuPrincipalJogo {
    
    public static final String ARQUIVO_JOGOS = "jogos.txt";
    private static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args){

        int opcao;

        do {
            
            System.out.println();
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Adicionar Jogo");
            System.out.println("2. Listar Jogos");
            System.out.println("3. Atualizar Jogo");
            System.out.println("4. Remover Jogo");
            System.out.println("5. Sair");
            System.out.println("Escolha uma opção: ");
            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {

                case 1:
                    adicionarJogo();
                    break;
                
                case 2:
                    listarJogos();
                    break;

                case 3:
                    atualizarJogo();
                    break;

                case 4:
                    removerJogo();
                    break;
                    
                case 5:
                    break;

                default: 
                    System.out.println("Opção inválida");
            }

        }
        while(opcao != 5);

    }

    //Métodos do menu principal


    //Método para adicionar um jogo
    private static void adicionarJogo(){

        System.out.println("-------------------------");
        System.out.println("Digite o nome do jogo: ");
        String nomeDoJogo = entrada.nextLine();
        System.out.println("Digite o genero do jogo: ");
        String genero = entrada.nextLine();
        System.out.println("Digite o codigo do jogo: ");
        int codigoDoJogo = Integer.parseInt(entrada.nextLine());
        System.out.println("Digite a plataforma que o jogo roda: ");
        String plataforma = entrada.nextLine();

        Jogo jogo = new Jogo(nomeDoJogo, genero, codigoDoJogo, plataforma);

        try(BufferedWriter escrever = new BufferedWriter(new FileWriter(ARQUIVO_JOGOS, true));
        ){
            escrever.write(jogo.toString());
            escrever.newLine();

        }
        catch(IOException excecao){
            System.out.println("Não foi possivel escrever no arquivo: " + excecao);
        }

        System.out.println();
        System.out.println("Jogo adicionado com sucesso!");
    }



    //Método para listar os jogos presentes no arquivo
    private static void listarJogos() {

        try(BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO_JOGOS));) {

            String linha;

            System.out.println();
            while((linha = leitor.readLine()) != null) {
                Jogo jogo = Jogo.fromString(linha);
                System.out.println("Jogo: " + jogo.getNome() + " - " + jogo.getGenero() + " - " + jogo.getCodigo() + " - " + jogo.getPlataforma());
            }

        }
        catch(Exception excecao) {

            System.out.println("Não foi possível listar os jogos: " + excecao);
        }

    }



    //Método para atualizar um jogo
    private static void atualizarJogo() {

        listarJogos();

        System.out.println();

        ArrayList<Jogo> jogos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO_JOGOS))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                Jogo jogo = Jogo.fromString(linha);
                jogos.add(jogo);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.print("Digite o código do jogo a ser atualizado: ");
        int codigo = Integer.parseInt(entrada.nextLine());

        boolean encontrado = false;
        for (Jogo jogo : jogos) {
            if (jogo.getCodigo() == codigo) {
                System.out.print("Digite o novo nome do jogo: ");
                jogo.setNome(entrada.nextLine());
                System.out.print("Digite o novo gênero do jogo: ");
                jogo.setGenero(entrada.nextLine());
                System.out.print("Digite a nova plataforma do jogo: ");
                jogo.setPlataforma(entrada.nextLine());
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO_JOGOS))) {
                for (Jogo jogo : jogos) {
                    escritor.write(jogo.toString());
                    escritor.newLine();
                }
                System.out.println("Jogo atualizado com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }


    //Método para remover um jogo
    private static void removerJogo() {

        listarJogos();

        System.out.println();

        ArrayList<Jogo> jogos = new ArrayList<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(ARQUIVO_JOGOS))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {
                Jogo jogo = Jogo.fromString(linha);
                jogos.add(jogo);
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        System.out.print("Digite o código do jogo a ser removido: ");
        int codigo = Integer.parseInt(entrada.nextLine());

        boolean encontrado = false;
        for (int i = 0; i < jogos.size(); i++) {
            if (jogos.get(i).getCodigo() == codigo) {
                jogos.remove(i);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ARQUIVO_JOGOS))) {
                for (Jogo jogo : jogos) {
                    escritor.write(jogo.toString());
                    escritor.newLine();
                }
                System.out.println("Jogo removido com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }
    
}
