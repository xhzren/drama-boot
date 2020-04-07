package cn.xhzren.drama;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan({"cn.xhzren.drama.mapper"})
@SpringBootApplication
public class DramaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DramaApplication.class, args);
    }

}
