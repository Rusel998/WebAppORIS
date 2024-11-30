package ru.kpfu.models;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
}
