import java.sql.Connection;
import java.sql.SQLException;

public class SistemaDeBiblioteca {
    public static void main(String[] args) {
        System.out.println(" Sistema de Biblioteca Iniciado");

        Connection conn = null;
        try {
            conn = ConexaoDB.conectar(); 
            System.out.println("Conexão com o banco de dados estabelecida.");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            System.exit(1);
        }

        AlunoController alunoController = new AlunoController(conn);
        FuncionarioController funcionarioController = new FuncionarioController(conn);
        LivroController livroController = new LivroController(conn);
        EmprestimoController emprestimoController = new EmprestimoController(conn);

        AlunoView alunoView = new AlunoView(alunoController);
        FuncionarioView funcionarioView = new FuncionarioView(funcionarioController);
        LivroView livroView = new LivroView(livroController);
        EmprestimoView emprestimoView = new EmprestimoView(emprestimoController);

        MenuView menuView = new MenuView(alunoView, funcionarioView, livroView, emprestimoView);
        menuView.exibirMenu();

        System.out.println("Sistema de Biblioteca Finalizado.");
    }
}
