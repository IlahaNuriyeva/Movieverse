package az.developia.movieverse.service.impl;

import az.developia.movieverse.dto.CheckOtpRequest;
import az.developia.movieverse.dto.CreateOtpResponse;
import az.developia.movieverse.dto.OtpCache;
import az.developia.movieverse.event.MailSenderEvent;
import az.developia.movieverse.exception.UnauthorizedException;
import az.developia.movieverse.model.Mail;
import az.developia.movieverse.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpServiceImpl implements OtpService {
    private ApplicationEventPublisher eventPublisher;
    private final ConcurrentHashMap<String, OtpCache> otpCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Instant> blockCache = new ConcurrentHashMap<>();

    @Override
    public CreateOtpResponse createOtp(String email) {
        if (blockCache.containsKey(email)){
            var blockedTime = blockCache.get(email);
            var isBlocked = blockedTime.plus(10, ChronoUnit.MINUTES).isAfter(Instant.now());
            if(isBlocked){
                throw new UnauthorizedException("excpetion.auth.blocked.temporarily");
            } else {
               blockCache.remove(email);
            }
        }
        int randomPin = (int) (Math.random() * 9000) + 1000;
        String otp = String.valueOf(randomPin);
        eventPublisher.publishEvent(new MailSenderEvent(
                Mail.builder()
                        .to(email)
                        .subject("OTP CODE")
                        .html(true)
                        .template(new Mail.Template("otp.html",
                                Map.of("otpCode", otp)))
                        .build()
        ));
        var sessionId = UUID.randomUUID().toString();
        var otpCacheDto = OtpCache.builder()
                .otpCode(otp)
                .email(email)
                .build();
        otpCacheDto.setExpireAt();
        otpCache.put(sessionId, otpCacheDto);
        return CreateOtpResponse.builder()
                .expireInSec(180)
                .sessionId(sessionId)
                .build();
    }



    @Override
    public void checkOtp(CheckOtpRequest request) {
        var otp = otpCache.get(request.getSessionId());
        var isContain = otp.getOtpCode().equals(request.getCode());
        if (!isContain){
            throw new UnauthorizedException("exception.auth.wrong-otp");
        }
        if (otp.getTries() >= 3){
            blockCache.put(otp.getEmail(), Instant.now());
            otpCache.remove(request.getCode());
            throw new UnauthorizedException("exception.auth.limit-exceeded");
        }
        if (otp.isExpired()){
            otpCache.remove(request.getCode());
            throw new UnauthorizedException("exception.auth.expired-otp");
        }
        otpCache.remove(request.getCode());

    }
}
