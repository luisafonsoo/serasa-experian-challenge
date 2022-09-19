package com.luisafonso.people.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.luisafonso.people.dto.request.ScoreRequestDTO;
import com.luisafonso.people.repository.ScoreRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCORE")
public class Score {

    @Id
    private String descricao;

    private Integer inicial;

    @Column(name = "FINAL")
    private Integer finall;

    public Score(ScoreRequestDTO dto) {
        this.descricao = dto.getScoreDescricao();
        this.inicial = dto.getInicial();
        this.finall = dto.getFinall();
    }

}
