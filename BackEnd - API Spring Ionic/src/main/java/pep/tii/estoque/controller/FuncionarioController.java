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
import pep.tii.estoque.model.Funcionario;
import pep.tii.estoque.repository.FuncionarioRepository;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/", produces = "application/json")
    public List<Funcionario> getFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @GetMapping(value = "/{codigo_funcionario}", produces = "application/json")
    public Optional<Funcionario> getFuncionarios(@PathVariable int codigo_funcionario) {
        return funcionarioRepository.findById(codigo_funcionario);
    }

    @GetMapping(value = "", produces = "application/json")
    public List<Funcionario> getFuncionarioPorNome(
            @RequestParam(value = "nome", defaultValue = "") String nome) {
        return funcionarioRepository.findByNomeLike("%" + nome + "%");
    }

    @PostMapping("")
    public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:8100")
    @PostMapping("/tabs/tabs1/login")
    public ResponseEntity<Funcionario> fazerLogin(@RequestBody Funcionario loginRequest) {
        Funcionario funcionario = funcionarioRepository.findByLoginAndSenha(loginRequest.getLogin(), loginRequest.getSenha());

        if (funcionario != null) {
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
