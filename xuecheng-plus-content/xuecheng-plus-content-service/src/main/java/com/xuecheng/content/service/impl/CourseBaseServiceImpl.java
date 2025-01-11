package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xuecheng.base.model.PageBean;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Author cliced
 * @Description 针对表【course_base(课程基本信息)】的数据库操作ServiceImpl
 * @Date 2025/01/10 11:28
 */
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase>
        implements CourseBaseService {
    /**
     * 课程分页查询
     *
     * @param pageBean       分页参数
     * @param queryCourseDto 查询条件
     * @return 分页结果
     */
    @Override
    public PageResult<CourseBase> queryCourseBaseList(PageBean pageBean, QueryCourseDto queryCourseDto) {
        Long pageNo = pageBean.getPageNo();
        Long pageSize = pageBean.getPageSize();
        String courseName = queryCourseDto.getCourseName();
        String auditStatus = queryCourseDto.getAuditStatus();
        String publishStatus = queryCourseDto.getPublishStatus();
        // 拼装查询条件，并分页查询
        // where course_base.name like '?%' and course_base.audit_status = ? and course_base.status = ?
        Page<CourseBase> queryPage = lambdaQuery()
                .like(StringUtils.isNotEmpty(courseName), CourseBase::getName, courseName)
                .eq(StringUtils.isNotEmpty(auditStatus), CourseBase::getAuditStatus, auditStatus)
                .eq(StringUtils.isNotEmpty(publishStatus), CourseBase::getStatus, publishStatus)
                .page(new Page<>(pageNo, pageSize));
        return new PageResult<>(queryPage.getRecords(), queryPage.getTotal(), pageNo, pageSize);
    }
}
