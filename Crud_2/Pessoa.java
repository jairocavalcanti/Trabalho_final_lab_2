package Crud_2;

import java.io.Serializable;

/*
   A implementação da interface Serializable na classe Pessoa é usada para permitir 
   que os objetos dessa classe possam ser convertidos em um fluxo de bytes e, 
   posteriormente, reconstruídos a partir desse fluxo de bytes. 
   Isso é particularmente útil em várias situações, como:

   Persistência:

   Serialização permite salvar o estado de um objeto em um arquivo 
   ou banco de dados e restaurá-lo mais tarde.
   
   -----
   Transmissão de Objetos pela Rede:

   Objetos serializados podem ser enviados através de uma rede, 
   facilitando a comunicação entre diferentes partes de um sistema distribuído.
   ------
  
   Cache:

   Objetos podem ser armazenados em cache de maneira eficiente e recuperados quando necessário.
 */


public class Pessoa implements Serializable {
    
    private String nome;
    private String endereco;
    private String email;
    private int idade;
    private long telefone;

    public Pessoa(String nome, String endereco, String email, int idade, long telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.idade = idade;
        this.telefone = telefone;
    }

    // Getters e setters

    @Override
    public String toString() {
        return nome + "," + endereco + "," + email + "," + idade + "," + telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public static Pessoa fromString(String str) {
        String[] parts = str.split(",");
        return new Pessoa(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Long.parseLong(parts[4]));
    }
}
