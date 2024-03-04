package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.security.CustomUserDetails;
import com.example.registrationlogindemo.service.CocheService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private CocheService cocheService;
    private CustomUserDetails userDetails;

    public AuthController(UserService userService, CocheService cocheService) {
        this.cocheService = cocheService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String inicio(Model model, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        // Acceder al nombre de usuario
        if(!authentication.getPrincipal().toString().equals("anonymousUser")){
            userDetails = (CustomUserDetails) authentication.getPrincipal();
        }

        if(userDetails!=null){
            List<Coche> cochesUsuario = cocheService.getAllByUsuario(userService.findByEmail(userDetails.getEmail()));
            model.addAttribute("cochesUsuario", cochesUsuario);
        }

        model.addAttribute("coches", cocheService.getAll());

        return "index";
    }

//    @GetMapping("/error")
//    public String errorHandling() {
//        return "redirect:/login";
//    }

//    @GetMapping("/otra-pagina-de-error")
//    public String errorDelError() {
//        return "opcionesAdmin";
//    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/saveUser")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Este email ya esta resgistrado, inicie sesion");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/opcionesAdministrador")
    public String opcionesDeAdmin(){
        return "opcionesAdmin";
    }
}
