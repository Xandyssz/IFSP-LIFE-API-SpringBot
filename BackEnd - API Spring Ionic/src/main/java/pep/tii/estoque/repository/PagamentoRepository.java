package pep.tii.estoque.repository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.Pagamentocompra;
import pep.tii.estoque.model.Produto;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class PagamentoRepository {

    @PersistenceContext
    private EntityManager gerente;

    public void realizarPagamento(Pagamentocompra pagamento) {

        gerente.getTransaction().begin();

        gerente.merge(pagamento);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public List<Pagamentocompra> getTodos() {

        TypedQuery<Pagamentocompra> consulta
                = gerente.createNamedQuery("Pagamentocompra.findAll", Pagamentocompra.class);

        return consulta.getResultList();

    }

    public List<Pagamentocompra> getPorNome(String nomePesquisar) {

        TypedQuery<Pagamentocompra> consulta
                = gerente.createNamedQuery("Pagamentocompra.findByNomeFornecedor", Pagamentocompra.class);

        consulta.setParameter("nome", "%" + nomePesquisar + "%");

        return consulta.getResultList();

    }

    public List<Pagamentocompra> getPorPeriodo(Date inicio, Date fim) {
        
        TypedQuery<Pagamentocompra> consulta
                = gerente.createNamedQuery("Pagamentocompra.findByPeriodo", Pagamentocompra.class);

        consulta.setParameter("inicio", inicio);
        consulta.setParameter("fim", fim);
        return consulta.getResultList();
    }

    public List<Pagamentocompra> getPorStatus(String status) {
        

        TypedQuery<Pagamentocompra> consulta = gerente.createNamedQuery("Pagamentocompra.findByStatus", Pagamentocompra.class);

        consulta.setParameter("status", status);

        return consulta.getResultList();
    }

}
