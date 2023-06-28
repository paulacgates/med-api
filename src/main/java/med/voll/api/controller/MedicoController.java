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
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.service.MedicoService;

@RestController
@AllArgsConstructor
@RequestMapping("/medicos")
public class MedicoController {
    
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados){
        return medicoService.cadastrarMedico(dados);
    }

    @GetMapping
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size=10, sort= {"nome"}) Pageable paginacao){
        return medicoService.listarMedicos(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizarMedico(@RequestBody @Valid DadosAtualizacaoMedico dados){
        return medicoService.atualizarMedico(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> DeletarMedico(@PathVariable Long id){
        return medicoService.deletarMedico(id);
    }
}
