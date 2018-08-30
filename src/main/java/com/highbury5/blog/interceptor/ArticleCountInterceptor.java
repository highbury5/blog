package com.highbury5.blog.interceptor;

import com.highbury5.blog.util.RedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ArticleCountInterceptor  implements HandlerInterceptor {
    private static final Log log = LogFactory.getLog(ArticleCountInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        String articleId = uri.split("/")[3];
        try {
            //redis记录访问次数
            RedisUtil.zincrby("articleRange", String.valueOf(articleId));
        }catch (Exception e){
            //todo 优化错误日志输出
            log.error("redis记录文字访问次数异常 ");
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
