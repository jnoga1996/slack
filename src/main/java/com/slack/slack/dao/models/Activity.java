package com.slack.slack.dao.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIdentityInfo(
            scope = Activity.class,
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    private Course course;

    @OneToOne
    private TimePlan timePlan;
//    public Activity() {
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
