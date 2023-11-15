package pep.tii.estoque.model;

import java.io.Serializable;

public class ProdutocompraId implements Serializable {

    private int codigo_compra;
    private int codigo_produto;

    public ProdutocompraId() {
    }

    public ProdutocompraId(int codigo_compra, int codigo_produto) {
        this.codigo_compra = codigo_compra;
        this.codigo_produto = codigo_produto;
    }

    public int getCodigo_compra() {
        return codigo_compra;
    }

    public void setCodigo_compra(int codigo_compra) {
        this.codigo_compra = codigo_compra;
    }

    public int getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.codigo_compra;
        hash = 97 * hash + this.codigo_produto;
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
        final ProdutocompraId other = (ProdutocompraId) obj;
        if (this.codigo_compra != other.codigo_compra) {
            return false;
        }
        return this.codigo_produto == other.codigo_produto;
    }

}
