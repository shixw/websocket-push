package cc.shixw.wsp.wsp.web.server.service.impl;

import cc.shixw.wsp.wsp.web.server.service.WebSocketSessionService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: session 信息服务
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:58
 */
@Component
public class WebSocketSessionServiceImpl implements WebSocketSessionService {

    private static final String WEB_SOCKET_SESSION_CACHE_KEY_PREFIX = "WEB_SOCKET_SESSION_CACHE_";
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void register(String key, String ip, int port) {
        redisTemplate.opsForSet().add(WEB_SOCKET_SESSION_CACHE_KEY_PREFIX+key,ip+":"+port);
    }

    @Override
    public void unRegister(String key, String ip, int port) {
        redisTemplate.opsForSet().remove(WEB_SOCKET_SESSION_CACHE_KEY_PREFIX+key,ip+":"+port);
    }
}
