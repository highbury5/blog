package com.highbury5.blog.service;

import com.highbury5.blog.mapper.CategoryDao;
import com.highbury5.blog.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  @author highbury5
 *  @date 2017-12-12
 */

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public  List categoryOption(){
        return categoryDao.listCategory();
    }

    public Map<Integer,String> categoryOptionByMap(){
        Map<Integer,String> map = new HashMap<Integer, String>();
        List<Category> list = categoryDao.listCategory();
        for(Category category : list){
            map.put(category.getId(),category.getName());
        }
        return  map;
    }

   public void addCategory(Category category){
        categoryDao.insert(category);
    }

    /*
    public Category getCategoryByName(String name){
        return categoryRepository.findCategoryByName(name);
    }

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }*/

}
