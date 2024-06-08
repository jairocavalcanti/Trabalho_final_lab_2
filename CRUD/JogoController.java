package CRUD;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class JogoController {

    private static final String FILE_NAME = "jogos.txt";

    public void addPessoa(Jogo game) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(game.toString());
            writer.newLine();
        }
    }

    public List<Jogo> getPessoas() throws IOException {
        List<Jogo> jogos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jogos.add(Jogo.fromString(line));
            }
        }
        return jogos;
    }
/* 
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
                }
            }
        }
    }
*/
}
