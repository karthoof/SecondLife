package com.digdes.scrum.model.entity;
import com.digdes.scrum.model.enums.BusyStatus;
import com.digdes.scrum.model.enums.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    //
    @Column(name = "lastName", length = 40, nullable = false)
    private String lastName;
    //
    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private Role roles;
    //
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BusyStatus status;
    //
    public User() {

    }


}
