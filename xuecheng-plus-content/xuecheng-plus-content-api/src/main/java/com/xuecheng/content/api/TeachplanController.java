package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程计划编辑接口
 * @Date 2025/01/14 20:11
 */
@Api(value = "课程计划编辑接口", tags = "课程计划编辑接口")
@RestController
public class TeachplanController {
    @Autowired
    private TeachplanService teachplanService;

    @ApiOperation(value = "课程计划查询接口")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDto> queryTreeNodes(@PathVariable Long courseId) {
        return teachplanService.queryTreeNodes(courseId);
    }
}
