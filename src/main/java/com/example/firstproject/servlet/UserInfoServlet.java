package com.example.firstproject.servlet;


import com.example.firstproject.bean.UserAccount;
import com.example.firstproject.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/userInfo")
public class UserInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public UserInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession httpSession = httpServletRequest.getSession();

        //Check User has logged on
        UserAccount loginedUser = MyUtils.getLoginedUser(httpSession);

        if (loginedUser == null) {
            //Redirect to login page
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return;
        }

        //Store info to the request attribute before forwarding
        httpServletRequest.setAttribute("user", loginedUser);

        //If the user has logged in, then forward to the page /WEB-INF/views/userInfoView.jsp

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest servletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(servletRequest, httpServletResponse);
    }

}
