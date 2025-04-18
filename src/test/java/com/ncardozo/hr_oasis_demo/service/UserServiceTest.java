package com.ncardozo.hr_oasis_demo.service;

import com.ncardozo.hr_oasis_demo.dto.ApiDTO;
import com.ncardozo.hr_oasis_demo.dto.PhoneDTO;
import com.ncardozo.hr_oasis_demo.entity.Phone;
import com.ncardozo.hr_oasis_demo.entity.User;
import com.ncardozo.hr_oasis_demo.mapper.UserMapper;
import com.ncardozo.hr_oasis_demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        String emailRegex="^[a-z0-9]+@[a-z0-9]+\\.[a-z0-9\\.]+$";
        String passwordRegex="^[a-zA-Z0-9]{4,}$";

        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserService(userRepository, new UserMapper(),emailRegex,passwordRegex);
    }


    @Test
    public void createUserSuccess(){
        ApiDTO dto = new ApiDTO();
        dto.setEmail("ncardozo19@gmail.com");
        dto.setName("Nicolás Cardozo");
        dto.setPassword("qwer1234");

        PhoneDTO phone = new PhoneDTO();
        phone.setCountryCode("54");
        phone.setCityCode("11");
        phone.setNumber("53289130");

        dto.setPhones(List.of(phone));

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(buildEntity());
        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        ApiDTO createdUser = userService.create(dto);

        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertNotNull(createdUser.getPhones().get(0).getId());
        Assertions.assertNotNull(createdUser.getCreated());
        Assertions.assertNotNull(createdUser.getLastLogin());
        Assertions.assertNotNull(createdUser.getModified());
        Assertions.assertNotNull(createdUser.getToken());
    }

    private User buildEntity(){
        User user = new User();

        user.setId(123L);
        user.setCreated(LocalDate.now());
        user.setModified(LocalDate.now());
        user.setLastLogin(LocalDate.now());
        user.setToken(UUID.randomUUID().toString());

        Phone phone = new Phone();
        phone.setId(1234L);

        user.setPhones(List.of(phone));

        return user;
    }
}
