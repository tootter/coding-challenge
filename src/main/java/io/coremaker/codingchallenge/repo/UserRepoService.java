package io.coremaker.codingchallenge.repo;

import io.coremaker.codingchallenge.payload.SignInRequest;
import io.coremaker.codingchallenge.payload.SignupRequest;
import io.coremaker.codingchallenge.util.Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRepoService {

    private static final int TOKEN_LENGTH = 12;

    private final Map<String, User> emailToUserCache = new HashMap<>();
    private final Map<String, User> authTokenToUser = new HashMap<>();

    public void registerNewUser(SignupRequest signUpRequest) {

        User user = new User(signUpRequest.getName(), signUpRequest.getEmail(), Utils.md5Encode(signUpRequest.getPassword()));
        emailToUserCache.put(signUpRequest.getEmail(), user);
    }

    public String login(SignInRequest signInRequest) {

        User user = emailToUserCache.get(signInRequest.getEmail());
        if (user == null || !user.getPasswordHash().equals(Utils.md5Encode(signInRequest.getPassword())))
            return null;

        String token = Utils.generateRandomString(TOKEN_LENGTH);
        authTokenToUser.put(token, user);

        return token;
    }

    public User getUserDetails(String authToken) {

        return authTokenToUser.get(authToken);
    }

    public boolean emailExists(String email) {

        return emailToUserCache.containsKey(email);
    }
}
