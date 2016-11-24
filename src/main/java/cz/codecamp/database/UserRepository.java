package cz.codecamp.database;

import cz.codecamp.classes.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jakubbares on 11/17/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLoginName(String loginName);


}