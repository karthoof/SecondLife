package com.digdes.scrum.model.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Project")
@Data //Конструктор от ломбока

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    //
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    //
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    //
    @Column(name = "users")
    @ElementCollection
    private List<User> users;
    //
    @Column(name = "tasks")
    @ElementCollection
    private List<Task> tasks;

    public Project() {

    }
}
