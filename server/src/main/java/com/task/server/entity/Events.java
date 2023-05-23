package com.task.server.entity;

import java.util.Date;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import jakarta.persistence.Id;
import org.springframework.data.relational.core.mapping.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.server.constants.EventEnum;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

// the entity for events
@Entity
@Data
@Table
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    // type of events
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private EventEnum event;

    // createdOn
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    // description: user:1234hhriedj logged in, user:nndneidnien was added into org:nendeiniendindin
    @NotNull
    private String description;
}
