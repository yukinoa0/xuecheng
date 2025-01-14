package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程计划树形结构 dto
 * @Date 2025/01/14 15:54
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@ApiModel("课程计划树形结构 dto")
public class TeachplanDto extends Teachplan {
    @ApiModelProperty(value = "课程计划关联的媒资信息")
    private TeachplanMedia teachplanMedia;

    @ApiModelProperty(value = "子结点")
    private List<TeachplanDto> teachPlanTreeNodes;
}
