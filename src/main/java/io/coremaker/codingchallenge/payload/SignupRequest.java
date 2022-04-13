package io.coremaker.codingchallenge.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String name;
    private String email;
    private char[] password;
}
