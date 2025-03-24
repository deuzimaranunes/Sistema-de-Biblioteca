import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {
    private Connection conn;

    public EmprestimoDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Emprestimo emprestimo) throws SQLException {
        String sql = "INSERT INTO emprestimo (data_emprestimo, data_devolucao, matricula, titulo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, emprestimo.getDataEmprestimo());
            stmt.setDate(2, emprestimo.getDataDevolucao());
            stmt.setString(3, emprestimo.getAluno().getMatricula());
            stmt.setString(4, emprestimo.getLivro().getTitulo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> listarTodos() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT e.data_emprestimo, e.data_devolucao, " +
                    "a.matricula AS aluno_matricula, a.nome, a.idade, a.cpf, a.email, a.senha, a.curso, " + 
                    "l.titulo AS livro_titulo, l.autor, l.ano_edicao, l.quantidade_disponivel, l.corredor, l.prateleira " +
                    "FROM emprestimo e " +
                    "JOIN aluno a ON e.matricula = a.matricula " +
                    "JOIN livros l ON e.titulo = l.titulo";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getString("aluno_matricula"),
                        rs.getString("curso"));
                Livro livro = new Livro(rs.getString("livro_titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_edicao"),
                        rs.getInt("quantidade_disponivel"),
                        rs.getString("corredor"),
                        rs.getString("prateleira"));
                Emprestimo emprestimo = new Emprestimo(rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao"),aluno, livro);
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

    public void atualizar(Emprestimo emprestimo) throws SQLException {
        String sql = "UPDATE emprestimo SET data_emprestimo = ?, data_devolucao = ? WHERE titulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, emprestimo.getDataEmprestimo());
            stmt.setDate(2, emprestimo.getDataDevolucao());
            stmt.setString(3,emprestimo.getLivro().getTitulo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void devolver(String titulo) throws SQLException {
        String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE titulo = ? AND data_devolucao IS NULL";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));  // Data atual
            stmt.setString(2, titulo);
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Erro: o livro já foi devolvido ou o empréstimo não foi encontrado.");
            }
        }
    }
    
     
    public Emprestimo buscarPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT e.data_emprestimo, e.data_devolucao, " +
                    "a.matricula AS aluno_matricula, a.nome, a.idade, a.cpf, a.email, a.senha, a.curso, " + 
                    "l.titulo AS livro_titulo, l.autor, l.ano_edicao, l.quantidade_disponivel, l.corredor, l.prateleira " +
                    "FROM emprestimo e " +
                    "JOIN aluno a ON e.matricula = a.matricula " +
                    "JOIN livros l ON e.titulo = l.titulo " +
                    "WHERE l.titulo = ?";
    
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Aluno aluno = new Aluno(rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getString("aluno_matricula"),
                            rs.getString("curso"));
                    Livro livro = new Livro(rs.getString("livro_titulo"),
                            rs.getString("autor"),
                            rs.getInt("ano_edicao"),
                            rs.getInt("quantidade_disponivel"),
                            rs.getString("corredor"),
                            rs.getString("prateleira"));
    
                    return new Emprestimo(rs.getDate("data_emprestimo"),
                            rs.getDate("data_devolucao"), aluno, livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    

    public List<Emprestimo> listarAtrasados() throws SQLException {
        List<Emprestimo> emprestimosAtrasados = new ArrayList<>();
        String sql = "SELECT e.data_emprestimo, e.data_devolucao, " +
                    "a.matricula AS aluno_matricula, a.nome, a.idade, a.cpf, a.email, a.senha, a.curso, " + 
                    "l.titulo AS livro_titulo, l.autor, l.ano_edicao, l.quantidade_disponivel, l.corredor, l.prateleira " +
                    "FROM emprestimo e " +
                    "JOIN aluno a ON e.matricula = a.matricula " +
                    "JOIN livros l ON e.titulo = l.titulo " +
                    "WHERE e.data_devolucao < CURRENT_DATE";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Aluno aluno = new Aluno(rs.getString("nome"), rs.getInt("idade"), rs.getString("cpf"), rs.getString("email"), rs.getString("senha"), rs.getString("aluno_matricula"), rs.getString("curso"));
                Livro livro = new Livro(rs.getString("livro_titulo"), rs.getString("autor"), rs.getInt("ano_edicao"), rs.getInt("quantidade_disponivel"), rs.getString("corredor"), rs.getString("prateleira"));
                Emprestimo emprestimo = new Emprestimo(rs.getDate("data_emprestimo"), rs.getDate("data_devolucao"), aluno, livro);
                emprestimosAtrasados.add(emprestimo);
            }
        }
        return emprestimosAtrasados;
    }

    public Connection getConn() {
        return conn;
    }
}
