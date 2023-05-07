package com.task.server.controllers.base;

import com.task.server.utils.MessageResult;

public class BaseController {

    protected MessageResult success() { 
        return new MessageResult(0, "SUCCESS");
    }

    protected MessageResult success(String msg) {
        return new MessageResult(0, msg);
    }

    protected MessageResult success(String msg, Object obj) {
        MessageResult mr = new MessageResult(0, msg);
        mr.setData(obj);
        return mr;
    }

    protected MessageResult success(Object obj) {
        MessageResult mr = new MessageResult(0, "SUCCESS");
        mr.setData(obj);
        return mr;
    }

    protected MessageResult error(String msg) {
        return new MessageResult(500, msg);
    }

    protected MessageResult error(int code, String msg) {
        return new MessageResult(code, msg);
    }

}
