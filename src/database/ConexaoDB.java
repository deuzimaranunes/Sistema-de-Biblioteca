import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres123";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");  
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: Driver do PostgreSQL não encontrado.");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
