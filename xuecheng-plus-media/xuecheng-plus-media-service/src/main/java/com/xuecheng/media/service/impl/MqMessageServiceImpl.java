package com.xuecheng.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.media.model.po.MqMessage;
import com.xuecheng.media.service.MqMessageService;
import com.xuecheng.media.mapper.MqMessageMapper;
import org.springframework.stereotype.Service;

/**
 * @Author cliced
 * @Description 针对表【mq_message】的数据库操作Service实现
 * @Date 2025-01-17 02:14:37
 */
@Service
public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage>
        implements MqMessageService {

}




