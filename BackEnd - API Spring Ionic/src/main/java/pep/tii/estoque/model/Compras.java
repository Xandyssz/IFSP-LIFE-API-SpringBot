package pep.tii.estoque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "compras")
@NamedQueries({
    @NamedQuery(name = "Compras.findAll", query = "SELECT c FROM Compras c"),
    @NamedQuery(name = "Compras.findByCodigoCompra", query = "SELECT c FROM Compras c WHERE c.codigo_compra = :codigo_compra"),
    @NamedQuery(name = "Compras.findByDataCompra", query = "SELECT c FROM Compras c WHERE c.data_compra = :dataCompra"),
    @NamedQuery(name = "Compras.findByFormaPagamento", query = "SELECT c FROM Compras c WHERE c.forma_pagamento = :formaPagamento"),
    @NamedQuery(name = "Compras.findByPeriodo", query = "SELECT c FROM Compras c WHERE c.data_compra BETWEEN :datainicio AND :datafim"),
    @NamedQuery(name = "Compras.itensPorPeriodos", query = "SELECT pc FROM Produtocompra pc WHERE pc.codigo_compra.data_compra BETWEEN :datainicio AND :datafim"),
    @NamedQuery(name = "Compras.findByValortotal", query = "SELECT c FROM Compras c WHERE c.valortotal = :valortotal")})

public class Compras implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "codigo_compra", nullable = false)
    private Integer codigo_compra;

    @Column(name = "data_compra", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_compra;

    @Column(name = "forma_pagamento", nullable = false)
    private String forma_pagamento;

    @Column(name = "valortotal", nullable = false)
    private double valortotal;

    @OneToMany(cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            mappedBy = "codigo_compra")
    @JsonManagedReference
    private List<Produtocompra> itemcompra = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST,
            orphanRemoval = true,
            mappedBy = "codigo_compra")
    @JsonManagedReference
    private List<Pagamentocompra> pagamentocompra;

    @ManyToOne
    @JoinColumn(name = "codigo_fornecedor", referencedColumnName = "codigo_fornecedor")
    private Fornecedor codigo_fornecedor;

    public Compras() {
    }

    public Compras(Integer codigo_compra, Date data_compra, String forma_pagamento, double valortotal, List<Pagamentocompra> pagamentocompra, Fornecedor codigo_fornecedor) {
        this.codigo_compra = codigo_compra;
        this.data_compra = data_compra;
        this.forma_pagamento = forma_pagamento;
        this.valortotal = valortotal;
        this.pagamentocompra = pagamentocompra;
        this.codigo_fornecedor = codigo_fornecedor;
    }

    public Integer getCodigo_compra() {
        return codigo_compra;
    }

    public void setCodigo_compra(Integer codigo_compra) {
        this.codigo_compra = codigo_compra;
    }

    public Date getData_compra() {
        return data_compra;
    }

    public void setData_compra(Date data_compra) {
        this.data_compra = data_compra;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public void adicionarItem(Produtocompra ic) {
        ic.setCodigo_compra(this);
        this.itemcompra.add(ic);
    }

    public List<Produtocompra> getItemcompra() {
        return itemcompra;
    }

    public void setItemcompra(List<Produtocompra> itemcompra) {
        this.itemcompra = itemcompra;
    }

    public List<Pagamentocompra> getPagamentocompra() {
        return pagamentocompra;
    }

    public void setPagamentocompra(List<Pagamentocompra> pagamentocompra) {
        this.pagamentocompra = pagamentocompra;
        for (Pagamentocompra p : pagamentocompra) {
            p.setCodigo_compra(this);
        }
    }

    public Fornecedor getCodigo_fornecedor() {
        return codigo_fornecedor;
    }

    public void setCodigo_fornecedor(Fornecedor codigo_fornecedor) {
        this.codigo_fornecedor = codigo_fornecedor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.codigo_compra);
        hash = 71 * hash + Objects.hashCode(this.codigo_fornecedor);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Compras other = (Compras) obj;
        if (!Objects.equals(this.codigo_compra, other.codigo_compra)) {
            return false;
        }
        return Objects.equals(this.codigo_fornecedor, other.codigo_fornecedor);
    }

}
