package com.progwebii.faculdadeprojeto.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class AlunoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com Aluno (usa a matrícula como chave estrangeira)
    @ManyToOne
    @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula")
    private Aluno aluno;

    // Relacionamento com Disciplina
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    // Dados de Notas
    private Double bimestre1;
    private Double bimestre2;
    // O campo 'media' pode ser calculado no Service/Frontend, mas adicionaremos aqui
    // caso você queira armazená-lo no banco.
    private Double media;

    // Dados de Frequência
    private Integer faltas;
    private Integer presencas;

    // Dados de Provas (usando JSON ou ElementCollection para lista de Strings)
    // O mais simples para um array de Strings é usar @ElementCollection
    @ElementCollection
    @CollectionTable(name = "aluno_disciplina_provas", joinColumns = @JoinColumn(name = "aluno_disciplina_id"))
    @Column(name = "horario_prova")
    private List<String> horariosProvas = new ArrayList<>();

    // Construtores
    public AlunoDisciplina() {}

    // Getters e Setters (Implemente todos, ou use Lombok)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Double getBimestre1() {
        return bimestre1;
    }

    public void setBimestre1(Double bimestre1) {
        this.bimestre1 = bimestre1;
    }

    public Double getBimestre2() {
        return bimestre2;
    }

    public void setBimestre2(Double bimestre2) {
        this.bimestre2 = bimestre2;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Integer getPresencas() {
        return presencas;
    }

    public void setPresencas(Integer presencas) {
        this.presencas = presencas;
    }

    public List<String> getHorariosProvas() {
        return horariosProvas;
    }

    public void setHorariosProvas(List<String> horariosProvas) {
        this.horariosProvas = horariosProvas;
    }
}