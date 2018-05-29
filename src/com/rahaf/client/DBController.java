package com.rahaf.client;

import java.util.HashMap;
import java.util.Map;

public class DBController {
    static Map<String,ServerModel> serverMap = new HashMap<>();
    public ServerModel getServerFromDatabase(String fun){
        return serverMap.getOrDefault(fun,null);
    }

    public void saveServer(String fun,ServerModel server){
        serverMap.put(fun,server);
    }

}
