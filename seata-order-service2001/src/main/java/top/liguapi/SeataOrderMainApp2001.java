package top.liguapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
// 谁管理数据源，谁就能控制事务。默认druid管理，应该交由seata管理。seata管理数据源后，seata才能管理事务。
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自动创建
@MapperScan("top.liguapi.mapper")
public class SeataOrderMainApp2001 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApp2001.class, args);
    }
}
