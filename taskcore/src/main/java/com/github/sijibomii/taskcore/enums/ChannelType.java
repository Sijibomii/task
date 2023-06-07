package com.github.sijibomii.taskcore.enums;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ChannelType {

    ORGANIZATION("ORGANIZATION"),
    TEAM("TEAM"),
    PROJECT("PROJECT");

    @Setter
    private String name;


    @JsonValue
    public int getOrdinal(){
        return this.ordinal();
    }

}
