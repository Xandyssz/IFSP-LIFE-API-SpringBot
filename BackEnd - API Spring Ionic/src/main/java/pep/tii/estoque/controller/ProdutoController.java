package pep.tii.estoque.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pep.tii.estoque.model.Produto;
import pep.tii.estoque.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/", produces = "application/json")
    public List<Produto> getProdutos()
    {
        return produtoRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/{codigo_produto}", produces = "application/json")
    public Optional<Produto> getProdutos(@PathVariable int codigo_produto)
    {
        return produtoRepository.findById(codigo_produto);
    }

    @GetMapping(value="", produces = "application/json")
    public List<Produto> getProdutoPorNome(
            @RequestParam(value="nome", defaultValue = "") String nome)
    {
        return produtoRepository.findByNomeLike("%"+nome+"%");
    }

    @PostMapping("")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }
}
