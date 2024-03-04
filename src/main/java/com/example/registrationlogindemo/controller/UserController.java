package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/remove/{idUser}")
    public String remove(@PathVariable long idUser){
        User user = userService.findById(idUser);
        if(!user.getCoches().isEmpty()){
            user.getCoches().forEach(coche -> coche.setUser(null));
        }

        if(userService.remove(idUser))
            return "redirect:/logout";
        else
            throw new RuntimeException("Error al borrar el usuario");
    }
}
