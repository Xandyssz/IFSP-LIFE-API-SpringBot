
package pep.tii.estoque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "despesas", catalog = "ifsplife", schema = "")
@NamedQueries({
    @NamedQuery(name = "Despesas.findAll", query = "SELECT d FROM Despesas d"),
    @NamedQuery(name = "Despesas.findByCodigoDespesa", query = "SELECT d FROM Despesas d WHERE d.codigo_despesa = :codigoDespesa"),
    @NamedQuery(name = "Despesas.findByDataVencimento", query = "SELECT d FROM Despesas d WHERE d.data_vencimento = :dataVencimento"),
    @NamedQuery(name = "Despesas.findByDescricao", query = "SELECT d FROM Despesas d WHERE d.descricao = :descricao"),
    @NamedQuery(name = "Despesas.findByNome", query = "SELECT d FROM Despesas d WHERE d.nome = :nome"),
    @NamedQuery(name = "Despesas.findByStatus", query = "SELECT d FROM Despesas d WHERE d.status = :status"),
    @NamedQuery(name = "Despesas.findByValor", query = "SELECT d FROM Despesas d WHERE d.valor = :valor")})
public class Despesas implements Serializable {

    
    @Id
    @Column(name = "codigo_despesa", nullable = false)
    private Integer codigo_despesa;
    
    @Column(name = "data_vencimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_vencimento;
    
    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;
    
    @Column(name = "nome", nullable = false, length = 45)
    private String nome;
    
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    
    @Column(name = "valor", nullable = false)
    private double valor;

    public Despesas() {
    }

    public Despesas(Integer codigo_despesa, Date data_vencimento, String descricao, String nome, String status, double valor) {
        this.codigo_despesa = codigo_despesa;
        this.data_vencimento = data_vencimento;
        this.descricao = descricao;
        this.nome = nome;
        this.status = status;
        this.valor = valor;
    }

    public Integer getCodigo_despesa() {
        return codigo_despesa;
    }

    public void setCodigo_despesa(Integer codigo_despesa) {
        this.codigo_despesa = codigo_despesa;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.codigo_despesa);
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
        final Despesas other = (Despesas) obj;
        return Objects.equals(this.codigo_despesa, other.codigo_despesa);
    }

    
    
}
