package com.example.registrationlogindemo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
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
    @NotNull(message = "El campo alquiler no puede estar vacio")
    private Double alquilerMensual;

    @Column(nullable = false)
    @NotNull(message = "El campo kilometros no puede estar vacio")
    private Integer kilometros;

    @Column(nullable = false)
    @NotNull(message = "El campo caballos no puede estar vacio")
    private Integer caballos;

    @OneToMany(mappedBy = "coche", fetch = FetchType.EAGER)
    private List<Alquiler> alquileres = new ArrayList<>();
}
