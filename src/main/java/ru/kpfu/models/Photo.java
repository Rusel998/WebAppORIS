package ru.kpfu.models;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Photo {
    private Long id;
    private Long profileId;
    private String photoUrl;
    private LocalDateTime uploadDate;
}
