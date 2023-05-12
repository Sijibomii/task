package com.task.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import com.task.server.dao.EventDao;
import com.task.server.entity.Events;

public class EventService {
    
    @Autowired
    private EventDao eventsDao;

    public Events save(Events events) {
        return eventsDao.save(events);
    }
}
