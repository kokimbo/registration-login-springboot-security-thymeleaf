package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    void saveUserAdmin(User user);
    void mergeUser(User user);

    User findByEmail(String email);
    User findById(Long id);

    List<UserDto> findAllUsers();
    List<User> findAll();

    boolean remove(Long id);

    boolean update(User user);


}
