package com.ncardozo.hr_oasis_demo.service;

import com.ncardozo.hr_oasis_demo.dto.ApiDTO;
import com.ncardozo.hr_oasis_demo.entity.User;
import com.ncardozo.hr_oasis_demo.exception.UserAlreadyExistsException;
import com.ncardozo.hr_oasis_demo.mapper.UserMapper;
import com.ncardozo.hr_oasis_demo.repository.UserRepository;
import com.ncardozo.hr_oasis_demo.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserService {

    private UserValidator userValidator;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       @Value("${regex.email}") String emailRegex,
                       @Value("${regex.password}") String passwordRegex){

        this.userValidator = new UserValidator(emailRegex, passwordRegex);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public ApiDTO create(ApiDTO dto){

        userValidator.validate(dto);

        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }

        User user = userMapper.mapFrom(dto);
        user.setCreated(LocalDate.now());
        user.setModified(LocalDate.now());
        user.setLastLogin(LocalDate.now());
        user.setToken(UUID.randomUUID().toString());

        return userMapper.mapTo(userRepository.save(user));
    }
}
