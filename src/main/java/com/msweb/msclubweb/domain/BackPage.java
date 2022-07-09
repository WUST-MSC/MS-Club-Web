package com.msweb.msclubweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BackPage<T> {


    /**
     * 总页数
     */
    private long totalPage;

    /**
     * 当前页数
     */
    private long currentPage;

    /**
     * 总数
     */
    private long totalNum;

    /**
     * 内容
     */
    private List<T> contentList;
}

