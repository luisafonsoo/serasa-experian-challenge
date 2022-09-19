package com.luisafonso.people.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luisafonso.people.model.UserAuth;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuth, Integer> {

    Optional<UserAuth> findByUsername(String username);

}
