package ru.kpfu.models;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class PersonalForm {
    private Long id;
    private Long userId;
    private String bio;
    private Integer age;
    private String gender;
    private byte[] photo;
}
