package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.AlquilerService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;
    private AlquilerService alquilerService;

    public UserController(UserService userService, AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
        this.userService = userService;
    }

    @GetMapping("/remove/{idUser}")
    public String remove(@PathVariable long idUser){
        User user = userService.findById(idUser);
        if(!user.getAlquileres().isEmpty()){
            user.getAlquileres().forEach(alquiler ->{
                alquiler.setUsuario(null);
                alquiler.setEstado("NO ALQUILADO");
                alquilerService.insertUpdate(alquiler);
            });
        }
        user.getAlquileres().clear();
        if(userService.remove(idUser))
            return "redirect:/logout";
        else
            throw new RuntimeException("Error al borrar el usuario");
    }

    @GetMapping("/opcionesUsuarios")
    public String opcionAdmin(Model model){
        model.addAttribute("users", userService.findAll());
        return "opcionUsuarios";
    }

    @GetMapping("/modify")
    public String modificar(Model model, @RequestParam("id") Long id){
        model.addAttribute("usuario", userService.findById(id));
        return "modUser";
    }

    @PostMapping("/modify")
    public String modificar(@Valid @ModelAttribute("usuario") User usuario, BindingResult result, Model model){
        User userDB = userService.findById(usuario.getId());
        usuario.setEmail(userDB.getEmail());
        userDB.setPassword(usuario.getPassword());
        userDB.setName(usuario.getName());
        if (result.hasErrors()) {
            model.addAttribute("usuario", userService.findById(usuario.getId()));
            return "modUser";
        }
        userService.mergeUser(userDB);
        return "redirect:/opcionesUsuarios";
    }
}
