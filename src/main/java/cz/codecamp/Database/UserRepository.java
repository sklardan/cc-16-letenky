package cz.codecamp.Database;

import cz.codecamp.Classes.Flight;
import cz.codecamp.Classes.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jakubbares on 11/17/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLoginName(String loginName);


}