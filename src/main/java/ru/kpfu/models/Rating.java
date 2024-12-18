package ru.kpfu.models;


import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Rating {
    private Long id;
    private Long userId;
    private Long ratedUserId;
    private Integer rating;
    private Timestamp date;
}
