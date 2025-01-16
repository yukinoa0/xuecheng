package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.TeachplanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程计划服务测试类
 * @Date 2025/01/16 16:31
 */
@SpringBootTest
public class TeachplanServiceTests {
    @Autowired
    private TeachplanService teachplanService;

    @Test
    void testGetMaxOrderBy() {
        Long courseId = 117L;
        Long parentid = 0L;
        QueryWrapper<Teachplan> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("orderby").lambda()
                .eq(Teachplan::getCourseId, courseId)
                .eq(Teachplan::getParentid, parentid)
                .last("limit 1");
        Teachplan teachplan = teachplanService.getBaseMapper().selectOne(queryWrapper);
        System.out.println(teachplan);
    }
}
