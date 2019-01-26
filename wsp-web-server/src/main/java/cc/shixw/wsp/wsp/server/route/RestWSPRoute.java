package cc.shixw.wsp.wsp.server.route;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.api.exception.NoRegisterClientException;
import cc.shixw.wsp.wsp.api.exception.WSPInternalPushException;
import cc.shixw.wsp.wsp.server.session.WebSocketSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * @Description: 消息推送路由
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:35
 */
public class RestWSPRoute implements WSPRoute{

    private static final Logger LOGGER = LoggerFactory.getLogger(RestWSPRoute.class);

    private WebSocketSessionService webSocketSessionService;

    private RestTemplate restTemplate;

    public RestWSPRoute(WebSocketSessionService webSocketSessionService, RestTemplate restTemplate) {
        this.webSocketSessionService = webSocketSessionService;
        this.restTemplate = restTemplate;
    }

    /**
     * 将一条消息路由到对应的服务器上
     * @param wspMessage
     */
    public void routeMessage(WSPMessage wspMessage){
        //首先获取当前路由对应的所有的注册的服务地址
        Set<String> registerAddressSet = webSocketSessionService.listRegisterAddress(wspMessage.getRouteKey());
        if (CollectionUtils.isEmpty(registerAddressSet)){
            throw new NoRegisterClientException("未找到注册的客户端连接");
        }
        registerAddressSet.forEach(registerAddress->{
            try {
                restTemplate.postForObject("http://"+registerAddress+"/wsp/internal/push",wspMessage,WSPMessage.class);
            } catch (Exception e) {
                //注销当前节点
                webSocketSessionService.unRegisterAddress(wspMessage.getRouteKey(),registerAddress);
                LOGGER.error("推送节点："+wspMessage.getRouteKey()+","+registerAddress+" 异常：",e);
                //不抛异常 推送下一个节点
//                throw new WSPInternalPushException("调用注册节点推送消息异常",e);
            }
        });
    }
}
