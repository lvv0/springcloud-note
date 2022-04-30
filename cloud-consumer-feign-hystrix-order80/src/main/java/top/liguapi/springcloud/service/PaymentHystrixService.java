package top.liguapi.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
// 写在接口里的fallback只能处理服务端的异常，不能处理客户端controller中的异常
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT/payment/hystrix", fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
