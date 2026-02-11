package org.example.baitapthuctap.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "names")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
