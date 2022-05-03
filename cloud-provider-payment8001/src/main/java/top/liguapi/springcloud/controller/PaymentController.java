package top.liguapi.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import top.liguapi.springcloud.entities.CommonResult;
import top.liguapi.springcloud.entities.Payment;
import top.liguapi.springcloud.service.PaymentService;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result == 1) {
            log.info("插入成功！");
            return CommonResult.ok(payment, "插入成功，服务端口好为" + serverPort);
        } else {
            return CommonResult.fail("失败");
        }
    }

    @GetMapping("getPayment/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPayment(id);
        if (payment != null) {
            log.info("查询成功！");
            return CommonResult.ok(payment, "查询成功，服务端口好为" + serverPort);
        } else {
            return CommonResult.fail("失败");
        }
    }

    @RequestMapping("discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("feign/timeout")
    public String paymentFeignTimeOut() {
        System.out.println("*****paymentFeignTimeOut from port: " + serverPort);
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back";
    }
}
