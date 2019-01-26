package cc.shixw.wsp.wsp.server.config;

import cc.shixw.wsp.wsp.server.WebSocketPushServer;
import cc.shixw.wsp.wsp.server.manager.CycleMessageManager;
import cc.shixw.wsp.wsp.server.manager.impl.CycleMessageManagerImpl;
import cc.shixw.wsp.wsp.server.route.RestWSPRoute;
import cc.shixw.wsp.wsp.server.route.WSPRoute;
import cc.shixw.wsp.wsp.server.session.WebSocketSessionService;
import cc.shixw.wsp.wsp.server.session.RedisWebSocketSessionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 18:57
 */
@Configuration
public class WebSocketPushServerConfig {

    @Bean
    public WebSocketSessionService webSocketSessionService(RedisTemplate redisTemplate){
        return new RedisWebSocketSessionServiceImpl(redisTemplate);
    }

    @Bean
    public WSPRoute wspRoute(WebSocketSessionService webSocketSessionService, RestTemplate restTemplate){
        return new RestWSPRoute(webSocketSessionService,restTemplate);
    }

    @Bean
    public CycleMessageManager cycleMessageManager(RedisTemplate redisTemplate){
        return new CycleMessageManagerImpl(redisTemplate);
    }
    @Bean
    public WebSocketPushServer webSocketPushServer(WSPRoute wspRoute,CycleMessageManager cycleMessageManager){
        WebSocketPushServer webSocketPushServer = new WebSocketPushServer();
        webSocketPushServer.setWspRoute(wspRoute);
        webSocketPushServer.setCycleMessageManager(cycleMessageManager);
        return webSocketPushServer;
    }
}
