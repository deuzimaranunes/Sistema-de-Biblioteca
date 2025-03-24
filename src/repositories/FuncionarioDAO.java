import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Connection conn;

    public FuncionarioDAO(Connection conn) {
        this.conn = conn;
    }

    public void salvar(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO funcionarios (nome, idade, cpf, email, senha, administrador) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getIdade());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getSenha());
            stmt.setBoolean(6, funcionario.getAdministrador());
            stmt.executeUpdate();
        }
    }

    public List<Funcionario> buscarTodos() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        rs.getBoolean("administrador"));
                funcionarios.add(funcionario);
            }
        }
        return funcionarios;
    }

    public Funcionario buscarPorCpf(String cpf) throws SQLException {
        String sql = "SELECT * FROM funcionarios WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Funcionario(
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("cpf"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getBoolean("administrador"));
                }
            }
        }
        return null;
    }

    public void atualizar(Funcionario funcionario) throws SQLException {
        String sql = "UPDATE funcionarios SET nome = ?, idade = ?, email = ?, senha = ?, administrador = ? WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setInt(2, funcionario.getIdade());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getSenha());
            stmt.setBoolean(5, funcionario.getAdministrador());
            stmt.setString(6, funcionario.getCpf());
            stmt.executeUpdate();
        }
    }

    public void excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE cpf = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        }
    }
}
