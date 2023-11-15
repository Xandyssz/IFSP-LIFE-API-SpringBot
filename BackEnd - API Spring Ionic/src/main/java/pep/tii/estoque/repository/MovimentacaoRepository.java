package pep.tii.estoque.repository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.Caixa;
import pep.tii.estoque.model.Movimentacao;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class MovimentacaoRepository {
    @PersistenceContext
    private EntityManager gerente;

    public void adicionar(Movimentacao movimentacao) {

        gerente.getTransaction().begin();

        gerente.persist(movimentacao);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public List<Movimentacao> getTodos() {

        TypedQuery<Movimentacao> consulta
                = gerente.createNamedQuery("Movimentacao.findAll", Movimentacao.class);

        return consulta.getResultList();

    }

    public List<Movimentacao> getMovPorCaixa(Caixa caixa) {

        TypedQuery<Movimentacao> consulta
                = gerente.createNamedQuery("Movimentacao.findByCodigoCaixa", Movimentacao.class);
        consulta.setParameter("codigoCaixa", caixa);

        return consulta.getResultList();
    }

}
