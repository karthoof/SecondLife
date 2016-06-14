package com.digdes.scrum.dao;
import com.digdes.scrum.model.entity.*;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by artemkopytok on 10.06.16.
 */
public interface ProjectDao extends CrudRepository<Project, Long> {

//
    List<Project> findByName (String name);

}
