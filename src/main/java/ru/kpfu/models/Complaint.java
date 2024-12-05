package ru.kpfu.models;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Complaint {
    private Long id;
    private Long complainantId;
    private Long offenderId;
    private String reason;
    private String comment;
    private LocalDateTime dateTime;
}
