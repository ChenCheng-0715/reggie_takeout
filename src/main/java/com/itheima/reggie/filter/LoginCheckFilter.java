package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.itheima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: LoginCheckFilter
 * Package: com.itheima.reggie.filter
 * Description:
 *
 * @Author:
 * @Create: 5/10/2023 - 9:23 pm
 * @Version:
 */
@WebFilter(filterName =  "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // Path matcher
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. get request URI
        String requestURI = request.getRequestURI();

        log.info("Filtered request: {}", requestURI);

        // define urls not request filter
        String[] urls = new String[] {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };

        // 2. check whether request need filter
        boolean check = check(urls, requestURI);

        // 3. if no need filter, let go thru
        if (check) {
            log.info("Request {} Need not filter", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 4. check whether logged in, if logged, let go thru
        if (request.getSession().getAttribute("employee") != null) {
            log.info("User logged in, User ID {}", request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }

        // 5. if not logged in, send data to web page thru stream

        log.info("User not logged in");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));



        return;
    }

    /**
     * Path matcher, check request whether need filter
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
