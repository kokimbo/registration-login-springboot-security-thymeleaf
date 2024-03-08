package com.example.registrationlogindemo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Component
@Table(name="coches")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "coche_seq")
    @SequenceGenerator(name = "coche_seq", sequenceName = "SEQ_COCHE_ID", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @Column(nullable = false)
    @NotEmpty(message = "El campo marca no puede estar vacio")
    private String marca;

    @Column(nullable = false)
    @Min(value = 100, message = "El valor debe ser mayor o igual a 100")
    @Max(value = 50000, message = "El valor debe ser menor o igual a 50000")
    @NotNull(message = "El campo alquiler no puede estar vacio")
    private Double alquilerMensual;

    @Column(nullable = false)
    @Max(value = 1000000, message = "Mejor llévalo a un desguace")
    @NotNull(message = "El campo kilometros no puede estar vacio")
    private Integer kilometros;

    @Column(nullable = false)
    @Min(value = 50, message = "El valor debe ser mayor o igual a 50 caballos")
    @Max(value = 2000, message = "Ningun coche convencional hasta la fecha tiene mas de 2000 caballos")
    @NotNull(message = "El campo caballos no puede estar vacio")
    private Integer caballos;

    @OneToMany(mappedBy = "coche", fetch = FetchType.EAGER)
    private List<Alquiler> alquileres = new ArrayList<>();
}
