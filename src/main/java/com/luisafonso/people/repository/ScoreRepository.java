package com.luisafonso.people.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.luisafonso.people.model.Score;

public interface ScoreRepository extends CrudRepository<Score, String> {

    @Query("SELECT s FROM Score s where s.inicial <=  ?1 AND s.finall >= ?1")
    Optional<Score> findByDescricao(Integer score);

}
