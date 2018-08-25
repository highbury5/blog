package com.highbury5.blog.controller;

import com.highbury5.blog.model.Category;
import com.highbury5.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 *  @author highbury5
 *  @date 2017-12-12
 */

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping("/list")
    public String listCategory(Model model){
        List list = categoryService.categoryOption();
        model.addAttribute("categorys",list);
        return "category";
    }

    @RequestMapping("add")
    public String addCategory(@RequestParam String name,@RequestParam String describes,Model model){
        Category category = new Category();
        category.setName(name);
        category.setDescribes(describes);
        category.setImage("");
        category.setArticleNum(0);
        categoryService.addCategory(category);

        List list = categoryService.categoryOption();
        model.addAttribute("categorys",list);
        return "category";
    }

}
