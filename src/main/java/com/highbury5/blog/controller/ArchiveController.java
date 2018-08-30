package com.highbury5.blog.controller;

import com.highbury5.blog.common.Page;
import com.highbury5.blog.model.Article;
import com.highbury5.blog.service.ArchiveService;
import com.highbury5.blog.service.ArticleService;
import com.highbury5.blog.service.CategoryService;
import com.highbury5.blog.util.DateUtil;
import com.highbury5.blog.util.RedisUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author highbury5
 * @date 2017-12-12
 */

@Controller
@RequestMapping("/archive")
public class ArchiveController {
    private static final Log log = LogFactory.getLog(ArchiveService.class);

    private final static String RANGETOPIC = "articleRange";

    @Autowired
    ArchiveService archiveService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Autowired
    DateUtil dateUtil;

    @RequestMapping("/list")
    public String listArticle(Model model) {
        List<Article> articles = archiveService.listArticle();
        model.addAttribute("articles", articles);
        return "archive";
    }

    @RequestMapping("page")
    public String page(Model model, @RequestParam("pageNum") Integer pageNum) {
        Page page = archiveService.listArticle(pageNum);
        model.addAttribute("page", page);
        model.addAttribute("categoryMap", categoryService.categoryOptionByMap());
        model.addAttribute("dateUtil", dateUtil);
        return "page";
    }

    @RequestMapping("range")
    public String range(Model model) {
        Map<String, Double> articleMap = RedisUtil.zrevrange(RANGETOPIC);
        List<Article> articles = new ArrayList<>();
        Map<Integer,Integer> scores = new HashMap<>();
        for (Map.Entry entry : articleMap.entrySet()) {
            int articleId = Integer.parseInt((String) entry.getKey());
            articles.add(articleService.articleDetail(articleId));

            double score  = (Double)entry.getValue();
            scores.put(articleId, (int)score);
        }
        model.addAttribute("articles", articles);
        model.addAttribute("scores", scores);
        return "range";
    }

}
