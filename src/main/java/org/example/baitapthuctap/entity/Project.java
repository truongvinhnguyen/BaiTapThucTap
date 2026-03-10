package org.example.baitapthuctap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "names")
    private String name;

    @Column(name = "descriptions")
    private String description;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Task> tasks;
}
