package com.xuecheng.content.api;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程分类接口
 * @Date 2025/01/11 14:43
 */
@Api(tags = "课程分类接口")
@RestController
public class CourseCategoryController {
    @Autowired
    private CourseCategoryService courseCategoryService;

    @ApiOperation("课程分类树节点查询接口")
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        return courseCategoryService.queryTreeNodes("1");
    }
}
