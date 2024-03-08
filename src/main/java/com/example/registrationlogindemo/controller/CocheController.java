package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Alquiler;
import com.example.registrationlogindemo.entity.Coche;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.AlquilerService;
import com.example.registrationlogindemo.service.CocheService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class CocheController {

    private final CocheService cocheService;
    private final AlquilerService alquilerService;
    private final UserService userService;

    @Autowired
    private Coche coche;

    public CocheController(CocheService cocheService, UserService userService, AlquilerService alquilerService) {
        this.cocheService = cocheService;
        this.userService = userService;
        this.alquilerService = alquilerService;
    }

    @GetMapping("/alquilar")
    public String alquilarCoche(@RequestParam("id") Long id, @RequestParam("idCoche") Long idCoche, @RequestParam("cantidad") int cantidad){
        Coche cocheSelect = cocheService.getById(idCoche);
        User user = userService.findById(id);
        long tiempoDespuesDeSumar = new Date().getTime() + (30L * 24 * 60 * 60 * 1000 * cantidad);

        Alquiler alquiler = Alquiler.builder()
                .coche(cocheSelect)
                .usuario(user)
                .fechaAlquiler(new Date())
                .fechaDevolucion(new Date(tiempoDespuesDeSumar))   // new Date pero con los meses seleccionados sumados
                .estado("ALQUILADO")
                .importe(cantidad*cocheSelect.getAlquilerMensual())
                .build();

        cocheSelect.getAlquileres().add(alquiler);
        user.getAlquileres().add(alquiler);

        if(alquilerService.insertUpdate(alquiler)){
            return "redirect:/";
        }else{
            return "redirect:/?errorAlquiler";
        }
    }


    @GetMapping("/cancelarAlquiler")
    public String cancelarAlquiler(@RequestParam("idAlquiler") Long idAlquiler){
        Alquiler alquiler = alquilerService.getById(idAlquiler);
        if(alquilerService.remove(alquiler)){
            return "redirect:/?cancelar="+alquiler.getCoche().getNombre();
        }else{
            return "redirect:/?errorAlquiler";
        }
    }

    @GetMapping("/pagarAlquiler")
    public String pagarAlquiler(@RequestParam("idAlquiler") Long idAlquiler){
        Alquiler alquiler = alquilerService.getById(idAlquiler);
        alquiler.setEstado("PAGADO");
        if(alquilerService.insertUpdate(alquiler)){
            return "redirect:/?pagado="+alquiler.getImporte();
        }else{
            return "redirect:/?errorAlquiler";
        }
    }

    @GetMapping("/opcionesCoches")
    public String opcionCoche(Model model, @RequestParam(name = "id", required = false) Long idCoche){
        if (idCoche!=null){
            Coche coche = cocheService.getById(idCoche);
            model.addAttribute("alquilerCoches", coche.getAlquileres());
        }
        model.addAttribute("coches", cocheService.getAll());
        return "opcionCoches";
    }

    @GetMapping("/removeCoche")
    public String removeCoche(@RequestParam("id") Long id){
        Coche coche = cocheService.getById(id);
        if(!coche.getAlquileres().isEmpty()){
            coche.getAlquileres().forEach(alquiler ->{
                alquiler.setCoche(null);
                //Realmente daria igual en que estado se encontraria el alquiler porque el coche seria retirado
                alquiler.setEstado("PAGADO");
                alquilerService.insertUpdate(alquiler);
            });
        }
        coche.getAlquileres().clear();
        cocheService.remove(id);
        return "redirect:/opcionesCoches";
    }


    @GetMapping("/addCocheAdmin")
    public String addCoche(Model model){
        model.addAttribute("coche", coche);
        return "addCoche";
    }

    @PostMapping("/addCocheAdmin")
    public String addAdminPost(@Valid @ModelAttribute("coche") Coche coche,
                               BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("coche", coche);
            return "addCoche";
        }
        cocheService.saveUpdateCoche(coche);
        return "redirect:/opcionesCoches?success";
    }

}
