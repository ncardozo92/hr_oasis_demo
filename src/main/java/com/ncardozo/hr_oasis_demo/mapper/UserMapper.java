package com.ncardozo.hr_oasis_demo.mapper;

import com.ncardozo.hr_oasis_demo.dto.ApiDTO;
import com.ncardozo.hr_oasis_demo.dto.PhoneDTO;
import com.ncardozo.hr_oasis_demo.entity.Phone;
import com.ncardozo.hr_oasis_demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapFrom(ApiDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhones(dto.getPhones().stream().map(this::mapPhone).collect(Collectors.toList()));

        return user;
    }

    private Phone mapPhone(PhoneDTO dto) {
        Phone phone = new Phone();
        phone.setNumber(dto.getNumber());
        phone.setCityCode(dto.getCityCode());
        phone.setCountryCode(dto.getCountryCode());
        return phone;
    }

    public ApiDTO mapTo(User user){
        ApiDTO dto = new ApiDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setCreated(user.getCreated());
        dto.setLastLogin(user.getLastLogin());
        dto.setModified(user.getModified());
        dto.setToken(user.getToken());
        dto.setPhones(user.getPhones().stream().map(this::mapTo).collect(Collectors.toList()));

        return dto;
    }

    private PhoneDTO mapTo(Phone phone) {
        PhoneDTO dto = new PhoneDTO();

        dto.setId(phone.getId());
        dto.setNumber(phone.getNumber());
        dto.setCityCode(phone.getCityCode());
        dto.setCountryCode(phone.getCountryCode());

        return dto;
    }
}
