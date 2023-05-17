package com.task.server.controllers.base;

import com.task.server.utils.MessageResult;
import org.apache.commons.lang3.StringUtils;
import jakarta.servlet.http.HttpServletRequest;

public class BaseController {

    protected MessageResult success() { 
        return new MessageResult(0, "SUCCESS");
    }

    protected MessageResult success(int code) { 
        return new MessageResult(code, "SUCCESS");
    }

    protected MessageResult success(String msg) {
        return new MessageResult(0, msg);
    }

    protected MessageResult success(String msg, Object obj) {
        MessageResult mr = new MessageResult(0, msg);
        mr.setData(obj);
        return mr;
    }
    protected MessageResult success(int code, Object obj) { 
        MessageResult mr = new MessageResult(code, "SUCCESS");
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

    protected String request(HttpServletRequest request, String name) {
        // Removes control characters (char <= 32) from both ends of this String returning an empty String ("") if the String is empty ("") after the trim or if it is null.
        return StringUtils.trimToEmpty(request.getParameter(name));
    }
}
