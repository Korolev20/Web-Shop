package com.exampleqq.myWebProgr.JWTAuth.services;

import com.exampleqq.myWebProgr.JWTAuth.models.Users;
import com.exampleqq.myWebProgr.JWTAuth.repositories.UsersRepository;
import com.exampleqq.myWebProgr.JWTAuth.security.HumanDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumanDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public HumanDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> human = usersRepository.findByEmail(email);

        if (human.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new HumanDetails(human.get());
    }
}