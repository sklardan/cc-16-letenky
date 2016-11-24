package cz.codecamp.database;

import cz.codecamp.classes.Location;
import cz.codecamp.classes.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jakubbares on 11/23/16.
 */


public interface LocationRepository extends CrudRepository<User, Long> {

    Location findByCode(String code);
    Location findByCity(String city);


}
