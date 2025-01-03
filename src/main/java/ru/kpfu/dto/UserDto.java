package ru.kpfu.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String role;
}
