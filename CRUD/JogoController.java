package CRUD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JogoController {

    private static final String FILE_NAME_JOGOS = "jogos.txt";
    private static final String HISTORICO = "Historico.txt";

    // METODOS PARA MANIPULAÇAO DE JOGOS

    public void addJogo(Jogo jogo) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_JOGOS, true))) {
            writer.write(jogo.toString());
            writer.newLine();
        }
    }

    public List<Jogo> getJogos() throws Exception {
        List<Jogo> jogos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_JOGOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jogos.add(Jogo.fromString(line));
            }
        }
        return jogos;
    }

    public List<Jogo> getJogo_por_nome(String nome) throws Exception {
        List<Jogo> jogos = new ArrayList<>();
        boolean jogoencontrado = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_JOGOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Jogo jogo = Jogo.fromString(line);
                if (jogo.getNome().equals(nome)) {
                    System.out.println("\n -- JOGO DISPONIVEL ! -- ");
                    jogos.add(jogo);
                    jogoencontrado = true;
                    break;
                }
            }
        }

        if (!jogoencontrado) {
            System.out.println("Jogo não encontrado nos arquivos !");
        }

        return jogos;
    }

    private void registrarCompra(String nomeUsuario, Jogo jogo , double totalapagar , Date date) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORICO, true))) {
            writer.write(
            "Usuario: " + nomeUsuario + 
            ", Jogo: " + jogo.getNome() + 
            ", Total a pagar: R$" + totalapagar +
            ", Data e Hora: " + dateFormat.format(date));
            writer.newLine();
        }
    }
    
    public List<Jogo> comprar_jogo(String posicao , String nomedeusuario, Date date) throws Exception {
        List<Jogo> jogos = new ArrayList<>();
        int posicaoInt = Integer.parseInt(posicao);

        // Lê o arquivo e armazena os jogos na lista
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_JOGOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Jogo jogo = Jogo.fromString(line);
                jogos.add(jogo);
            }
        }

        // Atualiza a quantidade do jogo especificado pela posição
        Jogo jogo = jogos.get(posicaoInt - 1); // Subtrai 1 porque a posição do usuário é baseada em 1, não em 0
        

        if (jogo.getQuantidade() > 0) {
            jogo.setQuantidade(jogo.getQuantidade() - 1);
            double totalapagar = jogo.getValor();
            System.out.println(
                "Agora restam apenas " + jogo.getQuantidade() + " cópias do jogo " + jogo.getNome() + "\n");
        
                registrarCompra(nomedeusuario ,jogo, totalapagar,date);
        } else {
            System.out.println("Cópias nao disponiveis !! \n");
        }
        // Reescreve o arquivo com as atualizações
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_JOGOS))) {
            for (Jogo j : jogos) {
                writer.write(j.toString());
                writer.newLine();
            }
        }

        return jogos;
    }

    public void mostrar_jogos(JogoController jc) throws Exception {
        List<Jogo> jogos = jc.getJogos(); // sugestão: gameManager.getJogos()
        System.out.println("\n -- JOGOS DISPONIVEIS -- ");
        int cont = 0;
        for (Jogo j : jogos) { // sugestão: Jogo jogo : jogos
            cont++;
            // System.out.println("# -" + (cont) + " " + j.getNome() + j.getGenero() +
            // j.getQuantidade());
            System.out.printf("# - " + cont++ + "," + j + "\n");
        }
        System.out.println("\n ------------------------------- \n");
    }

    public void pesquisar_jogo_por_nome(Scanner scanner, JogoController jc) throws Exception {
        System.out.println("Insira o nome do jogo que deseja achar: ");
        String nome = scanner.next();

        List<Jogo> jogos_1 = jc.getJogo_por_nome(nome);
        int cont_1 = 0;
        for (Jogo j : jogos_1) {
            cont_1++;
            System.out.println("# -" + (cont_1) + " " + j);
        }
        System.out.println("---------------------------------- \n");
    }

    public void retirar_jogo(String nome_de_usuario ,Scanner scanner, JogoController jc, Date date) throws Exception {
        System.out.println("-- MENU DE COMPRAS --");

        List<Jogo> jogos_2 = jc.getJogos();
        System.out.println("\n -- JOGOS DISPONIVEIS -- ");
        int cont_2 = 0;
        for (Jogo j : jogos_2) {
            cont_2++;
            // System.out.println("# -" + (cont_2) + " " + j);
            System.out.printf("# -" + (cont_2) + " " + j + "\n");
        }
        System.out.println("\n ------------------------------- \n");

        System.out.println("Insira a quantidade de jogos que deseja comprar: ");
        int quantidade = scanner.nextInt();
        
        for(int i = 0; i< quantidade; i++){
        System.out.println("Selecione uma opcao: ");
        String opcao_jogo = scanner.next();

        jc.comprar_jogo(opcao_jogo , nome_de_usuario, date);
        }

    }

    public void cadastro_jogos(JogoController jc, Scanner scanner, Random rand) throws Exception {

        System.out.printf("Insira o nome do jogo: ");
        String nome = scanner.next();

        System.out.printf("Insira o genero do jogo: ");
        String genero = scanner.next();

        int id = rand.nextInt(1000);

        System.out.printf("Insira a plataforma do jogo: ");
        String plataforma = scanner.next();

        System.out.printf("Insira o valor do jogo: ");
        double valor = scanner.nextDouble();

        System.out.printf("Defina a quantidade de cópias: ");
        int quantidade = scanner.nextInt();

        Jogo jogo = new Jogo(nome, genero, id, plataforma, valor, quantidade);

        jc.addJogo(jogo);

        System.out.println("Jogo Cadastrado com sucesso !");

        System.out.println("------------------------------- \n");

    }

}
