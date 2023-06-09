package com.task.webscokethandler.types;
import lombok.Data;

@Data
public class BaseMessage<T> {
    private String op;
    private String fetchId;
    private T data;
}
