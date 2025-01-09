package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cliced
 * @Version 1.0
 * @Description CourseBaseMapper测试类
 * @Date 2025/01/09 21:46
 */
@MapperScan(basePackages = "com.xuecheng.content.mapper")
@SpringBootTest
public class CourseBaseMapperTests {
    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Test
    void testSelectById() {
        CourseBase courseBase = courseBaseMapper.selectById(1);
        Assertions.assertNotNull(courseBase);
    }

    /**
     * 测试分页查询
     */
    @Test
    void testSelectPage() {
        // 分页参数
        PageBean pageBean = new PageBean(1L, 2L);
        long pageNo = pageBean.getPageNo();
        long pageSize = pageBean.getPageSize();
        // 查询条件
        QueryCourseDto queryCourseDto = new QueryCourseDto();
        queryCourseDto.setCourseName("java");
        String courseName = queryCourseDto.getCourseName();
        String auditStatus = queryCourseDto.getAuditStatus();
        // 拼接查询条件
        // where course_base.name like '?%' and course_base.audit_status = ?
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<CourseBase>()
                .like(StringUtils.isNotEmpty(courseName), CourseBase::getName, courseName)
                .eq(StringUtils.isNotEmpty(auditStatus), CourseBase::getAuditStatus, auditStatus);
        // 分页查询
        Page<CourseBase> queryPage = courseBaseMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
        // 封装查询结果
        PageResult<CourseBase> pageResult = new PageResult<>(queryPage.getRecords(), queryPage.getTotal(), pageNo, pageSize);
        System.out.println(pageResult);
    }
}
