package com.ncardozo.hr_oasis_demo.validator;

import com.ncardozo.hr_oasis_demo.dto.ApiDTO;
import com.ncardozo.hr_oasis_demo.exception.InvalidDTOException;
import lombok.AllArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class UserValidator {

    private String regexEmail;
    private String regexPassword;

    public void validate(ApiDTO dto){
        Matcher emailMatcher = Pattern.compile(regexEmail).matcher(dto.getEmail());
        Matcher passwordMatcher = Pattern.compile(regexPassword).matcher(dto.getPassword());

        if(!emailMatcher.matches()){
            throw new InvalidDTOException("formato de email inválido.");
        }

        if(!passwordMatcher.matches()){
            throw new InvalidDTOException("formato de password inválido.");
        }
    }
}
