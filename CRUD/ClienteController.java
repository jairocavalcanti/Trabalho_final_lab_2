package CRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class ClienteController {

    private static final String FILE_NAME = "pessoas.txt";

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

    public boolean verificacao_de_usuario(Scanner scanner, ClienteController cc, boolean verif) throws Exception {
        System.out.println("Insira o nome do login:");
        String nome_1 = scanner.next();

        System.out.println("Insira a senha: ");
        String senha_1 = scanner.next();

        List<Cliente> clientes_1 = cc.getPessoas();
        for (Cliente p : clientes_1) {
            if (p.getNome() != null && p.getNome().equals(nome_1) && p.getSenha() != null
                    && p.getSenha().equals(senha_1)) {
                verif = true;
                System.out.println("Nome verificado nos cadastros com sucesso!");
                break;
            } else {
                verif = false;
            }
        }
        System.out.println("------------------------------- \n");
        return verif;
    }

    public void cadastro(ClienteController cc, Scanner scanner, Random rand) throws Exception {

        System.out.printf("Insira seu nome: ");
        String nome = scanner.next();

        System.out.printf("Insira sua senha: ");
        String senha = scanner.next();

        System.out.printf("Insira seu endereço: ");
        String endereco = scanner.next();

        System.out.printf("Insira seu email: ");
        String gmail = scanner.next();

        System.out.printf("Insira sua idade: ");
        int idade = scanner.nextInt();

        int ID = rand.nextInt(1000);

        Cliente cliente = new Cliente(nome, endereco, gmail, senha, idade, ID);

        cc.addPessoa(cliente);

    }

    public void mostrar_cadastros(ClienteController cc) throws Exception {
        List<Cliente> clientes = cc.getPessoas(); // sugestão: criar um clientManager ou
        // client_manager e método de getPessoas para
        // getClients() ou get_clients()
        System.out.println("\n-- CADASTROS EXISTENTES --");
        int cont = 0;
        for (Cliente cliente : clientes) {
            cont++;
            System.out.println("# -" + (cont) + " " + cliente);
        }
        System.out.println("--------------------------------- \n");
    }

    public void excluir_usuario(ClienteController cc, Scanner scanner) throws Exception {
        System.out.println("Insira o id da pessoa que deseja excluir: ");
        int id_cliente = scanner.nextInt(); // sugestão: id, client_id, clientID ou client_ID
        cc.deletePessoa(id_cliente); // sugestão: clientManager.delClient()
    }

    public void atualizar_cadastro(Scanner scanner, ClienteController cc) throws Exception {
        List<Cliente> clientes = getPessoas();

        System.out.printf("Insira o ID do cliente que deseja atualizar: ");
        int id = scanner.nextInt();

        for (Cliente c : clientes) {
            if (c.getID() == id) {


                System.out.println("ID existente no sistema!");
                System.out.println("Nome associado ao ID: " + c.getNome());

                /*
                 * System.out.printf("Insira o novo nome: ");
                 * String nome = scanner.next();
                 * 
                 * System.out.printf("Insira a nova senha: ");
                 * String senha = scanner.next();
                 * 
                 * System.out.printf("Insira o novo endereço: ");
                 * String endereco = scanner.next();
                 * 
                 * System.out.printf("Insira o novo email: ");
                 * String gmail = scanner.next();
                 * 
                 * System.out.printf("Insira a nova idade: ");
                 * int idade = scanner.nextInt();
                 * 
                 * Cliente updatedCliente = new Cliente(nome, endereco, gmail, senha, idade,
                 * id);
                 * 
                 * updatePessoa(id, updatedCliente);
                 */
            } else {
                System.out.println("ID nao existente no sistema!");
            }
        }

    }

}
