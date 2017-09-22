package com.ws.websocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 凡是带注解的类或者注册了请求路径的类必须与Application类同包，否则应用将报Whitelable Error Page的错
 */
//@Configuration
public class WebSocMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registration){
        //此语句是注册系统访问的URL，也就是注册视图的URL
        registration.addViewController("/ws").setViewName("/ws");
    }
}
