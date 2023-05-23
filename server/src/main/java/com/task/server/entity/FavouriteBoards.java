package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class FavouriteBoards extends Boards {

    private TaskBoards taskboard;

    private UserBoards userboard;

    private TeamsBoards teamboard;

    private ProjectBoards projectboard;
    
}
