package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// shows tasks but only specific to user
@Entity
@Data
@Table
public class UserBoards extends Boards {

    // whether this is the default user board
    @NotNull 
    private Boolean is_default;

    private Boolean seed;
}

