package pep.tii.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pep.tii.estoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    List<Produto> findByNomeLike(String nome);
}