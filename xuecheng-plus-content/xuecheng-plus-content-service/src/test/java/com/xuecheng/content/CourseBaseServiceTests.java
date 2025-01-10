package com.xuecheng.content;

import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cliced
 * @Version 1.0
 * @Description CourseBaseService 测试类
 * @Date 2025/01/10 12:00
 */
@SpringBootTest
public class CourseBaseServiceTests {
    @Autowired
    CourseBaseService courseBaseService;

    /**
     * 测试分页查询
     */
    @Test
    void testQueryCourseBaseList() {
        // 分页参数
        PageBean pageBean = new PageBean(2L, 2L);
        // 查询条件
        QueryCourseDto queryCourseDto = new QueryCourseDto();
        queryCourseDto.setCourseName("java");
        queryCourseDto.setAuditStatus("202004"); // 202004 表示课程审核通过
        PageResult<CourseBase> pageResult = courseBaseService.queryCourseBaseList(pageBean, queryCourseDto);
        System.out.println(pageResult);
    }
}
