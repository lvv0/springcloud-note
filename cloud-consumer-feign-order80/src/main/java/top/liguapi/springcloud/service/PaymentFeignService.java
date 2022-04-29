package top.liguapi.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.liguapi.springcloud.entities.CommonResult;

@Service
@FeignClient(value = "CLOUD-PAYMENT-SERVICE/payment")
public interface PaymentFeignService {
    @GetMapping("getPayment/{id}")
    CommonResult getPayment(@PathVariable("id") Long id);

    @GetMapping(value = "feign/timeout")
    String paymentFeignTimeOut();
}
