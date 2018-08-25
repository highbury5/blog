package com.highbury5.blog.controller;

import com.highbury5.blog.common.Page;
import com.highbury5.blog.model.Article;
import com.highbury5.blog.service.ArchiveService;
import com.highbury5.blog.service.CategoryService;
import com.highbury5.blog.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

/**
 *  @author highbury5
 *  @date 2017-12-12
 */

@Controller
@RequestMapping("/archive")
public class ArchiveController {

    @Autowired
    ArchiveService archiveService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DateUtil dateUtil;

    @RequestMapping("/list")
    public String listArticle(Model model){
        List<Article> articles = archiveService.listArticle();
        model.addAttribute("articles",articles);
        return "archive";
    }

    @RequestMapping("page")
    public String page(Model model,@RequestParam("pageNum") Integer pageNum){
        Page page = archiveService.listArticle(pageNum);
        model.addAttribute("page",page);
        model.addAttribute("categoryMap",categoryService.categoryOptionByMap());
        model.addAttribute("dateUtil",dateUtil);
        return "page";
    }

}
