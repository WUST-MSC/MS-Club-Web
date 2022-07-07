package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value ="recruitment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recruitment {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String student_id;

    private String phone;

    private String qq;

    private String email;

    private Integer interest_department;

    private String classname;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
