package cc.shixw.wsp.wsp.server.entity;

import java.io.Serializable;

/**
 * @Description:
 * @Auther: Ⅱ又贝人韦
 * @Date: 2019/1/26 20:46
 */
public class WSPSessionInfo implements Serializable{

    private String routeKey;

    private String serverIp;

    private int serverPort;

    public WSPSessionInfo(String routeKey, String serverIp, int serverPort) {
        this.routeKey = routeKey;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }


    @Override
    public String toString() {
        return "WSPSessionInfo{" +
                "routeKey='" + routeKey + '\'' +
                ", serverIp='" + serverIp + '\'' +
                ", serverPort=" + serverPort +
                '}';
    }

    public String getServerAddress(){
        return this.serverIp+":"+this.serverPort;
    }
}
