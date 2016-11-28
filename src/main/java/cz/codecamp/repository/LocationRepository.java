package cz.codecamp.repository;

import cz.codecamp.model.Location;
import cz.codecamp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakubbares on 11/23/16.
 */

@Repository
public interface LocationRepository extends CrudRepository<User, Long> {

    Location findByCode(String code);
    Location findByCity(String city);


}
