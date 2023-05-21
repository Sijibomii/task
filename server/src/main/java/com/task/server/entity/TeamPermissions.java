package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table()
@EqualsAndHashCode(callSuper=false)
public class TeamPermissions extends Permissions {
    
    @ManyToOne
    @JoinColumn(name = "teams_id")
    private Teams team;

    @Enumerated(EnumType.STRING)
    //  name conflicted so...
    private com.task.server.enums.TeamPermissions permission;

}
