package az.developia.movieverse.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import az.developia.movieverse.model.User;
import az.developia.movieverse.dto.CreateUserRequest;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    public abstract User createUserRequestToUser(CreateUserRequest request);

}
