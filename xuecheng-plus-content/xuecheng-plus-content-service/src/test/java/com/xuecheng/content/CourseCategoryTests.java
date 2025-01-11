package com.xuecheng.content;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 课程分类测试类
 * @Date 2025/01/11 15:59
 */
@SpringBootTest
public class CourseCategoryTests {
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Autowired
    private CourseCategoryService courseCategoryService;

    @Test
    void testSelectTreeNodes() {
        List<CourseCategoryTreeDto> treeDtos = courseCategoryMapper.selectTreeNodes("1");
        System.out.println(treeDtos);
    }

    @Test
    void testQueryTreeNodes() {
        List<CourseCategoryTreeDto> treeDtos = courseCategoryService.queryTreeNodes("1");
        System.out.println(treeDtos);
    }
}
