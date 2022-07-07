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
 * @TableName news_imags
 */
@TableName(value ="news_imags")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsImags implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer newsId;

    /**
     * 
     */
    private Integer imagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}