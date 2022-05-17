package az.developia.movieverse.repository;

import az.developia.movieverse.model.LoginDetail;

import java.util.Optional;

public interface LoginDetailRepository extends CrudRepository<LoginDetail, Long>{

    Optional<LoginDetail> findByEmail(String email);

}
