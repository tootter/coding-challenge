package io.coremaker.codingchallenge.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    private String email;
    private char[] password;
}
