package az.developia.movieverse.service.impl;

import az.developia.movieverse.dto.CreateUserRequest;
import az.developia.movieverse.dto.UserCreatedResponse;
import az.developia.movieverse.event.MailSenderEvent;
import az.developia.movieverse.mapper.LoginDetailMapper;
import az.developia.movieverse.mapper.UserMapper;
import az.developia.movieverse.model.Mail;
import az.developia.movieverse.repository.LoginDetailRepository;
import az.developia.movieverse.repository.UserRepository;
import az.developia.movieverse.service.UserService;
import az.developia.movieverse.util.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LoginDetailRepository loginDetailRepository;
    private final ApplicationEventPublisher publisher;
    @Transactional
    @Override
    public UserCreatedResponse createUser(CreateUserRequest request) {
        log.info("ActionLog.UserServiceImpl.createUser.start - request: {}", request);
        userRepository.findByUsername(request.getUsername())
                        .ifPresent(user -> {
                            throw new RuntimeException("exception.username.already-exist");
                        });
        request.setPassword(PasswordUtil.hash(request.getPassword()));

        var user = UserMapper.INSTANCE.createUserRequestToUser(request);
        user = userRepository.save(user);

        var loginDetail = LoginDetailMapper.INSTANCE
                        .toLoginDetail(request, user);
        loginDetailRepository.save(loginDetail);

        publisher.publishEvent(new MailSenderEvent(
                Mail.builder()
                        .to(loginDetail.getEmail())
                        .subject("Welcome " + user.getFirstname() + " " + user.getLastname())
                        .body("You're successfully registered!")
                        .build()
        ));

        log.info("ActionLog.UserServiceImpl.createUser.end - request: {}", request);
        return new UserCreatedResponse(user.getId());
    }
}
