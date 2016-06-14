package com.digdes.scrum.dao;
import com.digdes.scrum.model.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by artemkopytok on 10.06.16.
 */
public interface SprintDao extends CrudRepository<Sprint, Long> {

    List<Sprint> findByName (String name);


}
