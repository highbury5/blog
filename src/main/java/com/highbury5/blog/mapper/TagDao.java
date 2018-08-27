package com.highbury5.blog.mapper;

import com.highbury5.blog.model.Tag;

import java.util.List;

public interface TagDao {

    Tag getTagByName(String name);

    void insert(Tag tag);

    List<Tag> getTagsByArticleId(int articleId);

    List<Tag> listTags();

    Tag findById(int id);
}
