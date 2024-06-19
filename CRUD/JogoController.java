package CRUD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoController {

    private static final String FILE_NAME_JOGOS = "jogos.txt";

    // METODOS PARA MANIPULAÇAO DE JOGOS
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

    public List<Jogo> comprar_jogo(String posicao) throws Exception {
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
            System.out.println("Agora restam apenas " + jogo.getQuantidade() + " cópias do jogo " + jogo.getNome());
        } else {
            System.out.println("Cópias nao disponiveis !!");
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
            System.out.printf("# - " + cont++ + "," + j);
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

    public void retirar_jogo(Scanner scanner , JogoController jc) throws Exception{
        System.out.println("-- MENU DE COMPRAS --");

        List<Jogo> jogos_2 = jc.getJogos();
        System.out.println("\n -- JOGOS DISPONIVEIS -- ");
        int cont_2 = 0;
        for (Jogo j : jogos_2) {
            cont_2++;
            // System.out.println("# -" + (cont_2) + " " + j);
            System.out.printf("# -" + (cont_2) + " " + j);
        }
        System.out.println("\n ------------------------------- \n");

        System.out.println("Selecione uma opcao: ");
        String nome_1 = scanner.next();

        jc.comprar_jogo(nome_1);

    }

}
