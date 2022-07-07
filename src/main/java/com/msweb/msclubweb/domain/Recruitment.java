package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName recruitment
 */
@TableName(value ="recruitment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruitment implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String studentId;

    /**
     * 
     */
    private String phone;

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
    private Integer interestDepartment;

    /**
     * 
     */
    private String classname;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}