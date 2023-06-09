package com.task.webscokethandler.types;
import lombok.Data;

@Data
public class AuthMessage {
    private String command;
    private String destination;
    private BaseMessage<Auth> data;
}
