//package com.example.registrationlogindemo.pruebas;
//
//import com.example.registrationlogindemo.entity.Coche;
//import com.example.registrationlogindemo.entity.Role;
//import com.example.registrationlogindemo.entity.User;
//import com.example.registrationlogindemo.repository.CocheRepository;
//import com.example.registrationlogindemo.repository.RoleRepository;
//import com.example.registrationlogindemo.repository.UserRepository;
//import com.example.registrationlogindemo.service.CocheService;
//import com.example.registrationlogindemo.service.UserService;
//import com.example.registrationlogindemo.service.impl.CocheServiceImpl;
//import com.example.registrationlogindemo.service.impl.UserServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//@SpringBootApplication
//@Slf4j
//public class PruebasAdmin implements CommandLineRunner {
//
//    public static void main(String[] args) {
//        SpringApplication.run(PruebasAdmin.class, args);
//    }
//
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private CocheRepository cocheRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public PruebasAdmin() {
//    }
//
//    public PruebasAdmin(UserRepository userRepository, CocheRepository cocheRepository, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.cocheRepository = cocheRepository;
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void  run(String[] args) throws Exception  {
//
//        //Los usuarios con rol user y alquileres se pueden hacer desde la aplicacion facilmente. Tambien podras crear mas usuarios con rol ADMIN desde el admin
//        //que se instancia aqui
//        User admin = User.builder()
//                .name("Bautista Jose")
//                .email("bautista@gmail.com")
//                .password("1234")
//                .roles(Arrays.asList(Role.builder().name("ROLE_ADMIN").build()))
//                .build();
//        String hashsed = passwordEncoder.encode(admin.getPassword());
//        admin.setPassword(hashsed);
//        userRepository.save(admin);
//
//        Coche coche1 = Coche.builder().nombre("Picasso")
//                .marca("Citroen")
//                .caballos(150)
//                .kilometros(10000)
//                .alquilerMensual(199.9)
//                .build();
//        cocheRepository.saveAndFlush(coche1);
//        // Coche 2
//        Coche coche2 = Coche.builder().nombre("Golf")
//                .marca("Volkswagen")
//                .caballos(200)
//                .kilometros(8000)
//                .alquilerMensual(249.5)
//                .build();
//        cocheRepository.saveAndFlush(coche2);
//// Coche 3
//        Coche coche3 = Coche.builder().nombre("Civic")
//                .marca("Honda")
//                .caballos(180)
//                .kilometros(12000)
//                .alquilerMensual(219.0)
//                .build();
//        cocheRepository.saveAndFlush(coche3);
//// Coche 4
//        Coche coche4 = Coche.builder().nombre("3 Series")
//                .marca("BMW")
//                .caballos(250)
//                .kilometros(6000)
//                .alquilerMensual(299.99)
//                .build();
//        cocheRepository.saveAndFlush(coche4);
//// Coche 5
//        Coche coche5 = Coche.builder().nombre("Civic Type R")
//                .marca("Honda")
//                .caballos(300)
//                .kilometros(5000)
//                .alquilerMensual(279.75)
//                .build();
//        cocheRepository.saveAndFlush(coche5);
//// Coche 6
//        Coche coche6 = Coche.builder().nombre("A4")
//                .marca("Audi")
//                .caballos(190)
//                .kilometros(15000)
//                .alquilerMensual(269.5)
//                .build();
//        cocheRepository.saveAndFlush(coche6);
//// Coche 7
//        Coche coche7 = Coche.builder().nombre("Fiesta")
//                .marca("Ford")
//                .caballos(120)
//                .kilometros(18000)
//                .alquilerMensual(179.9)
//                .build();
//        cocheRepository.saveAndFlush(coche7);
//// Coche 8
//        Coche coche8 = Coche.builder().nombre("Clio")
//                .marca("Renault")
//                .caballos(110)
//                .kilometros(20000)
//                .alquilerMensual(159.99)
//                .build();
//        cocheRepository.saveAndFlush(coche8);    }
//}
