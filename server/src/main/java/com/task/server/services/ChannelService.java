package com.task.server.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.server.dao.ChannelDao;
// import com.task.server.entity.Channel;

@Service
public class ChannelService {

    @Autowired
    private ChannelDao channelDao;
    
    // get all org channels by orgId where user is a member
    public List<?> getOrgChannelsWhereUserIsMemeber(String user_id, String org_id) throws Exception{
        return channelDao.getOrgChannelsWhereUserIsMemeber(user_id, org_id);
    }
}
