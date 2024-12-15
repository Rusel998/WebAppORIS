package ru.kpfu.models;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Interest {
    private Long id;
    private String name;
}
