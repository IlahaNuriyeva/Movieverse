package az.developia.movieverse.service;

import az.developia.movieverse.dto.SignInRequest;
import az.developia.movieverse.dto.SignInResponse;

public interface AuthService {
    SignInResponse signIn(SignInRequest request);
}
