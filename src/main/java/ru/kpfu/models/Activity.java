package ru.kpfu.models;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Activity {
    private Long id;
    private Long userId;
    private LocalDateTime lastLogin;
    private Integer profileViews;
}
