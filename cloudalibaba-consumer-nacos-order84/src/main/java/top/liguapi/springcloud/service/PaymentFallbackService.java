package top.liguapi.springcloud.service;

import org.springframework.stereotype.Service;
import top.liguapi.springcloud.entities.CommonResult;
import top.liguapi.springcloud.entities.Payment;

@Service
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return CommonResult.fail(444, "服务降级返回,没有该流水信息");
    }
}
