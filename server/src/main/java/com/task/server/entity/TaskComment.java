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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data 
@Table
public class TaskComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
    @CreationTimestamp
    private Date createdOn;

    @NotNull
    private String comment;

    private Boolean supervised;

    @ManyToOne
    @NotNull
    private Users creator;

    @ManyToMany
    @JoinTable(
    name = "comment_tags",  
    joinColumns = @JoinColumn(name = "taskcomment_id", referencedColumnName = "id"), 
    inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    List<Users> tagged_users;
    
}
