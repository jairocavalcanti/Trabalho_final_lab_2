package CRUD;

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

public class Jogo implements Serializable {

    // Atributos da classe jogo

    private String nome;
    private String genero;
    private int codigo;
    private String plataforma;

    // Construtores da classe jogo

    public Jogo(String nome, String genero, int codigo, String plataforma) {
        this.nome = nome;
        this.genero = genero;
        this.codigo = codigo;
        this.plataforma = plataforma;
    }

    public Jogo(){
        
    }

    // Getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeDoJogo) {
        this.nome = nomeDoJogo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigoDoJogo) {
        this.codigo = codigoDoJogo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    // Método para transformar uma linha de strings em um objeto do tipo Jogo(),
    // útil para transformar uma linha do arquivo txt contendo os jogos em objeto

    public static Jogo fromString(String str) {
        String[] partes = str.split("-");
        return new Jogo(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
    }

    @Override
    public String toString() {
        return nome + "-" + genero + "-" + codigo + "-" + plataforma + "-";
    }

}
