package cc.shixw.wsp.wsp.web.config;

import cc.shixw.wsp.wsp.server.session.WebSocketSessionService;
import cc.shixw.wsp.wsp.web.websocket.WebSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

/**
 * @Description: websocket 支持
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 21:42
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Resource
    public void setWebSocketSessionService(WebSocketSessionService webSocketSessionService) {
        WebSocketServer.webSocketSessionService = webSocketSessionService;
    }
}
