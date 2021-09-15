package com.example.mytaskweek9.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;


//    @JsonIgnore
    @OneToMany
    private List<Post> postList = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    private Connection connection;

//    @JsonIgnore
//    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private List<Comments> commentsList = new ArrayList<>();
}
