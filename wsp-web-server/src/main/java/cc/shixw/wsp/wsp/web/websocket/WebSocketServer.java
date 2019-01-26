package cc.shixw.wsp.wsp.web.websocket;

import cc.shixw.wsp.wsp.api.entity.WSPMessage;
import cc.shixw.wsp.wsp.api.exception.NoRegisterClientException;
import cc.shixw.wsp.wsp.server.session.WebSocketSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
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

    public static WebSocketSessionService webSocketSessionService;


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
        //将连接保存到当前服务器缓存中
        if (websocketMap.containsKey(key)){
            websocketMap.get(key).add(this);
        }else{
            Set<WebSocketServer> set = new HashSet<>();
            set.add(this);
            websocketMap.put(key,set);
        }
        String host = session.getRequestURI().getHost();
        int port = session.getRequestURI().getPort();
        webSocketSessionService.register(key,host,port);
        LOGGER.debug("UUID:"+uuid+",route:"+route+" 连接成功!");

    }

    /**
     * 客户端连接关闭
     */
    @OnClose
    public void onClose(){
        //从当前服务缓存中移除server
        if (websocketMap.containsKey(key)){
            websocketMap.get(key).remove(this);
        }
        //从session缓存中移除
        String host = session.getRequestURI().getHost();
        int port = session.getRequestURI().getPort();
        webSocketSessionService.unRegister(key,host,port);
        LOGGER.debug("连接关闭");
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
        this.session.getBasicRemote().sendText(wspMessage.getMessage());
    }


    /**
     * 按照route在当前节点的连接缓存中推送消息
     * @param wspMessage
     */
    public static void sendInfoByRoute(final WSPMessage wspMessage) throws Exception {
        String key = wspMessage.getUuid()+wspMessage.getRoute();
        if (websocketMap.containsKey(key)){
            Set<WebSocketServer> servers = websocketMap.get(key);
            for (WebSocketServer server : servers) {
                server.sendMessage(wspMessage);
            }
        }else{
            throw new NoRegisterClientException("未找到注册的客户端连接");
        }
    }

}
