package com.highbury5.blog.service;


import com.highbury5.blog.mapper.LoginTicketDao;
import com.highbury5.blog.mapper.UserDao;
import com.highbury5.blog.model.LoginTicket;
import com.highbury5.blog.model.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  @author highbury5
 *  @date 2017-11-10
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDao loginTicketDao;

    public Map<String,String> register(String username, String password){
        Map<String,String> map = new HashMap<String,String>();
        if(StringUtils.isBlank(username)){
            map.put("emsg","用户名不能为空");
            return map;
        }

        if(StringUtils.isBlank(password)){
            map.put("emsg","密码不能为空");
            return map;
        }

            User checkUser = userDao.findUserByName(username);
        if(checkUser != null){
            map.put("emsg","用户名已存在");
            return map;
        }

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setRole("user");
        userDao.insertUser(user);
        map.put("smsg","注册成功");
        return map;
    }

    public Map<String,String> login(String username,String password){
        Map<String,String> map =new HashMap<String,String>();
        if(StringUtils.isBlank(username)){
            map.put("emsg","用户名必须输入");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("emsg","密码必须输入");
            return map;
        }

        User user = userDao.findUserByName(username);
        if(user == null){
            map.put("emsg","用户名不存在");
            return map;
        }
        if(!user.getPassword().equals(password)){
            map.put("emsg","密码错误");
            return map;
        }

        String ticket = addLoginTicket(user.getId());
        map.put("smsg","登录成功");
        map.put("ticket",ticket);
        return map;
    }

    public String addLoginTicket(int userId){
        Date date = new Date();
        date.setTime(date.getTime()+1000*3600*30);

        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        loginTicket.setTicket(UUID.randomUUID().toString());
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);

        loginTicketDao.insertLoginTicket(loginTicket);
        //loginTicketRepository.save(loginTicket);
        return loginTicket.getTicket();
    }
}
