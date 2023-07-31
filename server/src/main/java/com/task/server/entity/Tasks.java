package com.task.server.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.task.server.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table
public class Tasks {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    @NotNull
    private String description;

    @NotNull
    private String heading;

    @NotNull
    private int comment_count;

    @NotNull
    private int assignee_count;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private Boolean supervised;

    @ManyToOne
    @NotNull
    private Users creator;


    @ManyToOne 
    @NotNull
    private Categories category;


    @ManyToMany(mappedBy = "task_assignees")
    // @JsonIgnore
    private List<Users> members;


    @ManyToMany
    @JoinTable(
    name = "task_tags",  
    joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    List<Tags> tags;


    private Boolean seed;
}
