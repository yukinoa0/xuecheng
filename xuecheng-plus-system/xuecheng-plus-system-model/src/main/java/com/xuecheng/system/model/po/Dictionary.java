package com.xuecheng.system.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据字典
 *
 * @TableName dictionary
 */
@TableName(value = "dictionary")
@Data
public class Dictionary implements Serializable {
    /**
     * id标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据字典名称
     */
    private String name;

    /**
     * 数据字典代码
     */
    private String code;

    /**
     * 数据字典项--json格式
     *
     * <pre>
     * [{
     *      "sd_name": "低级",
     *      "sd_id": "200001",
     *      "sd_status": "1"
     *   }, {
     *      "sd_name": "中级",
     *      "sd_id": "200002",
     *      "sd_status": "1"
     *   }, {
     *      "sd_name": "高级",
     *      "sd_id": "200003",
     *      "sd_status": "1"
     *   }]
     * </pre>
     */
    private String itemValues;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}