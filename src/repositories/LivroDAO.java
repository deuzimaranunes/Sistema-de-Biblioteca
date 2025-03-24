import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection conn;

    public LivroDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Livro livro) throws SQLException {
        String sql = "INSERT INTO livros (titulo, autor, ano_edicao, quantidade_disponivel, corredor, prateleira) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setInt(4, livro.getQuantidadeDisponivel());
            stmt.setString(5, livro.getCorredor());
            stmt.setString(6, livro.getPrateleira());
            stmt.executeUpdate();
        }
    }

    public List<Livro> buscarLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("ano_edicao"),
                        rs.getInt("quantidade_disponivel"),
                        rs.getString("corredor"),
                        rs.getString("prateleira"));
                livros.add(livro);
            }
        }
        return livros;
    }

    public void editar(Livro livro) throws SQLException {
        String sql = "UPDATE livros SET autor = ?, ano_edicao = ?, quantidade_disponivel = ?, corredor = ?, prateleira = ? WHERE titulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, livro.getAutor());
            stmt.setInt(2, livro.getAno());
            stmt.setInt(3, livro.getQuantidadeDisponivel());
            stmt.setString(4, livro.getCorredor());
            stmt.setString(5, livro.getPrateleira());
            stmt.setString(6, livro.getTitulo());
            stmt.executeUpdate();
        }
    }

    public void excluir(String titulo) throws SQLException {
        String sql = "DELETE FROM livros WHERE titulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        }
    }

    public Livro buscar(String titulo) throws SQLException {
        String sql = "SELECT * FROM livros WHERE titulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getString("Titulo"),
                            rs.getString("Autor"),
                            rs.getInt("Ano_edicao"),
                            rs.getInt("Quantidade_disponivel"),
                            rs.getString("Corredor"),
                            rs.getString("Prateleira"));
                }
            }
        }
        return null;
    }

    public Livro localizacao(String titulo) throws SQLException{
        String sql = "SELECT corredor, prateleira FROM livros WHERE titulo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                        rs.getString("Titulo"),
                        rs.getString("Autor"),
                        rs.getInt("Ano_edicao"),
                        rs.getInt("Quantidade_disponivel"),
                        rs.getString("Corredor"),
                        rs.getString("Prateleira"));
                }
            }
        }
        return null;
    }
}
