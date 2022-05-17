package az.developia.movieverse.mapper;

import az.developia.movieverse.dto.CreateUserRequest;
import az.developia.movieverse.model.LoginDetail;
import az.developia.movieverse.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class LoginDetailMapper {
    public static final LoginDetailMapper INSTANCE = Mappers.getMapper(LoginDetailMapper.class);
    public abstract LoginDetail toLoginDetail(CreateUserRequest request, User user);
}
