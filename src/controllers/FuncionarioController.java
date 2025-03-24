import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    private FuncionarioDAO funcDao;

    public FuncionarioController(Connection conn) {
        this.funcDao = new FuncionarioDAO(conn);
    }
    
    public void cadastrarFuncionario(Funcionario funcionario){
        try {
            funcDao.salvar(funcionario);
            System.out.println("Funcionario cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o funcionario: " + e.getMessage());
        }
    }

    public void listarFuncionarios() {
        try {
            List<Funcionario> funcionarios = funcDao.buscarTodos();
            if (funcionarios.isEmpty()) {
                System.out.println("Nenhum funcionario cadastrado.");
            } else {
                for (Funcionario func : funcionarios) {
                    System.out.println(func);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar funcionarios: " + e.getMessage());
        }
    }

    public void atualizarFuncionario(Funcionario funcionario) {
        try {
            funcDao.atualizar(funcionario);
            System.out.println("Funcionario atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar funcionario: " + e.getMessage());
        }
    }

    public void excluirFuncionario(String cpf) {
        try {
            funcDao.excluir(cpf);
            System.out.println("Funcionario excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionario: " + e.getMessage());
        }
    }

    public Funcionario buscarFuncionario(String cpf) {
        try {
            return funcDao.buscarPorCpf(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar funcionario: " + e.getMessage());
            return null;
        }
    }
}           
