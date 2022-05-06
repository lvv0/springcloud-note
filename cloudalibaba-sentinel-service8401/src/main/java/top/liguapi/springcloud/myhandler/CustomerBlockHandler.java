package top.liguapi.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import top.liguapi.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException2(BlockException blockException) {
        return new CommonResult(444, "global处理方法！");
    }
}
