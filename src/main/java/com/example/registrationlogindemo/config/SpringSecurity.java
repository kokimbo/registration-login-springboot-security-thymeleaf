package com.example.registrationlogindemo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/opcionesAdministrador/**").hasRole("ADMIN")
                                .requestMatchers("/opcionesUsuarios/**").hasRole("ADMIN")
                                .requestMatchers("/modify/**").hasRole("ADMIN")
                                .requestMatchers("/opcionesCoches/**").hasRole("ADMIN")
                                .requestMatchers("/removeByAdmin/**").hasRole("ADMIN")
                                .requestMatchers("/removeCoche/**").hasRole("ADMIN")
                                .requestMatchers("/addAdmin/**").hasRole("ADMIN")
                                .requestMatchers("/addCocheAdmin/**").hasRole("ADMIN")
                                .requestMatchers("/alquilar/**").hasRole("USER")
                                .requestMatchers("/cancelarAlquiler/**").hasRole("USER")
                                .requestMatchers("/pagarAlquiler/**").hasRole("USER")
                                .requestMatchers("/remove/**").authenticated()
                                .requestMatchers("/login/**").permitAll()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .successHandler(manejadorSuccessUsuarios())
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );//exceptionHandling()
//                .accessDeniedPage("/error");
        return http.build();
    }

    //Metodo para aplicar una url u otra en funcion de si el usuario que se logue es admin o no
    private AuthenticationSuccessHandler manejadorSuccessUsuarios() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Override
            protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                boolean isAdmin = authentication.getAuthorities().stream()
                        .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
                return isAdmin ? "/opcionesAdministrador" : "/";  //La respuesta en el false la tengo que cambiar, bueno realmente las 2
            }
        };
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
