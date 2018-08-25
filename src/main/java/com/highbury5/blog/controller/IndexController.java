package com.highbury5.blog.controller;

import com.highbury5.blog.common.Page;
import com.highbury5.blog.model.Category;
import com.highbury5.blog.service.ArchiveService;
import com.highbury5.blog.service.CategoryService;
import com.highbury5.blog.service.UserService;
import com.highbury5.blog.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author  highbury5
 * @date 2017-11-10
 */


@Controller
public class IndexController {
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArchiveService archiveService;

    @Autowired
    DateUtil dateUtil;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        Page page = archiveService.listArticle(1);
        model.addAttribute("page",page);
        model.addAttribute("categoryMap",categoryService.categoryOptionByMap());
        model.addAttribute("dateUtil",dateUtil);
        return "index";
    }

    /*@RequestMapping(value={"/","/index"})
    public String index(){
        return "index";
    }*/

    @RequestMapping("/toLogin")
    public String in() {
        return "login";
    }

    @RequestMapping("/toCreate")
    public String toCreate(Model model) {
        List list = categoryService.categoryOption();
        model.addAttribute("categorys",list);
        return "create";
    }

    @RequestMapping("about")
    public String about(){
        return "about";
    }

    @RequestMapping("admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/register")
    public String register(Model model, @RequestParam String username, @RequestParam String password) {
        Map<String, String> map = userService.register(username, password);
        if (map.containsKey("smsg")) {
            return "redirect:/";
        } else {
            if (map.containsKey("emsg")) {
                model.addAttribute("emsg", map.get("emsg"));
            }
            return "login";
        }
    }

    @RequestMapping("login")
    public String login(Model model, HttpServletResponse httpResponse, @RequestParam String username, @RequestParam String password) {
        Map<String,String> map = userService.login(username,password);
        if(map.containsKey("smsg")){
            Cookie cookie = new Cookie("ticket",map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);
            return "redirect:/";
        }else{
            if(map.containsKey("emsg")){
                model.addAttribute("emsg",map.get("emsg"));
            }
            return "login";
        }
    }
}
