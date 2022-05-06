package top.liguapi.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.liguapi.springcloud.service.PaymentService;
import top.liguapi.springcloud.entities.CommonResult;
import top.liguapi.springcloud.entities.Payment;

@RestController
public class CircleBreakerController {
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    PaymentService paymentService;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "fallbackHandler", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException e) {
        return CommonResult.fail("来自block：" + e.getMessage());
    }

    public CommonResult fallbackHandler(@PathVariable Long id, Throwable e) {
        return CommonResult.fail("来自fallback：" + e.getMessage());
    }

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id)
    {
//        if(id == 4)
//        {
//            throw new RuntimeException("没有该id");
//        }
        return paymentService.paymentSQL(id);
    }
}
