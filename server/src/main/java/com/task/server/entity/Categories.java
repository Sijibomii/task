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
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String label;

    @NotNull
    @ManyToOne
    private Users creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    // project categories
    @ManyToOne
    private ProjectBoards projects_board; 

    // user categories
    @ManyToOne
    private UserBoards user_board;

 
    // for task boards or user boards
    @ManyToMany
    @JoinTable(
    name = "task_categories",  
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    List<Tasks> task_categories;

    // for project boards
    @ManyToMany
    @JoinTable(
    name = "project_categories",  
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    List<Projects> projects_categories;

    // for teams categories
    @ManyToMany
    @JoinTable(
    name = "teams_categories",  
    joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "teams_id", referencedColumnName = "id"))
    List<Teams> teams_categories;

}
