package com.task.server.entity;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

// base entity for the permissions
@MappedSuperclass
public class Permissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    @NotNull
    private String description;

    @NotNull
    private Integer code;

    // user who granted permission
    @ManyToOne
    @NotNull
    private Users assigned_by;

    // has this permission been suspended?
    @NotNull
    private Boolean active;

    
}
