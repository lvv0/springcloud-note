package top.liguapi.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    public static CommonResult ok(){
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setCode(0);
        commonResult.setMessage("成功");
        return commonResult;
    }

    public static CommonResult ok(Object data){
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setCode(0);
        commonResult.setMessage("成功");
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult ok(Object data, String message){
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setCode(0);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult fail(){
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setCode(640);
        commonResult.setMessage("失败");
        return commonResult;
    }

    public static CommonResult fail(String message){
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setCode(640);
        commonResult.setMessage(message);
        return commonResult;
    }

    public static CommonResult fail(Integer code, String message){
        CommonResult<Object> commonResult = new CommonResult<>();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        return commonResult;
    }
}
