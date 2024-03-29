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


@Entity
@Data
@Table
public class Status {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    @NotNull
    private String label;

    private String description;

    @ManyToOne
    private ProjectBoards project_board;

    @ManyToOne
    private UserBoards user_board;

    private Boolean seed;
}
