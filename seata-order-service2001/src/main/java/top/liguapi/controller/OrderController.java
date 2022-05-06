package top.liguapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liguapi.service.OrderService;
import top.liguapi.springcloud.entities.CommonResult;
import top.liguapi.springcloud.entities.Order;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return CommonResult.ok("订单创建成功!");
    }
}
