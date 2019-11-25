package com.slack.slack.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class Courses2DTO {

    Long userId;
    LocalDateTime startDate;
    Integer duration;
    String description;
}
