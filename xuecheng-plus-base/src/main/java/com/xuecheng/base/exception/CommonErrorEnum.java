
package com.xuecheng.base.exception;


/**
 * @Description 通用错误信息
 * @Author Mr.M
 * @Date 2022/9/6 11:29
 * @Version 1.0
 */
public enum CommonErrorEnum implements ErrorEnum {

    UNKNOWN_ERROR("执行过程异常，请重试。"),
    PARAMS_ERROR("非法参数"),
    OBJECT_NULL("对象为空"),
    QUERY_NULL("查询结果为空"),
    REQUEST_NULL("请求参数为空");

    /**
     * 错误信息
     */
    private final String errorMsg;

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    CommonErrorEnum(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
