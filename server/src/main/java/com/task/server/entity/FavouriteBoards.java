package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.Date;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;


@Entity
@Data
@Table
public class FavouriteBoards extends Boards {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    private Users creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    @NotNull
    private boolean is_private; 


    @ManyToOne
    private TaskBoards taskboard;

    @ManyToOne
    private UserBoards userboard;

    @ManyToOne
    private TeamsBoards teamboard; 

    @ManyToOne
    private ProjectBoards projectboard;
    
    private Boolean seed;
   
}
