package com.ws.websocket;

import com.ws.config.Danmu;
import com.ws.config.ServiceFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
public class DanMuWebSocket {
    private static CopyOnWriteArraySet<DanMuWebSocket> websocketPools = new CopyOnWriteArraySet<DanMuWebSocket>();

    private Session session;
    boolean index = false;

    /**
     * 建立连接成功调用的方法
     * @param session
     * @throws Exception
     */
    @OnOpen
    public void onOpen(Session session) throws Exception{
        this.session = session;
        websocketPools.add(this);
        index = true;
        onMessage("Welcome to DEYAO danmu ...", this.session);
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        websocketPools.remove(this);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
         if (index) {
             Map<String, Object> dmMap = ServiceFactory.getIDanmuServiceInstance().list();
             List<Danmu> list = (List<Danmu>) dmMap.get("allDanmu");
              for(int i = 0; i < list.size() ; i++) {
                  String msg = list.get(i).getDanmuInfo();
                  this.session.getBasicRemote().sendText(msg);
                  System.out.println(msg);
              }
              index = false;
         } else {
             Danmu danmu = new Danmu();
             danmu.setDanmuInfo(message);
             danmu.setUserId(null);
             danmu.setUserNickName(null);
             ServiceFactory.getIDanmuServiceInstance().insert(danmu);
         }
        for (DanMuWebSocket dmws : websocketPools) {
            dmws.send(message);
        }
    }

    public void send(String message) throws IOException {
        this.session.getAsyncRemote().sendText(message);
    }

    @OnError
    public void onError(Session session, Throwable e){
        System.out.println("发送错误");
        e.printStackTrace();
    }
}
