package com.example.registrationlogindemo.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "alquileres")
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alquiler_seq")
    @SequenceGenerator(name = "alquiler_seq", sequenceName = "SEQ_ALQUILER_ID", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "COCHE_ID", referencedColumnName = "ID")
    private Coche coche;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAlquiler;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;

    @Column(nullable = false)
    private Double importe;

}
