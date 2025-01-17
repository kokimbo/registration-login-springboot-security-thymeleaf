package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Component
@Table(name="roles")
@EqualsAndHashCode(exclude = "roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rol_seq")
    @SequenceGenerator(name = "rol_seq", sequenceName = "SEQ_ROL_ID", allocationSize = 1)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;
}
