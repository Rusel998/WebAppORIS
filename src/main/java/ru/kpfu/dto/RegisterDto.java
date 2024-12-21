package ru.kpfu.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class RegisterDto {
    private String username;
    private String email;
    private String password;
}
