package com.digdes.scrum.model.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Sprint")
@Data

public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //
    @Column(name = "number")
    private Long number;
    //
    @Column(name = "name", length = 30, nullable = false)
    private String name;
    //
    @Column(name = "firstDay")
    private Date firstDay;
    //
    @Column(name = "lastDay")
    private Date lastDay;

    public Sprint() {

    }

}
