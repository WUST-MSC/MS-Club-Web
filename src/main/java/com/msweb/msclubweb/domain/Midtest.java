package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName midtest
 */
@TableName(value ="midtest")
@Data
public class Midtest implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}