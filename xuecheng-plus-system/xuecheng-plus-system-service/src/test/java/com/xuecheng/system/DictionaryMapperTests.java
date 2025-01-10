package com.xuecheng.system;

import com.xuecheng.system.mapper.DictionaryMapper;
import com.xuecheng.system.model.po.Dictionary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author cliced
 * @Version 1.0
 * @Description DictionaryMapper 测试类
 * @Date 2025/01/10 19:07
 */
@SpringBootTest
public class DictionaryMapperTests {
    @Autowired
    DictionaryMapper dictionaryMapper;

    @Test
    void test() {
        Dictionary dictionary = dictionaryMapper.selectById(12L);
        System.out.println(dictionary);
    }
}
