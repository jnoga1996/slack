package com.slack.slack.dao.models;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGES")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    public Message() {}

    public Message(String content) {
        this.content = content;
    }

    public Message(Long id, String content) {
        this(content);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
