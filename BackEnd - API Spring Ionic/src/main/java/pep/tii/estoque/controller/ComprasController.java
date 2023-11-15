package pep.tii.estoque.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pep.tii.estoque.model.Compras;
import pep.tii.estoque.repository.CompraRepository;

@RestController
@RequestMapping("compras")
public class ComprasController {

    @Autowired
    private CompraRepository compraRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/", produces = "application/json")
    public List<Compras> getCompras()
    {
        return compraRepository.getTodos();
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/{codigo_compra}", produces = "application/json")
    public List<Compras> getCompras(@PathVariable int codigo_compra) {
        return compraRepository.getComprasCodigo(codigo_compra);
    }



}
