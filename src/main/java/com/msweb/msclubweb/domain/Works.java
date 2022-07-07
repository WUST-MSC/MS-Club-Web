package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value ="works")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Works {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String introduction;

    private String title;

    private String name;

    private String href;

    private String img;

    private Integer flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
