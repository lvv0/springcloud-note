package top.liguapi.springcloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.liguapi.springcloud.entities.Payment;
import top.liguapi.springcloud.mapper.PaymentMapper;
@Service
public class PaymentSerciveImpl implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;


    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentMapper.selectPaymentById(id);
    }
}
