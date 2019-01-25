package cc.shixw.wsp.wsp.web.server.ws;

import cc.shixw.wsp.wsp.web.server.entity.WSPMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: websocket server
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/25 22:15
 */
@ServerEndpoint("/ws/{uuid}/{route}")
@Component
public class WebSocketServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
    //当前连接的Session
    private Session session;

    private String key;

    //保存所有的websocket连接
    private static ConcurrentHashMap<String,Set<WebSocketServer>> websocketMap = new ConcurrentHashMap<>();

    /**
     * 客户端连接成功
     * @param session
     * @param uuid
     * @param route
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String uuid,@PathParam("route") String route){
        this.session = session;
        this.key = uuid+route;
        LOGGER.debug("UUID:"+uuid+",route:"+route+" 连接成功!");
        if (websocketMap.containsKey(key)){
            websocketMap.get(key).add(this);
        }else{
            Set<WebSocketServer> set = new HashSet<>();
            set.add(this);
            websocketMap.put(key,set);
        }
    }

    /**
     * 客户端连接关闭
     */
    @OnClose
    public void onClose(){
        LOGGER.debug("连接关闭");
        if (websocketMap.containsKey(key)){
            websocketMap.get(key).remove(this);
        }
    }

    /**
     * 接收发送的消息
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){

    }

    /**
     * 发生错误
     * @param session
     * @param e
     */
    @OnError
    public void onError(Session session,Throwable e){
        LOGGER.error("key : "+this.key+" 发生错误：",e);
    }


    public void sendMessage(WSPMessage wspMessage) throws Exception{
        this.session.getBasicRemote().sendObject(wspMessage.getMessage());
    }


    public static void sendInfoByRoute(final WSPMessage wspMessage){
        String key = wspMessage.getUuid()+wspMessage.getRoute();
        if (websocketMap.containsKey(key)){
            Set<WebSocketServer> servers = websocketMap.get(key);
            servers.forEach(server-> {
                try {
                    server.sendMessage(wspMessage);
                } catch (Exception e) {
                    LOGGER.error("向UUID:"+wspMessage.getUuid()+",route:"+wspMessage.getRoute()+"发送消息异常");
                }
            });
        }else{
            LOGGER.error("未找到当前连接的客户端");
        }
    }
}
