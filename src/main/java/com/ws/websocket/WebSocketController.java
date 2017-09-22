package com.ws.websocket;

import com.ws.config.MySocketMessage;
import com.ws.config.MySocketResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * 凡是带注解的类或者注册了请求路径的类必须与Application类同包，否则应用将报Whitelable Error Page的错
 */
//@Controller
public class WebSocketController {
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public MySocketResponse sayHi(MySocketMessage message) throws Exception{
        return new MySocketResponse("Welcome, " + message.getName() + "!");
    }
}
