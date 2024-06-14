package CRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    
    private static final String FILE_NAME = "pessoas.txt";
    private static final String FILE_NAME_JOGOS = "jogos.txt";


    public void addPessoa(Cliente pessoa) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(pessoa.toString());
            writer.newLine();
        }
    }

    public List<Cliente> getPessoas() throws IOException {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clientes.add(Cliente.fromString(line));
            }
        }
        return clientes;
    }

/* 
    public void checarCadastro(int id, String email ) throws FileNotFoundException, IOException{
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clientes.add(Cliente.fromString(line));
            }
        }
    }
*/
    public void updatePessoa(int id, Cliente updatedPessoa) throws IOException {
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

    public void deletePessoa(int id) throws IOException {
        List<Cliente> clientes = getPessoas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Cliente p : clientes) {
                if (p.getID() != id) {
                    writer.write(p.toString());
                    writer.newLine();
                }else{
                    System.out.println("Cliente excluido com sucesso!");
                }
            }
        }
    }

  /*   public void addjogo_no_estoque(Jogo es) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(es.toString());
            writer.newLine();
        }
    }*/
    
    
    public List<Jogo> getJogos() throws IOException {
        List<Jogo> jogos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_JOGOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jogos.add(Jogo.fromString(line));
            }
        }
        return jogos;
    }

    public List<Jogo> getJogo_por_nome(String nome) throws IOException {
        List<Jogo> jogos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_JOGOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Jogo jogo = Jogo.fromString(line);
                if(jogo.getNome().equals(nome)){
                    jogos.add(jogo);
                    System.out.println(jogo.getNome());
                    break;
                }
            }
        }
        return jogos;
    }

}
