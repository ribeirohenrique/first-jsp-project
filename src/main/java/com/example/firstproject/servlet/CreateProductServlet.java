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

@WebServlet(urlPatterns = {"/createProduct"})
public class CreateProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CreateProductServlet() {
        super();
    }


    //Show product creation page
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    //When the user enter the product information and
    //click Submit this method will be called
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        Connection connection = MyUtils.getStoredConnection(httpServletRequest);
        String code = (String) httpServletRequest.getParameter("code");
        String name = (String) httpServletRequest.getParameter("name");
        String priceStr = (String) httpServletRequest.getParameter("price");
        float price = 0;

        try {
            price = Float.parseFloat(priceStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Product product = new Product(code, name, price);
        String errorString = null;

        //Product ID is the string literal [a-zA-Z_0-9]
        //With at least 1 character
        String regex = "\\w+";

        if (code == null || !code.matches(regex)) {
            errorString = "Product Code Invalid!";
        }

        if (errorString == null) {
            try {
                DBUtils.insertProduct(connection, product);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
        }

        //Store information to request attribute before forward to views
        httpServletRequest.setAttribute("errorString", errorString);
        httpServletRequest.setAttribute("product", product);

        //If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher requestDispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/createProductView.jsp");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }

        //If everything nice, redirect to the product listing page
        else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/productList");
        }
    }
}
