package top.liguapi.mapper;

import org.apache.ibatis.annotations.Param;
import top.liguapi.springcloud.entities.Order;

public interface OrderMapper {
    /**
     * 创建订单
     */
    void create(Order order);

    /**
     * 修改订单金额
     */
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
