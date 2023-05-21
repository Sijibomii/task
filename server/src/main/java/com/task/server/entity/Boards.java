package com.task.server.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// a board basically displays tasks in a format.
// an org, project, person, team can have several boards
@Entity
@Data
@Table
public class Boards {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private Users creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    private boolean is_private;

    @ManyToMany
    @JoinTable(
    name = "organizations_board",  
    joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "id"))
    List<Organizations> organization_boards;

    @ManyToMany
    @JoinTable(
    name = "teams_board",  
    joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"))
    List<Teams> teams_boards;

    @ManyToMany
    @JoinTable(
    name = "projects_board",  
    joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "projects_id", referencedColumnName = "id"))
    List<Projects> projects_boards;


    @ManyToMany
    @JoinTable(
    name = "users_board",  
    joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    List<Users> users_boards;
}
