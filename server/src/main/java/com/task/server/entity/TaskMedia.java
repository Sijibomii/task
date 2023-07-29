package com.task.server.entity;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// table to store media files attached to a task

@Entity
@Data
@Table
public class TaskMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // media url
    @NotNull
    private String media_url;

    // media type

    // comment?
    @ManyToOne
    private TaskComment comment;

    // task ?
    @ManyToOne
    private Tasks task;

    // deleted
    private Boolean deleted;

    // banned?(admins only)
    private Boolean banned;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;
    
}
 