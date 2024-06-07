package Crud_2;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaManager {
    private static final String FILE_NAME = "jogos.txt";

    public void addPessoa(Pessoa pessoa) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(pessoa.toString());
            writer.newLine();
        }
    }

    public List<Pessoa> getPessoas() throws IOException {
        List<Pessoa> pessoas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pessoas.add(Pessoa.fromString(line));
            }
        }
        return pessoas;
    }

    public void updatePessoa(String email, Pessoa updatedPessoa) throws IOException {
        List<Pessoa> pessoas = getPessoas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pessoa p : pessoas) {
                if (p.getEmail().equals(email)) {
                    writer.write(updatedPessoa.toString());
                } else {
                    writer.write(p.toString());
                }
                writer.newLine();
            }
        }
    }

    public void deletePessoa(String email) throws IOException {
        List<Pessoa> pessoas = getPessoas();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pessoa p : pessoas) {
                if (!p.getEmail().equals(email)) {
                    writer.write(p.toString());
                    writer.newLine();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        PessoaManager pm = new PessoaManager();

        try {

            while (true) {
                System.out.println("Insira a operação que deseja realizar: " +
                        "\n1 - Cadastro // \n" +
                        "2 - Visualizar Cadastros // \n" +
                        "3 - Alterar Cadastro // \n" +
                        "4 - Excluir Cadastro // \n");
                System.out.printf(">>");
                String escolha = scanner.next();

                switch (escolha) {
                    case "1":

                        System.out.printf("Insira o nome: ");
                        String nome = scanner.next();

                        System.out.printf("Insira o endereço: ");
                        String endereço = scanner.next();

                        System.out.printf("Insira o email: ");
                        String email = scanner.next();

                        System.out.printf("Insira a idade: ");
                        int idade = scanner.nextInt();

                        System.out.printf("Insira o telefone: ");
                        long telefone = scanner.nextLong();

                        pm.addPessoa(new Pessoa(nome, endereço, email, idade, telefone));

                        break;

                    case "2":

                        List<Pessoa> pessoas = pm.getPessoas();
                        for (Pessoa p : pessoas) {
                            System.out.println(p);
                        }
                        break;

                    case "0":
                        System.out.println("Programa encerrado...");
                        scanner.close();
                        return;

                    default:

                        System.out.println("Insira uma informação válida! ");

                        break;
                }

            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
