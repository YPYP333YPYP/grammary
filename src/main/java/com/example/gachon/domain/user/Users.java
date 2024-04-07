package com.example.gachon.domain.user;

import com.example.gachon.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    private String role;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String social;

    private String password;

    private String phoneNum;

    @Column(nullable = false)
    private String nickname;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    private Status status;



}
