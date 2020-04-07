package cn.xhzren.drama.service;

import cn.xhzren.drama.netty.DramaServerStart;
import cn.xhzren.drama.properties.NettyProperties;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@Service
public class NettyService {

    @Autowired
    private NettyProperties nettyProperties;

    private Map<Integer, DramaServerStart> servers = new HashMap<>();
    private int lastPort = 0;

    public boolean start(int port) {
        DramaServerStart serverMain = new DramaServerStart(port);
        serverMain.run();
        if(serverMain.getFuture() == null) {
            return false;
        }
        if(serverMain.getFuture().isSuccess()) {
            lastPort = port;
            servers.put(port, serverMain);
            return true;
        }
        return false;
    }

    public void stop(int port) {
        servers.get(port).close();
        servers.remove(port);
        lastPort = 0;
    }

}
