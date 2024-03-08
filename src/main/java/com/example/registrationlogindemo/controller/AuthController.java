package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Alquiler;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.security.CustomUserDetails;
import com.example.registrationlogindemo.service.AlquilerService;
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
    private AlquilerService alquilerService;
    private CustomUserDetails userDetails;

    public AuthController(UserService userService, CocheService cocheService, AlquilerService alquilerService) {
        this.cocheService = cocheService;
        this.userService = userService;
        this.alquilerService = alquilerService;
    }

    @RequestMapping("/")
    public String inicio(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Acceder al nombre de usuario
        //Probar en este if el metodo authentication.isAuthenticated(); para ver si se puede sustituir por esta mierda de condicion
        //Y efectivamente se puede, pero lo voy a dejar asi
        if(!authentication.getPrincipal().toString().equals("anonymousUser")){
            userDetails = (CustomUserDetails) authentication.getPrincipal();
        }

        if(userDetails!=null){
            List<Alquiler> cochesUsuario = alquilerService.getAllByUsuario(userService.findByEmail(userDetails.getEmail()));
            model.addAttribute("cochesUsuario", cochesUsuario);
        }

        model.addAttribute("coches", cocheService.getAll());

        return "index";
    }

//    @GetMapping("/error")
//    public String errorHandling() {
//        return "error";
//    }



    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

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


    @GetMapping("/opcionesAdministrador")
    public String opcionesDeAdmin(){
        return "opcionesAdmin";
    }
}
