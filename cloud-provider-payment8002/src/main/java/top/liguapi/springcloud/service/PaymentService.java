package top.liguapi.springcloud.service;

import top.liguapi.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);

    Payment getPayment(Long id);
}
