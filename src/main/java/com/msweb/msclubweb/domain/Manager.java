package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName manager
 */
@TableName(value ="manager")
@Data
public class Manager implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String qq;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Integer departmentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}