package com.xuecheng.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.system.mapper.DictionaryMapper;
import com.xuecheng.system.model.po.Dictionary;
import com.xuecheng.system.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author itcast
 */
@Slf4j
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    /**
     * 查询所有数据字典内容
     */
    @Override
    public List<Dictionary> queryAll() {
        return list();
    }

    /**
     * 根据 code 查询数据字典
     * @param code -- String 数据字典 code
     */
    @Override
    public Dictionary getByCode(String code) {
        return lambdaQuery().eq(Dictionary::getCode, code).one();
    }
}
