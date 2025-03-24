import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDao;

    public AlunoController(Connection conn) {
        this.alunoDao = new AlunoDAO(conn);
    }

    public void cadastrarAluno(Aluno aluno) {
        try {
            alunoDao.salvar(aluno);
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o aluno: " + e.getMessage());
        }
    }

    public void listarAlunos() {
        try {
            List<Aluno> alunos = alunoDao.buscarTodos();
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno cadastrado.");
            } else {
                for (Aluno aluno : alunos) {
                    System.out.println(aluno);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
    }

    public void atualizarAluno(Aluno aluno) {
        try {
            alunoDao.atualizar(aluno);
            System.out.println("Aluno atualizado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void excluirAluno(String cpf) {
        try {
            alunoDao.excluir(cpf);
            System.out.println("Aluno excluído com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    public Aluno buscarAluno(String cpf) {
        try {
            return alunoDao.buscarPorCpf(cpf);
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
            return null;
        }
    }
}
