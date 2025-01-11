package com.xuecheng.base.exception;

import javax.validation.groups.Default;

/**
 * @Author cliced
 * @Version 1.0
 * @Description Validation 分组校验
 * @Date 2025/01/11 23:05
 */
public class ValidationGroup {
    /**
     * 插入分组
     */
    public interface Insert extends Default {
    }

    /**
     * 修改分组
     */
    public interface Update extends Default {
    }
}
