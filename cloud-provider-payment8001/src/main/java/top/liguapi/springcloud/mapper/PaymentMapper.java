package top.liguapi.springcloud.mapper;

import org.apache.ibatis.annotations.Param;
import top.liguapi.springcloud.entities.Payment;

public interface PaymentMapper {
    public int create(@Param("payment") Payment payment);

    Payment selectPaymentById(@Param("id") Long id);
}
