package az.developia.movieverse.service;

import az.developia.movieverse.dto.CreateUserRequest;
import az.developia.movieverse.dto.UserCreatedResponse;

public interface UserService {
    UserCreatedResponse createUser(CreateUserRequest request);
}
