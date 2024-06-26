package CRUD;

public class Jogo {

    // Atributos da classe jogo

    private String nome;
    private String genero;
    private int ID;
    private String plataforma;
    private double valor;
    private int quantidade;

    // Construtores da classe jogo

    public Jogo() {

    }

    public Jogo(String nome, String genero, int ID, String plataforma, double valor, int quantidade) {
        this.nome = nome;
        this.genero = genero;
        this.ID = ID;
        this.plataforma = plataforma;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    // Getters e setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nomeDoJogo) {
        this.nome = nomeDoJogo;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPlataforma() {
        return this.plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para transformar uma linha de strings em um objeto do tipo Jogo(),
    // útil para transformar uma linha do arquivo txt contendo os jogos em objeto

    public static Jogo fromString(String str) {
        String[] partes = str.split(",");
        return new Jogo(partes[0],
                partes[1],
                Integer.parseInt(partes[2]),
                partes[3],
                Double.parseDouble(partes[4]),
                Integer.parseInt(partes[5]));
    }

    
    @Override
    public String toString() {
        return nome + "," + genero + "," + ID + "," + plataforma + "," + valor + "," + quantidade;
    }
    
}


