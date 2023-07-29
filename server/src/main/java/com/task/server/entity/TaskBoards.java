package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

// shows tasks peculiar to a particluar project
@Entity
@Data
@Table
public class TaskBoards extends Boards {
    
    // one task board to one project
    @OneToOne
    private Projects project;

     
}
