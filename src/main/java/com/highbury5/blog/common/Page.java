package com.highbury5.blog.common;

import java.util.List;

public class Page {

    private List<?> contents;

    private int totalPage;

    private int currentPage;

    public Page(List<?> list, int totalPage, int currentPage){
        this.contents = list;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public List<?> getContents() {
        return contents;
    }

    public void setContents(List<?> contents) {
        this.contents = contents;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
