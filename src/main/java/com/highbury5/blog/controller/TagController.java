package com.highbury5.blog.controller;

import com.highbury5.blog.model.Article;
import com.highbury5.blog.model.Tag;
import com.highbury5.blog.service.ArchiveService;
import com.highbury5.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("tag")
@Controller
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    ArchiveService archiveService;

    //标签云
    @RequestMapping("/show")
    public String tag(Model model){
        List<Tag> tags = tagService.getAllTags();
        model.addAttribute("tags",tags);
        return "tagcloud";
    }

    @RequestMapping("/{tagId}")
    public String listByTag(Model model, @PathVariable("tagId") int tagId){
        Tag tag = tagService.getTag(tagId);
        List<Article> articleList =  archiveService.lsitArticleByTag(tagId);
        model.addAttribute("articles",articleList);
        model.addAttribute("tag",tag);
        return "tag_archive";
    }

}
