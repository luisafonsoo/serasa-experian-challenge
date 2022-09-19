package com.luisafonso.people.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luisafonso.people.dto.request.JwtRequestDTO;
import com.luisafonso.people.dto.response.JwtResponseDTO;
import com.luisafonso.people.service.UserAuthService;
import com.luisafonso.people.util.JwtUtility;

@RestController
@RequestMapping(value = "/authenticate")
public class AuthController {
    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserAuthService userService;

    @PostMapping
    public JwtResponseDTO authenticate(@RequestBody JwtRequestDTO jwtRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()));

            final UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());

            final String token = jwtUtility.generateToken(userDetails);

            return new JwtResponseDTO(token);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (UsernameNotFoundException exc) {
            throw new Exception("INVALID_USER", exc);
        }

    }
}
