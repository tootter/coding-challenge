package io.coremaker.codingchallenge.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDetails {

    private final String name;
    private final String email;
}
