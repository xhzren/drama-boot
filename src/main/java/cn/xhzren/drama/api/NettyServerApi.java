package cn.xhzren.drama.api;

import cn.xhzren.drama.netty.DramaServerStart;
import cn.xhzren.drama.properties.NettyProperties;
import cn.xhzren.drama.service.NettyService;
import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/netty")
public class NettyServerApi {

    @Autowired
    private NettyService nettyService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String start(@NotEmpty(message = "端口不能为空") int port) {
        boolean res = nettyService.start(port);
        Map<String,Object> result = new HashMap<>();
        if(!res) {
            result.put("code", 500);
            result.put("msg", "启动失败");
        }else {
            result.put("code", 200);
            result.put("msg", "启动成功");
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/stop",method = RequestMethod.POST)
    public String stop(@NotEmpty(message = "端口不能为空") int port) {
        nettyService.stop(port);
        Map<String,Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "已停止运行");
        return JSONObject.toJSONString(result);
    }

}
