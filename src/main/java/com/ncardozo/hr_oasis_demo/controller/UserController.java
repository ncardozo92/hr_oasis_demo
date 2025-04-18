package com.ncardozo.hr_oasis_demo.controller;

import com.ncardozo.hr_oasis_demo.dto.ApiDTO;
import com.ncardozo.hr_oasis_demo.dto.ErrorDTO;
import com.ncardozo.hr_oasis_demo.exception.InvalidDTOException;
import com.ncardozo.hr_oasis_demo.exception.UserAlreadyExistsException;
import com.ncardozo.hr_oasis_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

   @Autowired
   private UserService userService;

    @PostMapping
    public ResponseEntity create(@RequestBody ApiDTO dto){
        return ResponseEntity.ok(userService.create(dto));
    }

    @ExceptionHandler(InvalidDTOException.class)
    public ResponseEntity<ErrorDTO> handleInvalidDTOException(InvalidDTOException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("Email registrado"));
    }
}
