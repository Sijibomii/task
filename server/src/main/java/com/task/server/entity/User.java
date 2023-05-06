package com.task.server.entity;

import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@EntityScan
@Data
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    // online
    @NotNull
    private Boolean online;

    // staff
    @NotNull
    private Boolean staff;

    // hasLoggedIn
    @NotNull
    private Boolean hasLoggenIn;

    // hasActivated
    @NotNull
    private Boolean hasActivated;

    // email
    @NotNull
    private Boolean email;

    // createdAt
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    @CreationTimestamp
    private Date createdAt;

    // lastUpdateAt
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+1")
    @CreationTimestamp
    private Date lastUpdateAt;

    // token used to generate access and refresh tokens
    private String token;

    private Boolean isBlocked;
}
