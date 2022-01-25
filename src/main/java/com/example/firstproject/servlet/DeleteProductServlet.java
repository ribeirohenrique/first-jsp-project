package com.example.firstproject.servlet;


import com.example.firstproject.utils.DBUtils;
import com.example.firstproject.utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteProductServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(httpServletRequest);
        String code = (String) httpServletRequest.getParameter("code");
        String errorString = null;

        try {
            DBUtils.deleteProduct(connection, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        //If has an error, redirect to the error page
        if (errorString != null) {
            //Store the information in the request attribute before forward to views

            httpServletRequest.setAttribute("errorString", errorString);
            RequestDispatcher requestDispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }

        //If everything nice redirect to the prodct listing page
        else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/productList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
