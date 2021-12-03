package com.felece.ticketapi.backend.auth;

import com.felece.ticketapi.backend.entity.User;
import com.felece.ticketapi.backend.entity.vm.UserVM;
import com.felece.ticketapi.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @Autowired
    public AuthService(final UserRepository userRepository,
                       final PasswordEncoder passwordEncoder,
                       final TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public AuthResponse authenticate(final Credentials credentials) {
        User inDB = userRepository.findByEmail(credentials.getEmail());
        if (inDB == null) {
            System.out.println("User not found");
            throw new AuthException();
        }
        final boolean matches = passwordEncoder.matches(credentials.getPassword(), inDB.getPassword());
        if (!matches) {
            throw new AuthException();
        }
        final UserVM userVM = new UserVM(inDB);
        final String token = generateRandomToken();
        final Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setUser(inDB);
        tokenRepository.save(tokenEntity);
        final AuthResponse response = new AuthResponse();
        response.setUser(userVM);
        response.setToken(token);
        response.setRole(inDB.getRole());
        return response;
    }

    public UserDetails getUserDetails(final String token) {
        final Optional<Token> optionalToken = tokenRepository.findById(token);
        return optionalToken.<UserDetails>map(Token::getUser).orElse(null);
    }

    public String generateRandomToken() {
        return UUID.randomUUID().toString().replaceAll(",","");
    }

    public void clearToken(final String token) {
        tokenRepository.deleteById(token);
    }
}
