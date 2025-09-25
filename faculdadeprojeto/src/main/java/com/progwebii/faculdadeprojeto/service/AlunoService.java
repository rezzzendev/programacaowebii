package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.AlunoDTO;
import com.progwebii.faculdadeprojeto.model.Aluno;
import com.progwebii.faculdadeprojeto.repository.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {


    private final AlunoRepository alunoRepository;

    public Aluno cadastrar(AlunoDTO alunoDTO) {
        Aluno alunoSalvo = new Aluno();
        alunoSalvo.setNome(alunoDTO.getNome());
        alunoSalvo.setDataNascimento(alunoDTO.getDataNascimento());
        alunoSalvo.setMatricula(alunoDTO.getMatricula());
        return alunoRepository.save(alunoSalvo);
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Aluno atualizar(Long id, AlunoDTO alunoDTO) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com id: " + id));

        alunoExistente.setNome(alunoDTO.getNome());
        alunoExistente.setDataNascimento(alunoDTO.getDataNascimento());
        alunoExistente.setMatricula(alunoDTO.getMatricula());
        return alunoRepository.save(alunoExistente);
    }

    public void deletar(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Aluno não encontrado com id: " + id));
        alunoRepository.deleteById(id);
    }

}
