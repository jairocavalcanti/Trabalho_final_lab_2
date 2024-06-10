package CRUD;

public class Jogo {

    // Atributos da classe jogo

    private String nome;
    private String genero;
    private int codigo;
    private String plataforma;
    private double valor;

    // Construtores da classe jogo

    public Jogo(String nome, String genero, int codigo, String plataforma, double valor) {
        this.nome = nome;
        this.genero = genero;
        this.codigo = codigo;
        this.plataforma = plataforma;
        this.valor = valor;
    }

    public Jogo() {

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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /* 
    // Método para transformar uma linha de strings em um objeto do tipo Jogo(),
    // útil para transformar uma linha do arquivo txt contendo os jogos em objeto

    public static Jogo fromString(String str) {
        String[] partes = str.split("-");
        return new Jogo(partes[0], partes[1], Integer.parseInt(partes[2]), partes[3], Double.parseDouble(partes[4]));
    }

    @Override
    public String toString() {
        return nome + "-" + genero + "-" + codigo + "-" + plataforma + "-" + valor;
    }
    */
}
