package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.CourseCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程分类树实体
 * @Date 2025/01/11 01:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "课程分类树实体")
public class CourseCategoryTreeDto extends CourseCategory {
    @ApiModelProperty("课程分类子树节点")
    List<CourseCategoryTreeDto> childrenTreeNodes;
}