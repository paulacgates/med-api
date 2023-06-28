package med.voll.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.paciente.DadosAtualizacaoPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.service.PacienteService;

@RestController
@AllArgsConstructor
@RequestMapping("/pacientes")
public class PacienteController {
    
    private PacienteService pacienteService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosPaciente){
        return pacienteService.cadastrarPaciente(dadosPaciente);
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return pacienteService.listarPacientes(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        return pacienteService.atualizarPaciente(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> DeletarPaciente(@PathVariable Long id){
        return pacienteService.deletarPaciente(id);
    }
}
