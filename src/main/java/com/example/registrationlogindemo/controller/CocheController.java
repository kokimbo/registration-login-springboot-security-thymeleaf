package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.CocheService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CocheController {

    private final CocheService cocheService;
    private final UserService userService;

    public CocheController(CocheService cocheService, UserService userService) {
        this.cocheService = cocheService;
        this.userService = userService;
    }

    @GetMapping("/alquilar")
    public String alquilarCoche(@RequestParam("id") Long id, @RequestParam("idCoche") Long idCoche){
        Coche coche = cocheService.getById(idCoche);
        User user = userService.findById(id);
        coche.setUser(user);
        user.getCoches().add(coche);
        if(cocheService.saveUpdateCoche(coche)){
            return "redirect:/";
        }else{
            return "redirect:/?errorAlquiler";
        }
    }


    @GetMapping("/cancelarAlquiler")
    public String cancelarAlquiler(@RequestParam("id") Long id, @RequestParam("idCoche") Long idCoche){
        Coche coche = cocheService.getById(idCoche);
        User user = userService.findById(id);
        coche.setUser(null);
        user.getCoches().remove(coche);
        if(cocheService.saveUpdateCoche(coche)){
            return "redirect:/";
        }else{
            return "redirect:/?errorAlquiler";
        }
    }
}
