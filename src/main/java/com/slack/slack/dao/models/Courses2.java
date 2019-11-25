package com.slack.slack.dao.models;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SLACK_COURSE")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Courses2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COURS_ID", unique = true)
    Long coursId;

    @Column(name = "OWNER_ID")
    Long ownerId;

    @Column(name = "CLASS_START_DATE")
    LocalDateTime startDate;

    @Column(name = "CLASS_DURATION")
    Integer duration;

    @Column(name = "CLASS_DESC")
    String description;

}
