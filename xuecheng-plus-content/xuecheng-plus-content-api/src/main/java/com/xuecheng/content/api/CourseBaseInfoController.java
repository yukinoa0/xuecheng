package com.xuecheng.content.api;

import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程信息接口
 * @Date 2024/10/18 20:40
 */
@Api(tags = "课程信息编辑接口")
@RestController
public class CourseBaseInfoController {
    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageBean pageBean,
                                       @RequestBody(required = false) QueryCourseDto queryCourseDto) {
        Long page = pageBean.getPageNo();
        Long pageSize = pageBean.getPageSize();
        CourseBase courseBase = new CourseBase();
        courseBase.setName("测试名称");
        courseBase.setCreateDate(LocalDateTime.now());
        List<CourseBase> courseBases = new ArrayList<>();
        courseBases.add(courseBase);
        return new PageResult<>(courseBases,10,page,pageSize);
    }
}
