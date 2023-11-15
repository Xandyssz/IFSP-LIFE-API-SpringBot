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

import pep.tii.estoque.model.Fornecedor;
import pep.tii.estoque.repository.FornecedorRepository;

@RestController
@RequestMapping("fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/", produces = "application/json")
    public List<Fornecedor> getFornecedors()
    {
        return fornecedorRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/{codigo_fornecedor}", produces = "application/json")
    public Optional<Fornecedor> getFornecedors(@PathVariable int codigo_fornecedor)
    {
        return fornecedorRepository.findById(codigo_fornecedor);
    }

    @GetMapping(value="", produces = "application/json")
    public List<Fornecedor> getFornecedorPorNome(
            @RequestParam(value="nome", defaultValue = "") String nome)
    {
        return fornecedorRepository.findByNomeLike("%"+nome+"%");
    }

    @PostMapping("")
    public ResponseEntity<Fornecedor> criarFornecedor(@RequestBody Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
        return new ResponseEntity<>(fornecedor, HttpStatus.CREATED);
    }

}
