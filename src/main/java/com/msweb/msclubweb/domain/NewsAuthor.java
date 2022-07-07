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
 * @TableName news_author
 */
@TableName(value ="news_author")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsAuthor implements Serializable {
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
    private Integer authorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}