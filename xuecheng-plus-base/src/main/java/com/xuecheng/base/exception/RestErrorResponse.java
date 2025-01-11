package com.xuecheng.base.exception;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 与前端约定返回的异常信息类型
 * @Date 2025/01/11 21:04
 */
public class RestErrorResponse {
    private String errorMsg;

    public RestErrorResponse(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
