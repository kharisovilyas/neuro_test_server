package ru.ilcorp.neuro_test.model.entity.assignment.ai;

import jakarta.persistence.*;
import ru.ilcorp.neuro_test.model.entity.assignment.StudentAnswerEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "neural_analysis")
public class NeuralAnalysisEntity {
    @Id
    @Column(name = "id_neural_analysis")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long neuralAnalysisId;
    @ManyToOne
    @JoinColumn(name = "student_answer_id")
    private StudentAnswerEntity studentAnswerEntity;
    @Lob
    @Column(name = "key_phrases")
    private String keyPhrases;
    @Column(name = "semantic_score")
    private Double semanticScore;
    @Column(name = "syntax_score")
    private Double syntaxScore;
    @Column(name = "correctness")
    private Boolean correctness;
    @Lob
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "analyzed")
    private LocalDateTime analyzedAt;

    public NeuralAnalysisEntity() {
    }
}
