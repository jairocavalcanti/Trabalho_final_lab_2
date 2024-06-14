package CRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static final String FILE_NAME = "pessoas.txt";
    private static final String FILE_NAME_JOGOS = "jogos.txt";

    // METODOS PARA MANIPULAÇÃO DE PESSOAS
    public void addPessoa(Cliente pessoa) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(pessoa.toString());
            writer.newLine();
        }
    }

    public List<Cliente> getPessoas() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clientes.add(Cliente.fromString(line));
            }
        }
        return clientes;
    }

    public void updatePessoa(int id, Cliente updatedPessoa) throws Exception {
        List<Cliente> clientes = getPessoas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Cliente p : clientes) {
                if (p.getID() == id) {
                    writer.write(updatedPessoa.toString());
                } else {
                    writer.write(p.toString());
                }
                writer.newLine();
            }
        }
    }

    public void deletePessoa(int id) throws Exception {
        List<Cliente> clientes = getPessoas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Cliente p : clientes) {
                if (p.getID() != id) {
                    writer.write(p.toString());
                    writer.newLine();
                } else {
                    System.out.println("Cliente excluido com sucesso!");
                }
            }
        }
    }

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

    /*
     * public List<Jogo> comprar_jogo(String nome) throws Exception {
     * List<Jogo> jogos = new ArrayList<>();
     * try (BufferedReader reader = new BufferedReader(new
     * FileReader(FILE_NAME_JOGOS))) {
     * String line;
     * int count = 0;
     * while ((line = reader.readLine()) != null) {
     * count++;
     * Jogo jogo = Jogo.fromString(line);
     * // int diminuidor = 1;
     * if (count == Integer.parseInt(nome)) {
     * jogo.setQuantidade(jogo.getQuantidade() - 1);
     * System.out.println("Agora restam apenas " + jogo.getQuantidade() +
     * " copias");
     * }
     * }
     * 
     * }
     * 
     * return jogos;
     * }
     */

}
