package com.highbury5.blog.service;

import com.highbury5.blog.mapper.ArticleDao;
import com.highbury5.blog.model.Article;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  highbury5
 * @date 2017-11-13
 */

@Service
public class ArticleService {

    //@Autowired
    //ArticleDao articleDao;

    @Autowired
    ArticleDao articleDao;

    public int articleAdd(Article article){
        articleDao.insert(article);
        return article.getId();
    }

    /*
    public Map<String,String> articleAdd(Article article){
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isBlank(article.getTitle())){
            map.put("emsg","文章标题不允许为空");
            return map;
        }

        //articleDao.insertArticle(article);
        articleDao.insert(article);
        map.put("smsg","添加成功");
        return map;
    }*/

    public Article articleDetail(int id) {
        return articleDao.findOne(id);
    }
}
