package az.developia.movieverse.event;

import az.developia.movieverse.model.Mail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MailSenderEvent {
    private final Mail mail;
}
