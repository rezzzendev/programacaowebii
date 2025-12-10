package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.ProvaDTO;
import com.progwebii.faculdadeprojeto.model.Disciplina;
import com.progwebii.faculdadeprojeto.model.Prova;
import com.progwebii.faculdadeprojeto.repository.DisciplinaRepository;
import com.progwebii.faculdadeprojeto.repository.ProvaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvaService {

    @Autowired
    private ProvaRepository provaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public Prova cadastrar(ProvaDTO dto) {
        Disciplina disciplina = disciplinaRepository.findById(dto.getDisciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        Prova prova = new Prova();
        prova.setDisciplina(disciplina);
        prova.setDataHora(dto.getDataHora());

        return provaRepository.save(prova);
    }

    public List<Prova> listarPorDisciplina(Long disciplinaId) {
        return provaRepository.findByDisciplinaId(disciplinaId);
    }


    public Prova atualizar(Long id, Prova provaAtualizada) {
        Prova prova = provaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prova não encontrada"));

        prova.setDataHora(provaAtualizada.getDataHora());

        return provaRepository.save(prova);
    }


    public void excluir(Long id) {
        if (!provaRepository.existsById(id)) {
            throw new RuntimeException("Prova não encontrada");
        }

        provaRepository.deleteById(id);
    }
}
