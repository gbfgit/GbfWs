package com.ws.config;

import java.util.*;

public class ServiceFactory {
    private Map<String, Object> dmMap = new HashMap<String, Object>();

    private ServiceFactory() {
    }

    public static ServiceFactory getIDanmuServiceInstance(){
        ServiceFactory s = new ServiceFactory();
        return s;
    }

    public Map<String, Object> list(){
        List<Danmu> list = new ArrayList<Danmu>();
         for(int i = 0; i < 10; i++) {
             Danmu d = new Danmu();
             d.setDanmuInfo("发来一波弹幕" + i);
             d.setUserId(UUID.randomUUID().toString());
             d.setUserNickName("nickname");
             list.add(d);
         }
         dmMap.put("allDanmu", list);
         return dmMap;
    }

    public void insert(Danmu danmu){
        List<Danmu> l = (List<Danmu>) dmMap.get("allDanmu");
         if (l == null) {
              l = new ArrayList<Danmu>();
         }
         l.add(danmu);
         dmMap.put("allDanmu", l);
    }
}
