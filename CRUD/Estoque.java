package CRUD;

public class Estoque extends Jogo {

    // Atributos de classe estoque
    private int quantEstoque;
    private int subID;
    private int status;
   
    // Contrutores de classe estoque
    public Estoque(String nome, String genero, int codigo, String plataforma, double valor, int quantEstoque, int subID,
            int status) {
        super(nome, genero, codigo, plataforma, valor);
        this.quantEstoque = quantEstoque;
        this.subID = subID;
        this.status = status;
    }

    public Estoque(int quantEstoque, int subID, int status) {
        this.quantEstoque = quantEstoque;
        this.subID = subID;
        this.status = status;
    }

    public Estoque() {

    };

    // Getters e Setters
    public int getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(int quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    public int getSubID() {
        return subID;
    }

    public void setSubID(int subID) {
        this.subID = subID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Estoque [quantEstoque=" + quantEstoque + ", subID=" + subID + ", status=" + status + "]";
    }

}
