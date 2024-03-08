package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.AlquilerService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    private User user;
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
                alquiler.setEstado("PAGADO");
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
    public String modificar(@Valid @ModelAttribute("usuario") User usuario, BindingResult result){
        User userDB = userService.findById(usuario.getId());
        usuario.setEmail(userDB.getEmail());
        userDB.setPassword(usuario.getPassword());
        userDB.setName(usuario.getName());

        if (result.hasErrors()) {
            return "redirect:/modify?id="+usuario.getId();
        }

        userService.mergeUser(userDB);
        return "redirect:/opcionesUsuarios";
    }

    @GetMapping("/removeByAdmin")
    public String remove(@RequestParam("id") Long id){
        User user = userService.findById(id);
        if(!user.getAlquileres().isEmpty()){
            user.getAlquileres().forEach(alquiler ->{
                alquiler.setUsuario(null);
                alquiler.setEstado("NO ALQUILADO");
                alquilerService.insertUpdate(alquiler);
            });
        }
        user.getAlquileres().clear();
        userService.remove(id);
        return "redirect:/opcionesUsuarios";
    }

    @GetMapping("/addAdmin")
    public String addAdmin(Model model){
        model.addAttribute("user", user);
        return "addAdministrador";
    }

    @PostMapping("/addAdmin")
    public String addAdminPost(@Valid @ModelAttribute("user") User user,
                               BindingResult result, Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Este email ya esta resgistrado, inicie sesion");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "addAdministrador";
        }
        userService.saveUserAdmin(user);
        return "redirect:/opcionesUsuarios?success";
    }
}
