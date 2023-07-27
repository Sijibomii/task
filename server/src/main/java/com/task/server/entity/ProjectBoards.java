package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// shows projects
@Entity
@Data
@Table
public class ProjectBoards extends Boards {
    
    // one task board to one team
    @NotNull
    private Projects project;
}
