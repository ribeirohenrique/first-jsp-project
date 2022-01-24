package com.example.firstproject.servlet;

import com.example.firstproject.bean.UserAccount;
import com.example.firstproject.utils.DBUtils;
import com.example.firstproject.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    //Show login page
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String userName = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        String rememberMeStr = httpServletRequest.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection connection = MyUtils.getStoredConnection(httpServletRequest);
            try {
                //Find the user in Database
                user = DBUtils.findUser(connection, userName, password);

                if (user == null) {
                    hasError = true;
                    errorString = "Username or password invalid.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        //If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            //Store information in request attribute, before forward
            httpServletRequest.setAttribute("errorString", errorString);
            httpServletRequest.setAttribute("user", user);

            //Forward to /WEB-INF/views/login.jsp
            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }

        //If no error
        //Store user information in Session
        //And redirect to userInfo page

        else {
            HttpSession httpSession = httpServletRequest.getSession();
            MyUtils.storeLoginedUser(httpSession, user);

            //If user checked "Remember Me"
            if (remember) {
                MyUtils.storeUserCookie(httpServletResponse, user);
            } else {
                MyUtils.deleteUserCookie(httpServletResponse);
            }

            //Redirect to userInfo page
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "userInfo");

        }
    }

}
