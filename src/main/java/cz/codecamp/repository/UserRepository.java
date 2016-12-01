package cz.codecamp.repository;

import cz.codecamp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jakubbares on 11/17/16.
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailLogin(String emailLogin);
    User findByUserName(String userName);

}