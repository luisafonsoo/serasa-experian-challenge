package com.luisafonso.people.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luisafonso.people.model.UserAuth;
import com.luisafonso.people.repository.UserAuthRepository;

import java.util.Optional;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserAuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserAuth> optional = authRepository.findByUsername(username);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new UsernameNotFoundException("User not found");
    }
}