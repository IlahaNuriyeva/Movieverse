package az.developia.movieverse.repository;

import az.developia.movieverse.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String  username);
}
