package cc.shixw.wsp.wsp.server.manager.impl;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.server.manager.CycleMessageManager;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 19:34
 */
public class CycleMessageManagerImpl implements CycleMessageManager {

    private static final String CYCLE_MESSAGE_PREFIX="CYCLE_MESSAGE_";
    private RedisTemplate<String,String> redisTemplate;

    public CycleMessageManagerImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addCycleMessage(WSPMessage wspMessage) {
        redisTemplate.opsForHash().put(CYCLE_MESSAGE_PREFIX+wspMessage.getRouteKey(),wspMessage.getMessageId(), JSON.toJSONString(wspMessage));
    }

    @Override
    public void removeCycleMessage(String routeKey,String messageId) {
        redisTemplate.opsForHash().delete(CYCLE_MESSAGE_PREFIX+routeKey,messageId);
    }

    @Override
    public WSPMessage getCycleMessageSizeByRouteKeyAndMessageId(String routeKey, String messageId) {
        return JSON.parseObject((String)redisTemplate.opsForHash().get(CYCLE_MESSAGE_PREFIX+routeKey,messageId),WSPMessage.class);
    }
}
