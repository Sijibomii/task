package com.task.server.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.task.server.enums.ChannelType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

// a channel can be attached to a project, org, team. it's created by default
@Entity
@Data
@Table(name="channels")
public class Channel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    private Users creator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createdOn;

    @NotNull 
    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @ManyToOne 
    private Organizations organization;

    @ManyToOne
    private Teams team;

    @ManyToOne
    private Projects project;

    @ManyToMany
    @JoinTable(
    name = "channel_members",  
    joinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    List<Users> channel_members;

}
