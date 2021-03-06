package cc.shixw.wsp.wsp.server.session;

import cc.shixw.wsp.wsp.server.session.WebSocketSessionService;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;

/**
 * @Description: session 信息服务
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:58
 */
public class RedisWebSocketSessionServiceImpl implements WebSocketSessionService {

    private static final String WEB_SOCKET_SESSION_CACHE_KEY_PREFIX = "WEB_SOCKET_SESSION_CACHE_";

    private RedisTemplate<String,String> redisTemplate;

    public RedisWebSocketSessionServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void register(String routeKey, String ip, int port) {
        redisTemplate.opsForSet().add(getCacheKey(routeKey),ip+":"+port);
    }

    @Override
    public void unRegister(String routeKey, String ip, int port) {
        redisTemplate.opsForSet().remove(getCacheKey(routeKey),ip+":"+port);
    }

    @Override
    public void unRegisterAddress(String routeKey, String address) {
        redisTemplate.opsForSet().remove(getCacheKey(routeKey),address);
    }

    @Override
    public Set<String> listRegisterAddress(String routeKey) {
        return redisTemplate.opsForSet().members(getCacheKey(routeKey));
    }


    /**
     * 获取缓存key
     * @param routeKey
     * @return
     */
    private String getCacheKey(String routeKey){
        return WEB_SOCKET_SESSION_CACHE_KEY_PREFIX+routeKey;
    }
}
