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
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// task in boards are arranged in categories
@Entity
@Data
@Table
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private Users creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    @ManyToOne
    @NotNull
    private Boards board;

    @ManyToMany
    @JoinTable(
    name = "task_categories",  
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "tasks_id", referencedColumnName = "id"))
    List<Tasks> task_categories;
    
}
