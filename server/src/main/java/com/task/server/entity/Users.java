package com.task.server.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "users")
public class Users{

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private UUID id;

    // githubId
    private String githubId;

    // githubAccessToken
    private String githubAccessToken;

    // googleId
    private String googleId;

    // googleAccessToken
    private String googleAccessToken;

    // displayName
    @NotNull
    private String displayName;

    // avatarUrl
    private String avatarUrl;

    // Password
    private String password;

    // online
    @NotNull
    private Boolean online;

    // staff
    @NotNull
    private Boolean isStaff;

    // hasLoggedIn
    @NotNull
    private Boolean hasLoggenIn;

    // hasActivated
    @NotNull
    private Boolean hasActivated;

    // email
    @NotNull
    private String email;

    // createdAt
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    @CreationTimestamp
    private Date createdAt;

    // lastUpdateAt
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    private Date lastUpdateAt;

    // token used to generate access and refresh tokens
    private String token;

    @JsonIgnore
    private String salt;

    private Boolean isBlocked;

   
}
