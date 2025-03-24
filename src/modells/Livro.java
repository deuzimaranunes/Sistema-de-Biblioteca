public class Livro {
    private String titulo;
    private String autor;
    private int ano;
    private int quantidadeDisponivel;
    private String corredor;
    private String prateleira;
    
    public Livro(String titulo, String autor, int ano, int quantidadeDisponivel, String corredor, String prateleira) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.corredor = corredor;
        this.prateleira = prateleira;
    }

    public Livro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getCorredor() {
        return corredor;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public String getPrateleira() {
        return prateleira;
    }

    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }

    @Override
    public String toString() {
        return "\nLivro - Titulo: " + titulo + "\nAutor: " + autor + "\nAno: " + ano + "\nQuantidade Disponivel: "
                + quantidadeDisponivel + "\nCorredor: " + corredor + "\nPrateleira: " + prateleira;
    }
}
