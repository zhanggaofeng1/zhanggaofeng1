/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.interceptors;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Administrator
 */
public class RequestCtrl extends HandlerInterceptorAdapter {

    private List<String> excludeMethods;

    public List<String> getExcludeMethods() {
        return excludeMethods;
    }

    public void setExcludeMethods(List<String> excludeMethods) {
        this.excludeMethods = excludeMethods;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (excludeMethods.contains(getMethodName(request.getRequestURI()))) {
            return true;
        }

        String uname = (String) request.getSession().getAttribute("uname");
        if (uname == null || uname.isEmpty()) {
            request.getRequestDispatcher("/page/error.jsp").forward(request, response);
            return false;
        }

        // 还可以细化每个用户的具体权限
        return true;
    }

    private String getMethodName(String url) {
        int position = url.lastIndexOf('/');
        return url.substring(position + 1);
    }
}
