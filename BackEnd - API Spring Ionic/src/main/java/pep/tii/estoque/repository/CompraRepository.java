package pep.tii.estoque.repository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.Caixa;
import pep.tii.estoque.model.Compras;
import pep.tii.estoque.model.Pagamentocompra;
import pep.tii.estoque.model.Produto;
import pep.tii.estoque.model.Produtocompra;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class CompraRepository {

    @PersistenceContext
    private EntityManager gerente;
    
    public void adicionar(Compras compras) {

        gerente.getTransaction().begin();

        gerente.persist(compras);

        for (Produtocompra pc : compras.getItemcompra()) {
            Produto p = pc.getcodigo_produto();
            p.atualizarEstoqueCompra(pc.getQuantidade());
            gerente.merge(p);
        }

        gerente.getTransaction().commit();

        gerente.close();
    }

    public void remover(Compras Compras) {

        Compras compraExcluir = gerente.find(Compras.class,
                Compras.getCodigo_compra());

        gerente.getTransaction().begin();

        gerente.remove(compraExcluir);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public void alterar(Compras compras) {

        gerente.getTransaction().begin();

        gerente.merge(compras);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public List<Compras> getTodos() {

        TypedQuery<Compras> consulta
                = gerente.createNamedQuery("Compras.findAll", Compras.class);

        return consulta.getResultList();

    }

    public List<Compras> getComprasCodigo(int codigo_compra) {
        TypedQuery<Compras> consulta = gerente.createNamedQuery("Compras.findByCodigoCompra", Compras.class);
        consulta.setParameter("codigo_compra", codigo_compra);
        return consulta.getResultList();
    }

    public List<Compras> getPorPeriodo(Date inicio, Date fim) {

        TypedQuery<Compras> consulta
                = gerente.createNamedQuery("Compras.findByPeriodo", Compras.class);

        consulta.setParameter("datainicio", inicio);
        consulta.setParameter("datafim", fim);

        return consulta.getResultList();

    }

    public double getTotalParcelasPago(Caixa caixa) {

        double total = 0;

        TypedQuery<Pagamentocompra> consulta
                = gerente.createNamedQuery("Pagamentocompra.findByCaixaAndStatusPago", Pagamentocompra.class);
        consulta.setParameter("caixa", caixa);

        for (Pagamentocompra p : consulta.getResultList()) {
            total += p.getValor();
        }

        return total;
    }

    public List<Produtocompra> listarTodosProdutos() {
        
        TypedQuery<Produtocompra> consulta
                = gerente.createNamedQuery("Produtocompra.findAll", Produtocompra.class);
        return consulta.getResultList();

    }

    public List<Produtocompra> getItensPorPeriodo(Date inicio, Date fim) {

        TypedQuery<Produtocompra> consulta
                = gerente.createNamedQuery("Compras.itensPorPeriodos", Produtocompra.class);

        consulta.setParameter("datainicio", inicio);
        consulta.setParameter("datafim", fim);

        return consulta.getResultList();

    }
}
