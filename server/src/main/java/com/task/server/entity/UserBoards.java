package com.task.server.entity;

import javax.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// shows tasks but only specific to user
@Entity
@Data
@Table
public class UserBoards extends Boards {
    
    @ManyToOne
    private Users user;  

    // whether this is the default user board
    @NotNull
    private Boolean is_default;
}

