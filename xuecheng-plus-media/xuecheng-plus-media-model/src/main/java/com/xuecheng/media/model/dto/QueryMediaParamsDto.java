package com.xuecheng.media.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author Mr.M
 * @Version 1.0
 * @Description 媒资文件查询请求模型类
 * @Date 2022/9/10 8:53
 */
@Data
@ToString
@ApiModel(description = "媒资文件查询条件dto")
public class QueryMediaParamsDto {
    @ApiModelProperty("媒资文件名称")
    private String filename;

    @ApiModelProperty("媒资类型")
    private String fileType;

    @ApiModelProperty("审核状态")
    private String auditStatus;
}