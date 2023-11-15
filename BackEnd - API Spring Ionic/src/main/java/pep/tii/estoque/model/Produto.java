package pep.tii.estoque.model;
import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="produto")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int codigo_produto;
    private String categoria;
    private Date data_fabricacao;
    private Date data_validade;
    private String descricao;
    private String dosagem;
    private int lote;
    private String nome;
    private int quantidade;
    private double valor;

    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    // METODO PARA ATUALIZAR O ESTOQUE, QUANDO REALIZA UMA  VENDA
    public void atualizarEstoqueVenda(int quantidadeVendida) {
        this.quantidade -= quantidadeVendida;
    }

    // METODO PARA ATUALIZAR O ESTOQUE, QUANDO REALIZA UMA COMPRA
    public void atualizarEstoqueCompra(int quantidadeComprada) {
        this.quantidade += quantidadeComprada;
    }
    // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

}
