package com.xuecheng.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 保存课程计划dto，包括新增、修改
 * @Date 2025/01/15 22:01
 */
@Data
@ToString
@ApiModel("保存课程计划dto，包括新增、修改")
public class SaveTeachplanDto {
    @ApiModelProperty(value = "教学计划id")
    private Long id;

    @ApiModelProperty(value = "课程计划名称")
    private String pname;

    @ApiModelProperty(value = "课程计划父级id")
    private Long parentid;

    @ApiModelProperty(value = "层级，分为 1、2、3 级")
    private Integer grade;

    @ApiModelProperty(value = "课程类型: 1 视频、2 文档")
    private String mediaType;

    @ApiModelProperty(value = "课程id")
    private Long courseId;

    @ApiModelProperty(value = "课程发布id")
    private Long coursePubId;

    @ApiModelProperty(value = "是否支持试学或预览（试看）")
    private String isPreview;
}
