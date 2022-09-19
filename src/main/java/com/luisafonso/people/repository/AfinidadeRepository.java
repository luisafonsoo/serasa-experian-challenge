package com.luisafonso.people.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.luisafonso.people.model.Afinidade;

public interface AfinidadeRepository extends CrudRepository<Afinidade, Integer> {

    List<Afinidade> findByRegiao(String regiao);

}
