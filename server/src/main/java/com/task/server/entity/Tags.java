package com.task.server.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table
public class Tags {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
 
    // creator
    @ManyToOne
    @NotNull
    private Users creator;

    // private TaskBoards task_board;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;
    
    // can add task to tags?(this should be a permission thing)
    // name
    @NotNull
    private String name;

    private Boolean seed;

    @ManyToOne
    private ProjectBoards projects_board; 

    @ManyToMany(mappedBy = "tags")
    // @JsonIgnore
    private List<Tasks> tasks;

}
 