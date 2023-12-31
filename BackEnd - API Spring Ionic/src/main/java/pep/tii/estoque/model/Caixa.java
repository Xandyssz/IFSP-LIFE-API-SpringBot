package pep.tii.estoque.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "caixa")
@NamedQueries({
    @NamedQuery(name = "Caixa.findAll", query = "SELECT c FROM Caixa c"),
    @NamedQuery(name = "Caixa.findByAbertura", query = "SELECT c FROM Caixa c WHERE c.data_abertura BETWEEN :inicio AND :fim"),
    @NamedQuery(name = "Caixa.findByFechamento", query = "SELECT c FROM Caixa c WHERE c.data_fechamento IS NULL")})

public class Caixa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_caixa", nullable = false)
    private int codigo_caixa;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "data_abertura", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_abertura;

    @Column(name = "horario_abertura", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horario_abertura;

    @Column(name = "data_fechamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_fechamento;

    @Column(name = "horario_fechamento", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horario_fechamento;

    @Column(name = "total_entradas")
    private double total_entradas;

    @Column(name = "total_saidas")
    private double total_saidas;

    @Column(name = "valor_abertura")
    private double valor_abertura;

    @Column(name = "valor_fechamento")
    private double valor_fechamento;

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "codigo_caixa")
    @JsonIgnore
    private List<Vendas> vendas = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "codigo_caixa")
    @JsonIgnore
    private List<Pagamentocompra> pagamentocompra = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REFRESH,
            mappedBy = "codigo_caixa")
    @JsonIgnore
    private List<Movimentacao> movimentacao = new ArrayList<>();

    public Caixa() {
    }

    public Caixa(int codigo_caixa, String status, Date data_abertura, Date horario_abertura, Date data_fechamento, Date horario_fechamento, double total_entradas, double total_saidas, double valor_abertura, double valor_fechamento) {
        this.codigo_caixa = codigo_caixa;
        this.status = status;
        this.data_abertura = data_abertura;
        this.horario_abertura = horario_abertura;
        this.data_fechamento = data_fechamento;
        this.horario_fechamento = horario_fechamento;
        this.total_entradas = total_entradas;
        this.total_saidas = total_saidas;
        this.valor_abertura = valor_abertura;
        this.valor_fechamento = valor_fechamento;
    }

    public int getCodigo_caixa() {
        return codigo_caixa;
    }

    public void setCodigo_caixa(int codigo_caixa) {
        this.codigo_caixa = codigo_caixa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // GETTER DOS VALORES DE MOVIMENTAÇÃO //
    public double getSangria() {
        double soma_sangria = 0;
        for (Movimentacao mo : this.movimentacao) {
            if (mo.getTipo().equals("Sangria")) {
                soma_sangria += mo.getValor();
            }
        }
        return soma_sangria;
    }
    public double getSuplementacao() {
        double soma_suplementacao = 0;
        for (Movimentacao mo : this.movimentacao) {
            if (mo.getTipo().equals("Suplementação")) {
                soma_suplementacao += mo.getValor();
            }
        }
        return soma_suplementacao;
    }
    // GETTER DOS VALORES DE MOVIMENTAÇÃO //
    
    // GETTER DOS VALORES DE VENDA //
    public double getVendasPorCaixa() {
        double soma_vendas = 0;
        for (Vendas venda : this.vendas) {
            soma_vendas += venda.getValor_venda();
        }
        return soma_vendas;
    }

    // GETTER DOS VALORES DE VENDA //

    public double getTotalMovimentacao() {
        double total = valor_abertura; // Start with the opening value
        for (Movimentacao m : movimentacao) {
            if (m.getTipo().equals("Sangria")) {
                total -= m.getValor(); // Subtract sangria
            } else {
                total += m.getValor();
            }
        }
        return total;
    }
    
    public Date getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(Date data_abertura) {
        this.data_abertura = data_abertura;
    }

    public Date getHorario_abertura() {
        return horario_abertura;
    }

    public void setHorario_abertura(Date horario_abertura) {
        this.horario_abertura = horario_abertura;
    }

    public Date getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(Date data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public Date getHorario_fechamento() {
        return horario_fechamento;
    }

    public void setHorario_fechamento(Date horario_fechamento) {
        this.horario_fechamento = horario_fechamento;
    }

    public Double getTotal_entradas() {
        return total_entradas;
    }

    public void setTotal_entradas(double total_entradas) {
        this.total_entradas = total_entradas;
    }

    public Double getTotal_saidas() {
        return total_saidas;
    }

    public void setTotal_saidas(double total_saidas) {
        this.total_saidas = total_saidas;
    }

    public Double getValor_abertura() {
        return valor_abertura;
    }

    public void setValor_abertura(double valor_abertura) {
        this.valor_abertura = valor_abertura;
    }

    public Double getValor_fechamento() {
        return valor_fechamento;
    }

    public void setValor_fechamento(double valor_fechamento) {
        this.valor_fechamento = valor_fechamento;
    }

    public List<Vendas> getVendas() {
        return vendas;
    }

    public void setVendas(List<Vendas> vendas) {
        this.vendas = vendas;
    }

    public List<Pagamentocompra> getPagamentocompra() {
        return pagamentocompra;
    }

    public void setPagamentocompra(List<Pagamentocompra> pagamentocompra) {
        this.pagamentocompra = pagamentocompra;
    }

    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(List<Movimentacao> movimentacao) {
        this.movimentacao = movimentacao;
    }
    
    public void addVenda(Vendas venda)
    {
        this.vendas.add(venda);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo_caixa);
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
        final Caixa other = (Caixa) obj;
        return Objects.equals(this.codigo_caixa, other.codigo_caixa);
    }

}
