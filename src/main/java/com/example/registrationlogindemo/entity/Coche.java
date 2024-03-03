package com.example.registrationlogindemo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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

    @Column
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @Column
    @NotEmpty(message = "El campo marca no puede estar vacio")
    private String marca;

    @Column
    @NotEmpty(message = "El campo alquiler no puede estar vacio")
    private double alquilerMensual;

    @Column
    @NotEmpty(message = "El campo kilometros no puede estar vacio")
    private int kilometros;

    @Column
    @NotEmpty(message = "El campo caballos no puede estar vacio")
    private int caballos;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;
}
