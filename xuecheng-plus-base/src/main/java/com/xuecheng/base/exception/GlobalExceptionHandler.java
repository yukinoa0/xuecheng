package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 统一异常处理器
 * @Date 2025/01/11 21:33
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 自定义异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CustomException.class)
    public RestErrorResponse customException(CustomException e) {
        String errorMsg = e.getErrorMsg();
        log.error("系统异常! 错误原因: {}", errorMsg, e);
        return new RestErrorResponse(errorMsg);
    }

    /**
     * validation 参数异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public RestErrorResponse exception(BindException e) {
        StringJoiner errorMsgJoiner = new StringJoiner(",");
        // 遍历拼接所有的参数异常信息
        e.getBindingResult().getFieldErrors().forEach(f -> errorMsgJoiner.add(f.getDefaultMessage()));
        String errorMsg = errorMsgJoiner.toString();
        log.error("非法参数异常! 错误原因: {}", errorMsg, e);
        return new RestErrorResponse(errorMsg);
    }

    /**
     * 系统异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public RestErrorResponse systemException(Exception e) {
        log.error("系统异常! 错误原因: {}", e.getMessage(), e);
        return new RestErrorResponse(CommonErrorEnum.UNKNOWN_ERROR.getErrorMsg());
    }
}
