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

import pep.tii.estoque.model.Convenio;
import pep.tii.estoque.repository.ConvenioRepository;

@RestController
@RequestMapping("convenios")
public class ConvenioController {

    @Autowired
    private ConvenioRepository convenioRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/", produces = "application/json")
    public List<Convenio> getConvenios()
    {
        return convenioRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value="/{codigo_convenio}", produces = "application/json")
    public Optional<Convenio> getConvenios(@PathVariable int codigo_convenio)
    {
        return convenioRepository.findById(codigo_convenio);
    }

    @GetMapping(value="", produces = "application/json")
    public List<Convenio> getConvenioPorNome(
            @RequestParam(value="nome", defaultValue = "") String nome)
    {
        return convenioRepository.findByNomeLike("%"+nome+"%");
    }

    @PostMapping("")
    public ResponseEntity<Convenio> criarConvenio(@RequestBody Convenio convenio) {
        convenioRepository.save(convenio);
        return new ResponseEntity<>(convenio, HttpStatus.CREATED);
    }

}
