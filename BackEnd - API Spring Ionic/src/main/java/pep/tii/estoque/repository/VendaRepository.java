package pep.tii.estoque.repository;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import pep.tii.estoque.model.*;

import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager gerente;
    public void adicionar(Vendas vendas) {

        gerente.getTransaction().begin();

        gerente.persist(vendas);

        for (Produtovenda pv : vendas.getProdutovendaList()) {
            Produto p = pv.getcodigo_produto();
            p.atualizarEstoqueVenda(pv.getQuantidade());
            System.out.println(p.getQuantidade());
            gerente.merge(p);
        }

        gerente.getTransaction().commit();

        gerente.close();
    }

    public void remover(Vendas Vendas) {

        Vendas vendaExcluir = gerente.find(Vendas.class,
                Vendas.getCodigo_venda());

        gerente.getTransaction().begin();

        gerente.remove(vendaExcluir);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public void alterar(Vendas vendas) {

        gerente.getTransaction().begin();

        gerente.merge(vendas);

        gerente.getTransaction().commit();

        gerente.close();
    }

    public List<Vendas> getTodos() {

        TypedQuery<Vendas> consulta
                = gerente.createNamedQuery("Vendas.findAll", Vendas.class);

        return consulta.getResultList();

    }

    public List<Vendas> getVendasCodigo(int codigo_venda) {
        TypedQuery<Vendas> consulta = gerente.createNamedQuery("Vendas.findByCodigoVenda", Vendas.class);
        consulta.setParameter("codigo_venda", codigo_venda);
        return consulta.getResultList();
    }


    public double getTotalVendaPorCaixa(Caixa caixa) {
        
        double total = 0;

        TypedQuery<Vendas> consulta
                = gerente.createNamedQuery("Vendas.findByCaixa", Vendas.class);
        consulta.setParameter("caixa", caixa);

        for (Vendas v : consulta.getResultList()) {
            total += v.getValor_venda();
        }

        return total;
    }

    public List<Vendas> getPorPeriodo(Date inicio, Date fim) {

        TypedQuery<Vendas> consulta
                = gerente.createNamedQuery("Vendas.findByPeriodo", Vendas.class);

        consulta.setParameter("datainicio", inicio);
        consulta.setParameter("datafim", fim);

        return consulta.getResultList();

    }

    public List<Produtovenda> listarTodosProdutos() {
        
        TypedQuery<Produtovenda> consulta
                = gerente.createNamedQuery("Produtovenda.findAll", Produtovenda.class);
        return consulta.getResultList();

    }

    public List<Produtovenda> getItensPorPeriodo(Date inicio, Date fim) {

        TypedQuery<Produtovenda> consulta
                = gerente.createNamedQuery("Vendas.itensPorPeriodos", Produtovenda.class);

        consulta.setParameter("datainicio", inicio);
        consulta.setParameter("datafim", fim);

        return consulta.getResultList();

    }
}
