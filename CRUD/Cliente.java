package CRUD;

public class Cliente {

    private String nome;
    private String endereco;
    private String email;
    private int idade;
    private int ID;

    public Cliente() {

    }

    public Cliente(String nome, String endereco, String email, int idade, int id) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.idade = idade;
        this.ID = id;
    }

    // Getters e setters

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

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public static Cliente fromString(String str) {
        String[] parts = str.split(",");
        return new Cliente(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
    }

    @Override
    public String toString() {
        return nome + "," + endereco + "," + email + "," + idade + "," + ID;
    }

}
