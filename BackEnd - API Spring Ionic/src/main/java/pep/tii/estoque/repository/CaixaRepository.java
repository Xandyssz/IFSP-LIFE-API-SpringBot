package pep.tii.estoque.repository;

import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.Caixa;

@Repository
public class CaixaRepository {
    @PersistenceContext
    private EntityManager gerente;
    private static Caixa caixaAberto = null;

    public void abrir(Caixa caixa) {
        gerente.getTransaction().begin();
        gerente.persist(caixa);
        gerente.getTransaction().commit();
        gerente.close();
        caixaAberto = caixa;
    }

    public void fechar(Caixa caixa) {
        gerente.getTransaction().begin();
        gerente.merge(caixa);
        gerente.getTransaction().commit();
        gerente.close();
        caixaAberto = null;
    }

    public void alterar(Caixa caixa) {
        gerente.getTransaction().begin();
        gerente.merge(caixa);
        gerente.getTransaction().commit();
        gerente.close();

    }

    public List<Caixa> getTodos() {
        TypedQuery<Caixa> consulta
                = gerente.createNamedQuery("Caixa.findAll", Caixa.class);
        return consulta.getResultList();

    }

    public List<Caixa> getPorPeriodo(Date inicio, Date fim) {
        TypedQuery<Caixa> consulta
                = gerente.createNamedQuery("Caixa.findByAbertura", Caixa.class);

        consulta.setParameter("inicio", inicio);
        consulta.setParameter("fim", fim);
        return consulta.getResultList();

    }

    private Caixa verificarCaixaAbertoBanco() {
        List<Caixa> caixas;

        TypedQuery<Caixa> consulta = gerente.createNamedQuery("Caixa.findByFechamento", Caixa.class);

        caixas = consulta.getResultList();
        if (caixas.isEmpty()) {
            return null;
        } else {
            return caixas.get(0);
        }
    }

    public boolean isCaixaAberto() {
        if (caixaAberto == null) {
            caixaAberto = verificarCaixaAbertoBanco();
        }
        return caixaAberto != null;
    }

    public Caixa getCaixaAberto() {
        if (caixaAberto == null) {
            caixaAberto = verificarCaixaAbertoBanco();
        }
        return caixaAberto;
    }

    public void atualizarCaixa() {
        caixaAberto = verificarCaixaAbertoBanco();
    }

}
