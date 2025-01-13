package com.xuecheng.base.exception;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 项目自定义异常
 * @Date 2025/01/11 21:16
 */
public class CustomException extends RuntimeException {
    private String errorMsg;

    public CustomException() {
        super();
    }

    public CustomException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static void cast(ErrorEnum errorEnum) {
        cast(errorEnum.getErrorMsg());
    }

    public static void cast(String errorMsg) {
        throw new CustomException(errorMsg);
    }
}
