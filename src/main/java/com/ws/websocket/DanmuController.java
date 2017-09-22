package com.ws.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

@Controller
public class DanmuController {

    @RequestMapping("/dm")
    public ModelAndView showDanmu(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/index");
        return view;
    }
}
