import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class EmprestimoController {
    private EmprestimoDAO emprestimoDAO;
    private LivroDAO livroDAO;

    public EmprestimoController(Connection conn) {
        this.emprestimoDAO = new EmprestimoDAO(conn);
        this.livroDAO = new LivroDAO(conn);
    }
    
    public Livro buscarLivroPorTitulo(String titulo) {
        try {
            return livroDAO.buscar(titulo);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
            return null;
        }
    }

    public boolean criarEmprestimo(Date dataEmprestimo, Date dataDevolucao, Aluno aluno, Livro livro) {
        try {
            if (livro.getQuantidadeDisponivel() > 0) {
                Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucao, aluno, livro);
                emprestimoDAO.salvar(emprestimo);
                livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
                livroDAO.editar(livro);
                return true;
            } else {
                System.out.println("Livro não disponível para empréstimo.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar empréstimo: " + e.getMessage());
            return false;
        }
    }

    public void listarEmprestimos() {
        try {
            List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
            if (emprestimos.isEmpty()) {
                System.out.println("Nenhum empréstimo encontrado.");
            } else {
                for (Emprestimo emprestimo : emprestimos) {
                    System.out.println(emprestimo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos: " + e.getMessage());
        }
    }

    public void atualizarEmprestimo(String titulo, Date novaDataDevolucao) {
        try {
            Emprestimo emprestimo = emprestimoDAO.buscarPorTitulo(titulo);
            if (emprestimo != null) {
                emprestimo.setDataDevolucao(novaDataDevolucao);
                emprestimoDAO.atualizar(emprestimo);
                System.out.println("Empréstimo atualizado com sucesso!");
            } else {
                System.out.println("Empréstimo não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar empréstimo: " + e.getMessage());
        }
    }

    public boolean devolver(String titulo) {
        try {
            emprestimoDAO.devolver(titulo);
            return true;  
        } catch (SQLException e) {
            System.out.println("Erro ao devolver livro: " + e.getMessage());
            return false; 
        }
    }
     

    public Emprestimo buscarEmprestimo(String titulo) {
        try {
            return emprestimoDAO.buscarPorTitulo(titulo);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empréstimo: " + e.getMessage());
            return null;
        }
    }

    public void listarEmprestimosAtrasados() {
        try {
            List<Emprestimo> atrasados = emprestimoDAO.listarAtrasados();
            if (atrasados.isEmpty()) {
                System.out.println("Nenhum empréstimo atrasado encontrado.");
            } else {
                for (Emprestimo emprestimo : atrasados) {
                    System.out.println(emprestimo);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar empréstimos atrasados: " + e.getMessage());
        }
    }
}

