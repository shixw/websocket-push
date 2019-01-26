package cc.shixw.wsp.wsp.web.server.route;

import cc.shixw.wsp.wsp.web.server.entity.WSPMessage;
import cc.shixw.wsp.wsp.web.server.exception.NoRegisterClientException;
import cc.shixw.wsp.wsp.web.server.exception.WSPInternalPushException;
import cc.shixw.wsp.wsp.web.server.service.WebSocketSessionService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @Description: 消息推送路由
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:35
 */
@Component
public class WSPRoute {

    @Resource
    private WebSocketSessionService webSocketSessionService;

    @Resource
    private RestTemplate restTemplate;
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
        try {
            registerAddressSet.forEach(registerAddress->{
                restTemplate.postForObject("http://"+registerAddress+"/wsp/internal/push",wspMessage,WSPMessage.class);
            });
        } catch (Exception e) {
            throw new WSPInternalPushException("调用注册节点推送消息异常",e.getCause());
        }
    }
}
