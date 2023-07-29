package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// shows tasks peculiar to a particluar project
@Entity
@Data
@Table
public class TaskBoards extends Boards {
    
    // one task board to one project
    // @ManyToOne
    // @NotNull
    // private Projects project;
}
