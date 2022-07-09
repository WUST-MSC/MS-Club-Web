package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value ="administrator")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Administrator {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String qq;

    private String email;

    private String introduction;

    private String position;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
