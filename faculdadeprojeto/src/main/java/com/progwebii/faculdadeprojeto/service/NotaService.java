package com.progwebii.faculdadeprojeto.service;

import com.progwebii.faculdadeprojeto.dto.NotaDTO;
import com.progwebii.faculdadeprojeto.model.Nota;
import com.progwebii.faculdadeprojeto.repository.NotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotaService {

    private NotaRepository notaRepository;

    public Nota cadastrar(NotaDTO notaDTO) {
        Nota notaSalvo = new Nota();
        notaSalvo.setNota(notaDTO.getNota());
        return notaRepository.save(notaSalvo);
    }

    public List<Nota> listar() {
        return notaRepository.findAll();
    }

    public Nota atualizar(Long id, NotaDTO notaDTO) {
        Nota notaExistente = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada com id: " + id));

        notaExistente.setNota(notaDTO.getNota());
        return notaRepository.save(notaExistente);
    }

    public void deletar(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Nota não encontrada com id: " + id));
        notaRepository.deleteById(id);
    }
}
