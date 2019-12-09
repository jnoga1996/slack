package com.slack.slack.dao.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "TIMEPLAN")
public class TimePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TIMEPLANID")
    private Long timePlanId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    private Activity activity;

}

