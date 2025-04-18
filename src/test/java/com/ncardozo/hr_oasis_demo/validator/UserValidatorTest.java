package com.ncardozo.hr_oasis_demo.validator;

import com.ncardozo.hr_oasis_demo.dto.ApiDTO;
import com.ncardozo.hr_oasis_demo.exception.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserValidatorTest {


    UserValidator userValidator = new UserValidator("^[a-z0-9]+@[a-z0-9]+\\.[a-z0-9\\.]+$",
    "^[a-zA-Z0-9]{4,}$");

    @Test
    public void validationSuccess(){
        ApiDTO dto = new ApiDTO();
        dto.setEmail("ncardozo19@gmail.com");
        dto.setName("Nicolás Cardozo");
        dto.setPassword("qwer1234");

        Assertions.assertDoesNotThrow(() -> userValidator.validate(dto));
    }

    @Test
    public void emailValidationFails(){
        ApiDTO dto1 = new ApiDTO();
        dto1.setEmail("ncardozo19gmail.com");
        dto1.setName("Nicolás Cardozo");
        dto1.setPassword("qwer1234");

        ApiDTO dto2 = new ApiDTO();
        dto2.setEmail("ncardozo19@gmail");
        dto2.setName("Nicolás Cardozo");
        dto2.setPassword("qwer1234");

        ApiDTO dto3 = new ApiDTO();
        dto3.setEmail("ncardozo19.com");
        dto3.setName("Nicolás Cardozo");
        dto3.setPassword("qwer1234");

        List<ApiDTO> cases = List.of(dto1,dto2,dto3);

        cases.forEach(dto -> Assertions.assertThrows(InvalidDTOException.class,
                () -> userValidator.validate(dto)));
    }

    @Test
    public void validatePasswordFails(){
        ApiDTO dto1 = new ApiDTO();
        dto1.setEmail("ncardozo19gmail.com");
        dto1.setName("Nicolás Cardozo");
        dto1.setPassword("qwe");

        ApiDTO dto2 = new ApiDTO();
        dto2.setEmail("ncardozo19gmail.com");
        dto2.setName("Nicolás Cardozo");
        dto2.setPassword("");

        ApiDTO dto3 = new ApiDTO();
        dto3.setEmail("ncardozo19gmail.com");
        dto3.setName("Nicolás Cardozo");
        dto3.setPassword("qw!+-*34");

        List<ApiDTO> cases = List.of(dto1,dto2,dto3);

        cases.forEach(dto -> Assertions.assertThrows(InvalidDTOException.class,
                () -> userValidator.validate(dto)));
    }
}
