package CRUD;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.List;

public class ClienteController {

    private static final String FILE_NAME = "pessoas.txt";
    private Cliente usuario;

    public String GetUsuario(){
        return this.usuario.getNome();
    }

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
        System.out.printf("Insira o nome do login:");
        String nome_1 = scanner.next();

        System.out.printf("Insira a senha: ");
        String senha_1 = scanner.next();

        List<Cliente> clientes_1 = cc.getPessoas();
        for (Cliente p : clientes_1) {
            if (p.getNome() != null && p.getNome().equals(nome_1) && p.getSenha() != null
                    && p.getSenha().equals(senha_1)) {
                verif = true;
                System.out.println("Nome verificado nos cadastros com sucesso!");
                this.usuario = p;
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

        System.out.println("------------------------------- \n");

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

        int indice = 0;
        for (Cliente c : clientes) {
            if (c.getID() == id) {

                System.out.println("Nome associado ao ID: " + c.getNome());

                String nome = " ";

                System.out.printf("1 - Insira o novo nome // 2 - Manter nome ");
                String escolha_1 = scanner.next();
                if (escolha_1.equals("2")) {
                    nome = c.getNome();
                } else {
                    System.out.printf("Novo nome: ");
                    nome = scanner.next();
                }

                String senha = " ";

                System.out.printf("1 - Insira a nova senha // 2 - Manter senha ");
                String escolha_2 = scanner.next();
                if (escolha_2.equals("2")) {
                    senha = c.getSenha();
                } else {
                    System.out.printf("Nova senha: ");
                    senha = scanner.next();
                }

                String endereco = " ";

                System.out.printf("1 - Insira o novo endereço // 2 - Manter endereço ");
                String escolha_3 = scanner.next();
                if (escolha_3.equals("2")) {
                    endereco = c.getEndereco();
                } else {
                    System.out.printf("Novo endereço: ");
                    endereco = scanner.next();
                }

                String email = " ";

                System.out.printf("1 - Insira o novo email // 2 - Manter email ");
                String escolha_4 = scanner.next();
                if (escolha_4.equals("2")) {
                    email = c.getEmail();
                } else {
                    System.out.printf("Novo email: ");
                    email = scanner.next();
                }

                int idade = 0;
                System.out.printf("1 - Insira a nova idade // 2 - Manter idade ");
                String escolha_5 = scanner.next();
                if (escolha_5.equals("2")) {
                    idade = c.getIdade();
                } else {
                    System.out.printf("Nova idade: ");
                    idade = scanner.nextInt();
                }

                Cliente updatedCliente = new Cliente(nome, endereco, email, senha, idade, id);

                updatePessoa(id, updatedCliente);

                System.out.println("--------------------------------- \n");

                break;

            } else {
                indice++;
                System.out.println("ID não existente no cadastro " + indice + " !");

                System.out.println("--------------------------------- \n");
            }
        }

    }

}
