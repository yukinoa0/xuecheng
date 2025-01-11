package com.xuecheng.content.api;

import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.CourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程信息接口
 * @Date 2024/10/18 20:40
 */
@Api(tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {
    @Autowired
    CourseBaseService courseBaseService;

    @ApiOperation("课程分页查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageBean pageBean,
                                       @RequestBody(required = false) QueryCourseDto queryCourseDto) {
        return courseBaseService.queryCourseBaseList(pageBean, queryCourseDto);
    }

    @ApiOperation("新增课程接口")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated CourseDto courseDto) {
        // TODO 获取用户所属机构 id
        Long companyId = 1232141425L;
        return courseBaseService.createCourse(companyId, courseDto);
    }
}
