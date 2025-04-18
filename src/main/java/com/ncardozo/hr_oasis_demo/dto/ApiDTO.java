package com.ncardozo.hr_oasis_demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class ApiDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDate created;
    private LocalDate modified;
    @JsonProperty("last_login")
    private LocalDate lastLogin;
    private String token;
    private List<PhoneDTO> phones;
}
