package cn.xhzren.drama.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("netty.connection")
@Data
public class NettyProperties {
    private String port;
    private String host;
}
