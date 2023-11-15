package pep.tii.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.Convenio;

@Repository
public interface ConvenioRepository  extends JpaRepository<Convenio,Integer>{
    List<Convenio> findByNomeLike(String nome);
}