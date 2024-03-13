package com.exampleqq.myWebProgr.JWTAuth.controllers;

import com.exampleqq.myWebProgr.JWTAuth.dto.AuthDTO;
import com.exampleqq.myWebProgr.JWTAuth.dto.UsersDTO;
import com.exampleqq.myWebProgr.JWTAuth.models.Users;
import com.exampleqq.myWebProgr.JWTAuth.security.JWTCreate;
import com.exampleqq.myWebProgr.JWTAuth.services.RegistrationService;
import com.exampleqq.myWebProgr.JWTAuth.utils.UsersValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")


public class AuthController {

    private final RegistrationService registrationService;
    private final UsersValidator personValidator;
    private final JWTCreate jwtCreate;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(RegistrationService registrationService, UsersValidator personValidator, JWTCreate jwtCreate, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtCreate = jwtCreate;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid UsersDTO personDTO,
                                                   BindingResult bindingResult) {
        Users human = convertToPerson(personDTO);

        personValidator.validate(human, bindingResult);

        if (bindingResult.hasErrors()) {
            return Map.of("message", "There is already a human with the same email or not correct password");
        }

        registrationService.register(human);

        String token = jwtCreate.generateToken(human.getEmail());
        return Map.of("jwt-token", token);
    }


    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect data");
        }

        String token = jwtCreate.generateToken(authenticationDTO.getEmail());
        return Map.of("jwt-token", token);
    }


    public Users convertToPerson(UsersDTO personDTO) {
        return this.modelMapper.map(personDTO,Users.class);
    }
}