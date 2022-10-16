package com.kci.moodstore.framework.database.vo;

import java.util.List;

/**
 * @program: moodstore
 * @description: 分页展示层对象
 * @author: PlusL
 * @create: 2022-10-16 18:16
 **/
public class PageVO<T> {


    // 总页数
    private Integer pages;

    // 总条目数
    private Long total;

    // 结果集
    private List<T> list;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                ", pages=" + pages +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
