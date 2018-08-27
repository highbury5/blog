package com.highbury5.blog.service;

import com.highbury5.blog.mapper.ArticleTagDao;
import com.highbury5.blog.mapper.TagDao;
import com.highbury5.blog.model.ArticleTag;
import com.highbury5.blog.model.Tag;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    TagDao tagDao;

    @Autowired
    ArticleTagDao articleTagDao;

    /**
     * 记录文章tag信息
     */
    public void recodeTag(int articleId,String tag) {
        if (StringUtils.isBlank(tag)) {
            return;
        }
        String[] tags = tag.split(",");
        for (String currentTag : tags) {
            Tag tagObject;
            //若tag不存在则先创建
            if ((tagObject = tagDao.getTagByName(currentTag)) == null) {
                tagObject = new Tag();
                tagObject.setName(currentTag);
                tagDao.insert(tagObject);
            }
            int tagId = tagObject.getId();

            //插入关联表
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(articleId);
            articleTag.setTagId(tagId);
            articleTagDao.insert(articleTag);
        }
    }

    /**
     * 获取文章标签
     */
    public List<Tag> getTags(int articleId){
        return tagDao.getTagsByArticleId(articleId);
    }
    /**
     * 获取所有标签
     */
    public List<Tag> getAllTags(){
        return tagDao.listTags();
    }

    /**
     * 根据tag Id获取tag
     */
    public Tag getTag(int id){
        return tagDao.findById(id);
    }

}