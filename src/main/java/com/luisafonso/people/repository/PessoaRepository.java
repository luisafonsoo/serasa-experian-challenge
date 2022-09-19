package com.luisafonso.people.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.luisafonso.people.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer>, JpaRepository<Pessoa, Integer> {

}
