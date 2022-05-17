package az.developia.movieverse.repository;

import az.developia.movieverse.model.Movie;

import java.util.Optional;

public interface MovieRepository extends CrudRepository<Movie, Long>{
    Optional<Movie> findByKey(String keyId);
}
