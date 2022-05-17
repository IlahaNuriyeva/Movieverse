package az.developia.movieverse.service;

import az.developia.movieverse.dto.CheckOtpRequest;
import az.developia.movieverse.dto.CreateOtpResponse;

public interface OtpService {
    CreateOtpResponse createOtp(String email);

    void checkOtp(CheckOtpRequest request);
}
