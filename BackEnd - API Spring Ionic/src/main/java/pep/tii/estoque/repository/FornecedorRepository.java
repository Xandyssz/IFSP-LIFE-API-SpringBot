package pep.tii.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.tii.estoque.model.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Integer>{
    List<Fornecedor> findByNomeLike(String nome);
}
