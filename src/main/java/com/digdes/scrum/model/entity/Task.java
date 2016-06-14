package com.digdes.scrum.model.entity;
import com.digdes.scrum.model.enums.TaskStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Task")
@Data

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    //
    @Column (name = "description", length = 255, nullable = false)
    private String description;
    //
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @JoinColumn(name = "creator")
    @ManyToOne
    private User creator;

    @JoinColumn(name = "sprint")
    @ManyToOne
    private Sprint sprint;

    public Task() {

    }

}
