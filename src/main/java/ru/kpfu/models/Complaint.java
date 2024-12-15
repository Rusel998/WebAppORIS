package ru.kpfu.models;

import lombok.*;

import java.security.Timestamp;
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
    private Timestamp datetime;
}
