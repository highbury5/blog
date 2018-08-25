package com.highbury5.blog.interceptor;


import com.highbury5.blog.mapper.LoginTicketDao;
import com.highbury5.blog.mapper.UserDao;
import com.highbury5.blog.model.LoginTicket;
import com.highbury5.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    UserDao userDao;

    @Autowired
    LoginTicketDao loginTicketDao;

    Map<String,String> map;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = "";
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("ticket")){
                ticket = cookie.getValue();
                break;
            }
        }

        map = new HashMap<String,String>();
        if(!"".equals(ticket)) {
            LoginTicket loginTicket = loginTicketDao.selectLoginTicketByTicket(ticket);
            User user = userDao.findUser(loginTicket.getUserId());
            if(loginTicket.getExpired().before(new Date())){
                map.put("emsg","请先登录");
                httpServletResponse.sendRedirect("/toLogin");
            }
            if(!user.getRole().equals("admin")){
                map.put("emsg","普通用户无法发表文章");
                httpServletResponse.sendRedirect("/toLogin");
            }
        }else{
            map.put("emsg","请先登录");
            httpServletResponse.sendRedirect("/toLogin");
        }
        System.out.println("PassportInterceptor--preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null){
            if(map.containsKey("emsg")){
                modelAndView.addObject("emsg",map.get("emsg"));
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
