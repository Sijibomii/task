package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

// shows projects
@Entity 
@Data
@Table
public class TeamsBoards extends Boards {
    
    // one task board to one team
    @OneToOne
    private Organizations organization;
    private Boolean seed;
}
 