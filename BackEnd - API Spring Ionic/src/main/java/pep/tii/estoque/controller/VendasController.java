package pep.tii.estoque.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pep.tii.estoque.model.Vendas;
import pep.tii.estoque.repository.VendaRepository;

@RestController
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    private VendaRepository vendasRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/", produces = "application/json")
    public List<Vendas> getCompras()
    {
        return vendasRepository.getTodos();
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/{codigo_venda}", produces = "application/json")
    public List<Vendas> getCompras(@PathVariable int codigo_venda) {
        return vendasRepository.getVendasCodigo(codigo_venda);
    }

}
