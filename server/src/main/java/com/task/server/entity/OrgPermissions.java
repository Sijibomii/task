package com.task.server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table()
public class OrgPermissions extends Permissions {
    
    @ManyToOne
    @JoinColumn(name = "organizations_id")
    private Organizations org;

    @Enumerated(EnumType.STRING)
    //  name conflicted so...
    private com.task.server.enums.OrgPermissions status;

}
