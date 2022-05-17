package az.developia.movieverse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpCache {
    private String email;
    private String otpCode;
    @Builder.Default
    private Integer tries = 0;
    private Instant expireAt;

    public void setExpireAt(){
        this.expireAt = Instant.now().plus(3, ChronoUnit.MINUTES);
    }
    public boolean isExpired(){
        return this.expireAt.isBefore(Instant.now());
    }

}
