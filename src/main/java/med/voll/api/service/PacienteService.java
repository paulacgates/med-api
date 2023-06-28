package med.voll.api.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import med.voll.api.paciente.DadosAtualizacaoPaciente;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
@AllArgsConstructor
public class PacienteService {

    private PacienteRepository pacienteRepository;

    @Transactional
    public ResponseEntity<Paciente> cadastrarPaciente(DadosCadastroPaciente dadosPaciente){
        Paciente pacienteSalvo = pacienteRepository.save(new Paciente(dadosPaciente));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);
    }

    public Page<DadosListagemPaciente> listarPacientes(Pageable paginacao){
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }

    @Transactional
    public ResponseEntity<String> atualizarPaciente(DadosAtualizacaoPaciente dadosNovosPaciente) {
        Paciente pacienteAtualizado = pacienteRepository.getReferenceById(dadosNovosPaciente.id()) ;
        pacienteAtualizado.atualizarInformacoes(dadosNovosPaciente);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente Atualizado com Sucesso!");
   
    }

    public ResponseEntity<String> deletarPaciente(Long id) {
        Paciente pacienteExcluido = pacienteRepository.getReferenceById(id);
        pacienteExcluido.excluir();
        return ResponseEntity.status(HttpStatus.OK).body("Paciente Excluido com Sucesso!");
    }
}

