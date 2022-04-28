package top.liguapi.springcloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.liguapi.springcloud.entities.Payment;
import top.liguapi.springcloud.service.PaymentService;

@SpringBootTest()
class Payment8001Test {

    @Autowired()
    PaymentService paymentService;

    @Test
    void test1(){
        Payment payment = new Payment(1L, "abc");
        int creat = paymentService.create(payment);
        if (creat == 1){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }

    }

    @Test
    void test2(){
        Payment payment = paymentService.getPayment(1L);
        System.out.println(payment.getSerial());
    }
}
