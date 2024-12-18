package ru.kpfu.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserDto {
    private String username;
    private String email;
    private String password;
}
