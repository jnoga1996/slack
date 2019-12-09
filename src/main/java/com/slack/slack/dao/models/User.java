package com.slack.slack.dao.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String faculty;

    private String major;

    @Range(min = 0, max = 7)
    private int semester;

    //Jacek nie polecam uzywania many to many
    //bo gdy pojdzie request do repo o pobranie usera, to od razu pobierze wszystkie cursy do ktorych jest przypisany user i wychodzi jeden wielki kloc
    //bo np. masz w bazie 160 kursow w ciagu roku.
    //Lepiej ustawic id usera i np. date jako pola kursu i potem w repository kursu robisz findByUserIdAndDate() i zwraca Ci pare elementów.
    @ManyToMany(mappedBy = "users")
    @JsonIdentityInfo(
            scope = User.class,
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    private List<Course> courses;

    public User() {}

    public User(String login, String password, String faculty, String major, int semester) {
        this.login = login;
        this.password = password;
        this.faculty = faculty;
        this.major = major;
        this.semester = semester;
        this.role = Role.USER;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
