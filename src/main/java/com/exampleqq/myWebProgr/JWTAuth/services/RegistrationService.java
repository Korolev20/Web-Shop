package com.exampleqq.myWebProgr.JWTAuth.services;

import com.exampleqq.myWebProgr.JWTAuth.models.Users;
import com.exampleqq.myWebProgr.JWTAuth.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(Users users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
    }
}
