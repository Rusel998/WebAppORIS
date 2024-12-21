package ru.kpfu.models;

import lombok.*;

import java.sql.Timestamp;

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
    private String status;
}
