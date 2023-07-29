package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class FavouriteBoards extends Boards {

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
