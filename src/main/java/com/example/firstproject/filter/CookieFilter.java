package com.example.firstproject.filter;

import com.example.firstproject.bean.UserAccount;
import com.example.firstproject.utils.DBUtils;
import com.example.firstproject.utils.MyUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter {

    public CookieFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession httpSession = httpServletRequest.getSession();
        UserAccount userInSession = MyUtils.getLoginedUser(httpSession);

        if (userInSession != null) {
            httpSession.setAttribute("COOKIE_CHECKED", "CHECKED");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //Connection was created in JDBCFilter
        Connection connection = MyUtils.getStoredConnection(servletRequest);

        //Flag check cookie
        String checked = (String) httpSession.getAttribute("COOKIE_CHECKED");
        if (checked == null && connection != null) {
            String userName = MyUtils.getUserNameInCookie(httpServletRequest);
            try {
                UserAccount user = DBUtils.findUser(connection, userName);
                MyUtils.storeLoginedUser(httpSession, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            httpSession.setAttribute("COOKIE_CHECKED", "CHECKED");
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
