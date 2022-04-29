package top.liguapi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import top.liguapi.springcloud.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
// 排除myrule包
@ComponentScan(excludeFilters =
        {
                @ComponentScan.Filter(type = FilterType.REGEX,pattern = "top.liguapi.springcloud.myrule.*")
        })
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration= MySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
