package io.coremaker.codingchallenge.repo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {

    private final String name;
    private final String email;
    private final String passwordHash;
}
