import java.sql.Date;

public class Emprestimo {
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Aluno aluno;
    private Livro livro;

    public Emprestimo() {}

    public Emprestimo(Date dataEmprestimo, Date dataDevolucao, Aluno aluno, Livro livro) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.aluno = aluno;
        this.livro = livro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    @Override
    public String toString() {
        return "\nEmpréstimo - " + "\nAluno: " + aluno.getNome() + "\nLivro: " + livro.getTitulo() +
               "\nData Empréstimo: " + dataEmprestimo + "\nData Devolução: " + dataDevolucao;
    }
}
