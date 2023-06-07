package com.github.sijibomii.taskcore.entity;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// base entity for the permissions
@Entity
@Data
@Table()
public class Permissions {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    // "create:channel:<id>"
    @NotBlank(message="NAME CANNOT BE NULL OR BLANK")
    @NotNull(message="NAME CANNOT BE NULL OR BLANK")
    private String name; 

    @NotNull
    private String description;

    @NotNull
    private Integer code;

    @ManyToOne
    @NotNull
    private Users user;

    // has this permission been suspended?
    @NotNull
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "organizations_id")
    private Organizations org;

    @ManyToOne
    @JoinColumn(name = "projects_id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams team;


    @Enumerated(EnumType.STRING)
    //  name conflicted so...
    private com.github.sijibomii.taskcore.enums.Permissions permission;
}
