package com.xuecheng.system;

import com.xuecheng.system.model.po.Dictionary;
import com.xuecheng.system.service.DictionaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author cliced
 * @Version 1.0
 * @Description DictionaryService 测试类
 * @Date 2025/01/10 18:38
 */
@SpringBootTest
public class DictionaryServiceTests {
    @Autowired
    DictionaryService dictionaryService;

    @Test
    void testQueryAll() {
        List<Dictionary> dictionaries = dictionaryService.queryAll();
        System.out.println(dictionaries);
    }
}
