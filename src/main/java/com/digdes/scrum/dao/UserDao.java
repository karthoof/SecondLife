package com.digdes.scrum.dao;
import com.digdes.scrum.model.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by artemkopytok on 09.06.16.
 */
public interface UserDao extends CrudRepository <User, Long> {

    List<User> findByName (String name);
    List<User> findAll();
}
