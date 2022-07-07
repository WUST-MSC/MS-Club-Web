package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName(value ="honor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Honor {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String introduction;

    private String title;

    private String name;

    private String img;

    //flag=1 代表团体荣誉 flag=2 代表个人荣誉
    private Integer flag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
