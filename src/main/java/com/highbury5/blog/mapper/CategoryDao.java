package com.highbury5.blog.mapper;

import com.highbury5.blog.model.Category;

import java.util.List;

/**
 *  @author highbury5
 *  @date 2017-12-12
 */

public interface CategoryDao {
    public Category getCategoryByName(String name);

    public List<Category> listCategory();

    public void addCategory(Category category);

    public void insert(Category category);

}
