package com.highbury5.blog.controller;

import com.highbury5.blog.model.Article;
import com.highbury5.blog.model.Tag;
import com.highbury5.blog.service.ArticleService;
import com.highbury5.blog.service.TagService;
import com.highbury5.blog.util.BlogUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author  highbury5
 * @date 2017-11-13
 */

@Controller
@RequestMapping("article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    TagService tagService;

    @RequestMapping("add")
    public String ArticleAdd(Model model, @RequestParam("title") String title, @RequestParam("describes") String describes, @RequestParam("content") String content,
                             @RequestParam("category") int category,@RequestParam("tag") String tag){
        Article article = new Article();
        article.setTitle(title);
        article.setDescribes(describes);
        article.setContent(BlogUtil.tranfer(content));
        article.setCategory(category);
        article.setCreatedDate(new Date());
        article.setCommentCount(0);

        //todo 待优化报错机制
        Map<String,String> map = new HashMap<>();
        if(StringUtils.isBlank(article.getTitle())){
            map.put("emsg","文章标题不允许为空");
        }else{
            int articleId = articleService.articleAdd(article);
            tagService.recodeTag(articleId,tag);
            map.put("smsg","添加成功");
        }

        if(map.containsKey("emsg")){
            model.addAttribute("emsg",map.get("emsg"));
            return "forward:/index";
        }
        if(map.containsKey("smsg")){
            model.addAttribute("smsg",map.get("smsg"));
        }
        return "forward:/index";
    }

    @RequestMapping("/{articleId}")
    public String ArticleDetail(Model model, @PathVariable("articleId") int articleId){
            Article article = articleService.articleDetail(articleId);
            if(article==null){
                model.addAttribute("emsg","文章不存在");
                return "index";
            }
            //获取文章标签
            List<Tag> tags = tagService.getTags(articleId);
            model.addAttribute("article",article);
            model.addAttribute("tags",tags);
            return "article";
    }



}
