package com.example.firstproject.servlet;


import com.example.firstproject.bean.Product;
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
import java.util.List;

@WebServlet(urlPatterns = {"/productList"})
public class ProductListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProductListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(httpServletRequest);

        String errorString = null;
        List<Product> list = null;
        try {
            list = DBUtils.queryProduct(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        //Store info in request attribute, before forward to views
        httpServletRequest.setAttribute("errorString", errorString);
        httpServletRequest.setAttribute("productList", list);

        //Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher requestDispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/productListView.jsp");
        requestDispatcher.forward(httpServletRequest,httpServletResponse);
    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        doGet(httpServletRequest, httpServletResponse);
    }
}
