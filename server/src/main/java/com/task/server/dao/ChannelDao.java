package com.task.server.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.task.server.dao.base.BaseDao;
import com.task.server.entity.Channel;

@Repository
public interface ChannelDao extends BaseDao<Channel> {
    
    @Query(value="SELECT c.* FROM channel c INNER JOIN channel_members cm ON c.id = cm.channel_id WHERE cm.user_id = ?1 WHERE c.organization = ?2", nativeQuery = true)
    List<?> getOrgChannelsWhereUserIsMemeber(String user_id, String org_id);

}
