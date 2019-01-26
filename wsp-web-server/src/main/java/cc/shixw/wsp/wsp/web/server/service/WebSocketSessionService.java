package cc.shixw.wsp.wsp.web.server.service;

import java.util.Set;

/**
 * @Description: websocket session 使用的服务类
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:23
 */
public interface WebSocketSessionService {

    /**
     * 新连接注册
     */
    void register(String routeKey,String ip,int port);

    /**
     * 连接注销
     */
    void unRegister(String routeKey,String ip,int port);

    /**
     * 列出路由对应的所有的注册地址
     * @param routeKey
     * @return
     */
    Set<String> listRegisterAddress(String routeKey);

}
