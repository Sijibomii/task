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
public class ProjectPermissions {
    
    @ManyToOne
    @JoinColumn(name = "projects_id")
    private Projects projects;

    @Enumerated(EnumType.STRING)
    private com.task.server.enums.ProjectPermissions permission;

}
