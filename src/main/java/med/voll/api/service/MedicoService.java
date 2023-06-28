package med.voll.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
@AllArgsConstructor
public class MedicoService {
    private MedicoRepository medicoRepository;

    public ResponseEntity<Medico> cadastrarMedico(DadosCadastroMedico dadosMedico){
        Medico medicoSalvo = medicoRepository.save(new Medico(dadosMedico));
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoSalvo);
    }

    public Page<DadosListagemMedico> listarMedicos(Pageable paginacao){
        return medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    public ResponseEntity<String> atualizarMedico(DadosAtualizacaoMedico dadosNovosMedico) {
        Medico medicoAtualizado = medicoRepository.getReferenceById(dadosNovosMedico.id());
        medicoAtualizado.atualizarInformacoes(dadosNovosMedico);
        return ResponseEntity.status(HttpStatus.OK).body("Medico Atualizado com Sucesso!");
    }

    public ResponseEntity<String> deletarMedico(Long id) {
        Medico medicoExcluido = medicoRepository.getReferenceById(id);
        medicoExcluido.excluir();
        return ResponseEntity.status(HttpStatus.OK).body("Paciente Excluido com Sucesso!");
    }
}
