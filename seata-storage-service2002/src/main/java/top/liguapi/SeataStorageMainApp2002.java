package top.liguapi;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EnableFeignClients
// 谁管理数据源，谁就能控制事务。默认druid管理，应该交由seata管理。seata管理数据源后，seata才能管理事务。
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自动创建
@SpringBootApplication
@MapperScan("top.liguapi.mapper")
public class SeataStorageMainApp2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMainApp2002.class, args);
    }
}
