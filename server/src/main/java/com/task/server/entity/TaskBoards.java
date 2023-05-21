package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

    // @ManyToMany
    // @JoinTable(
    // name = "favourite_boards",  
    // joinColumns = @JoinColumn(name = "taskboard_id", referencedColumnName = "id"), 
    // inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    // List<Users> tagged_users;
    
}
