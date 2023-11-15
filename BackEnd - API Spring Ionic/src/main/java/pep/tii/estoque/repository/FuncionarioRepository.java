package pep.tii.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer>{
    List<Funcionario> findByNomeLike(String nome);
    Funcionario findByLoginAndSenha(String login, String senha);

}

