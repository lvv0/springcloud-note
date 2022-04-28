package top.liguapi.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liguapi.springcloud.entities.CommonResult;
import top.liguapi.springcloud.entities.Payment;
import top.liguapi.springcloud.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        if (result == 1){
            log.info("插入成功！");
            return CommonResult.ok(payment,"插入成功，服务端口好为" + serverPort);
        }else {
            return CommonResult.fail("失败");
        }
    }

    @RequestMapping("getPayment/{id}")
    public CommonResult getPayment(@PathVariable("id") Long id){
        Payment payment = paymentService.getPayment(id);
        if (payment != null){
            log.info("查询成功！");
            return CommonResult.ok(payment,"查询成功，服务端口好为" + serverPort);
        }else {
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
}
