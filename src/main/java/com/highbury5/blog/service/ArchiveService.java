package com.highbury5.blog.service;

import com.github.pagehelper.PageRowBounds;
import com.highbury5.blog.common.Constants;
import com.highbury5.blog.common.Page;
import com.highbury5.blog.mapper.ArticleDao;
import com.highbury5.blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveService {

    //@Autowired
    //ArticleDao articleDao;

    @Autowired
    ArticleDao articleDao;

    public List<Article> listArticle(){
        return articleDao.listArticle();
    }

    public List<Article> lsitArticleByTag(int tagId){
       return articleDao.listArticleByTag(tagId);
    }

    public Page listArticle(int pagenum){
        //Pageable pageable= new PageRequest(page,3);
        int index = ( pagenum - 1 )  * Constants.PAGE_SIZE;
        PageRowBounds pageRowBounds = new PageRowBounds(index, Constants.PAGE_SIZE);
        List<Article> list = articleDao.listArticle(pageRowBounds);
        //根据总记录数和每页记录数计算总页数
        int totalNum = new Long(pageRowBounds.getTotal()).intValue();
        int totalPage =  (totalNum % Constants.PAGE_SIZE == 0) ? totalNum/Constants.PAGE_SIZE : totalNum/Constants.PAGE_SIZE + 1;
        Page page = new Page(list,totalPage,pagenum);
        return page;
    }

}
