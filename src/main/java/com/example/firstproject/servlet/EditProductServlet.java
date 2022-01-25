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

@WebServlet(urlPatterns = {"/editProduct"})
public class EditProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
    }

    //Show product edit page.
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Connection connection = MyUtils.getStoredConnection(httpServletRequest);
        String code = (String) httpServletRequest.getParameter("code");
        Product product = null;
        String errorString = null;

        try {
            product = DBUtils.findProduct(connection, code);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        //If no Error the product does not exist to edit
        //Redirect to productList page
        if (errorString != null && product == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getServletPath() + "/productList");
            return;
        }

        //Store errorString in request attribute, before forward to views
        httpServletRequest.setAttribute("errorString", errorString);
        httpServletRequest.setAttribute("product", product);

        RequestDispatcher requestDispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    //After the user modifies the product information and click Submit
    //This method will be executed.

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

        try {
            DBUtils.updateProduct(connection, product);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        //Store information to request attribute before forward to views.
        httpServletRequest.setAttribute("errorString", errorString);
        httpServletRequest.setAttribute("product", product);

        //If error, forward to Edit page
        if (errorString != null) {
            RequestDispatcher requestDispatcher = httpServletRequest.getServletContext().getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
            requestDispatcher.forward(httpServletRequest, httpServletResponse);
        }
        //If everything nice
        //Redirect to the product listing page
        else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/productList");
        }
    }
}
