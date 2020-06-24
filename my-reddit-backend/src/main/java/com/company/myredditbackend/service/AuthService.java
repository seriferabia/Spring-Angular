package com.company.myredditbackend.service;

import com.company.myredditbackend.exceptions.SpringRedditException;
import com.company.myredditbackend.persistence.dto.RegisterRequest;
import com.company.myredditbackend.persistence.model.NotificationEmail;
import com.company.myredditbackend.persistence.model.User;
import com.company.myredditbackend.persistence.model.VerificationToken;
import com.company.myredditbackend.persistence.repository.UserRepository;
import com.company.myredditbackend.persistence.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .created(Instant.now())
                .enabled(false)
                .build();
        userRepository.save(user);

        String token = generateVerificationToken(user);
        NotificationEmail email = new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to My Fancy Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8080/api/auth/accountVerification/" + token);
        mailService.sendMail(email);
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElseThrow(() ->
                new SpringRedditException("Invalid Token"));

        fetchUserAndEnable(verificationToken);
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new SpringRedditException("User not found with name: " + username));
        user.setEnabled(true);
        userRepository.save(user);

    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);

        return token;
    }
}
