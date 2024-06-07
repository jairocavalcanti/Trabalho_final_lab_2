package CRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    
    private static final String FILE_NAME = "pessoas.txt";

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

}
