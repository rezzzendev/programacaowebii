package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.FrequenciaDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.model.Frequencia;
import com.progwebii.faculdadeprojeto.repository.AlunoRepository;
import com.progwebii.faculdadeprojeto.repository.DisciplinaRepository;
import com.progwebii.faculdadeprojeto.repository.FrequenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class FrequenciaService {

    @Autowired
    private FrequenciaRepository frequenciaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Frequencia registrarFrequencia(FrequenciaDTO dto) {

        // Busca aluno e disciplina
        Aluno aluno = alunoRepository.findByMatricula(dto.getMatricula())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado: " + dto.getMatricula()));

        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina não encontrada: " + dto.getDisciplinaId()));

        // Busca frequência existente
        Optional<Frequencia> optFrequencia = frequenciaRepository
                .findByAlunoMatriculaAndDisciplinaId(dto.getMatricula(), dto.getDisciplinaId());

        Frequencia frequencia;

        if (optFrequencia.isPresent()) {
            frequencia = optFrequencia.get();
            // Sobrescreve (edita) os valores, em vez de acumular
            frequencia.setPresencas(dto.getPresencas());
            frequencia.setFaltas(dto.getFaltas());
        } else {
            frequencia = new Frequencia();
            frequencia.setAluno(aluno);
            frequencia.setDisciplina(disciplina);
            frequencia.setPresencas(dto.getPresencas());
            frequencia.setFaltas(dto.getFaltas());
        }

        return frequenciaRepository.save(frequencia);
    }

    public Frequencia buscarPorAlunoEDisciplina(String matricula, Long disciplinaId) {
        return frequenciaRepository.findByAlunoMatriculaAndDisciplinaId(matricula, disciplinaId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Frequência não encontrada para o aluno: " + matricula + " na disciplina: " + disciplinaId
                ));
    }

    /**
     * NOVO MÉTODO: Deleta uma frequência pelo ID.
     */
    public void excluirFrequencia(Long id) {
        if (!frequenciaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro de Frequência não encontrado.");
        }
        frequenciaRepository.deleteById(id);
    }
}