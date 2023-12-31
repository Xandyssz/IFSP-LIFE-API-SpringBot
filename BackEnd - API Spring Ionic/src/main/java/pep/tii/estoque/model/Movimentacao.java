package pep.tii.estoque.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimentacao")
@NamedQueries({
    @NamedQuery(name = "Movimentacao.findAll", query = "SELECT m FROM Movimentacao m"),
    @NamedQuery(name = "Movimentacao.findBycodigo_movimentacao", query = "SELECT m FROM Movimentacao m WHERE m.codigo_movimentacao = :codigo_movimentacao"),
    @NamedQuery(name = "Movimentacao.findByMotivo", query = "SELECT m FROM Movimentacao m WHERE m.motivo = :motivo"),
    @NamedQuery(name = "Movimentacao.findByTipo", query = "SELECT m FROM Movimentacao m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "Movimentacao.findByValor", query = "SELECT m FROM Movimentacao m WHERE m.valor = :valor"),
    @NamedQuery(name = "Movimentacao.findByCodigoCaixa", query = "SELECT m FROM Movimentacao m WHERE m.codigo_caixa = :codigoCaixa")})

@IdClass(MovimentacaoId.class)
public class Movimentacao implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "codigo_caixa", referencedColumnName = "codigo_caixa")
    private Caixa codigo_caixa;

    @Id
    @Column(name = "codigo_movimentacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo_movimentacao;

    @Column(name = "motivo", nullable = false, length = 200)
    private String motivo;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "valor", nullable = false)
    private double valor;

    public Movimentacao() {
    }

    public Movimentacao(int codigo_movimentacoa, String motivo, String tipo, double valor, Caixa codigo_caixa) {
        this.codigo_movimentacao = codigo_movimentacao;
        this.motivo = motivo;
        this.tipo = tipo;
        this.valor = valor;
        this.codigo_caixa = codigo_caixa;
    }

    public int getCodigo_movimentacao() {
        return codigo_movimentacao;
    }

    public void setCodigo_movimentacao(int codigo_movimentacao) {
        this.codigo_movimentacao = codigo_movimentacao;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Caixa getCodigo_caixa() {
        return codigo_caixa;
    }

    public void setCodigo_caixa(Caixa codigo_caixa) {
        this.codigo_caixa = codigo_caixa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.codigo_caixa);
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
        final Movimentacao other = (Movimentacao) obj;
        return Objects.equals(this.codigo_caixa, other.codigo_caixa);
    }

}
