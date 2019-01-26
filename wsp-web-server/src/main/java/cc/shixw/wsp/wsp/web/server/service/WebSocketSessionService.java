package cc.shixw.wsp.wsp.web.server.service;

/**
 * @Description: websocket session 使用的服务类
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 12:23
 */
public interface WebSocketSessionService {

    /**
     * 新连接注册
     */
    void register(String key,String ip,int port);

    /**
     * 连接注销
     */
    void unRegister(String key,String ip,int port);

}
