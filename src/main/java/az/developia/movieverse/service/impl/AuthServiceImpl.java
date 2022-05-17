package az.developia.movieverse.service.impl;

import az.developia.movieverse.dto.SignInRequest;
import az.developia.movieverse.dto.SignInResponse;
import az.developia.movieverse.exception.BadCredentials;
import az.developia.movieverse.exception.NotFoundException;
import az.developia.movieverse.repository.LoginDetailRepository;
import az.developia.movieverse.service.AuthService;
import az.developia.movieverse.service.OtpService;
import az.developia.movieverse.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
   private final LoginDetailRepository loginDetailRepository;
   private final OtpService otpService;



    @Override
    public SignInResponse signIn(SignInRequest request) {
        log.info("ActionLog.{}.signIn.start - request: {}", getClass().getSimpleName(), request);

        var loginDetail = loginDetailRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new NotFoundException("exception.auth.user.not-found"));

        if(!PasswordUtil.checkPassword(request.getPassword(), loginDetail.getPassword())){
            throw new BadCredentials();
        }
        var otpResponse = otpService.createOtp(request.getEmail());
        log.info("ActionLog.{}.signIn.end - request: {}", getClass().getSimpleName(), request);
        return new SignInResponse(otpResponse.getSessionId());

    }
}
