package com.highbury5.blog.mapper;

import com.github.pagehelper.PageRowBounds;
import com.highbury5.blog.model.Article;

import java.util.List;

/**
 * @author  highbury5
 * @date 2017-11-13
 */


public interface ArticleDao {

    public List<Article> listArticle();

    public List<Article> listArticleByTag(int tagId);

    public List<Article> listArticle(PageRowBounds pageRowBounds);

    public Article findOne(int id);

    public void insert(Article article);
}
