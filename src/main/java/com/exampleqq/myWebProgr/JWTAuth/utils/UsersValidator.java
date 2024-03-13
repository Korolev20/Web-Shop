package com.exampleqq.myWebProgr.JWTAuth.utils;

import com.exampleqq.myWebProgr.JWTAuth.models.Users;
import com.exampleqq.myWebProgr.JWTAuth.services.HumanDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsersValidator implements Validator {

    private final HumanDetailsService humanDetailsService;

    @Autowired
    public UsersValidator(HumanDetailsService humanDetailsService) {
        this.humanDetailsService=humanDetailsService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Users human = (Users) target;

        try {
            humanDetailsService.loadUserByUsername(human.getEmail());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("email", "", "..");
    }
}