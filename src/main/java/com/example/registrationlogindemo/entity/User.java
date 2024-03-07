package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="users")
@EqualsAndHashCode(exclude = "users")
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "SEQ_CLIENTE_ID", allocationSize = 1)
    private Long id;

    @Column(nullable=false)
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String name;

    @Column(nullable=false, unique=true)
    @Email
    @NotEmpty(message = "El email no puede estar vacio")
    private String email;

    @Column(nullable=false)
    @NotEmpty(message = "La contraseña no puede estar vacia")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Alquiler> alquileres = new ArrayList<>();
}
