package ru.kpfu.models;


import lombok.*;

import java.util.Date;

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
    private String comment;
    private Date date;
}
