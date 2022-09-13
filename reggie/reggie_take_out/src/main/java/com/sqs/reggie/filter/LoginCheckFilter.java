package com.sqs.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.sqs.reggie.common.BaseContext;
import com.sqs.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author : kaka
 * @Date: 2022-09-11 17:16
 * @Description: 检查用户是否登录
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1、获取本次请求的Url
        String uri = request.getRequestURI();

        //定义不需要处理的请求
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
        };
        //2、判断是否需要处理
        boolean check = check(urls, uri);
        //3、如果不需要处理，则直接放行
        if (check) {
            filterChain.doFilter(request, response);
            return;
        }
        //4、判断是否登录
        Long currentId = (Long) request.getSession().getAttribute("employee");
        if (currentId != null) {

            BaseContext.setCurrentId(currentId);

            filterChain.doFilter(request, response);
            return;
        }
        //5、没有登录则返回登录，通过输出流向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配
     * @param urls
     * @param uri
     * @return
     */
    public boolean check(String[] urls, String uri) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, uri);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
