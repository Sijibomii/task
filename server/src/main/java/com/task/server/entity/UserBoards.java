package com.task.server.entity;

import javax.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

// shows tasks but only specific to user
@Entity
@Data
@Table
public class UserBoards extends Boards {
    
    @ManyToOne
    private Users user; 
}

