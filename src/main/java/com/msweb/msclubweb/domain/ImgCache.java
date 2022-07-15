package com.msweb.msclubweb.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName img_cache
 */
@TableName(value ="img_cache")
@Data
public class ImgCache implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String src;

    /**
     *
     */
    private Integer imgId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}