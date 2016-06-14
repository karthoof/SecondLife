package com.digdes.scrum.dao;
import com.digdes.scrum.model.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by artemkopytok on 10.06.16.
 */
public interface TaskDao extends CrudRepository<Task, Long> {

    List<Task> findByName (String name);


}
