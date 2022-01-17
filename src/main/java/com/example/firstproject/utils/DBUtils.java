package com.example.firstproject.utils;

import com.example.firstproject.bean.Product;
import com.example.firstproject.bean.UserAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static UserAccount findUser(Connection connection, String userName, String password) throws SQLException {
        String sql = "SELECT A.USER_NAME, A.PASSWORD, A.GENDER FROM USER_ACCOUNT A" +
                " WHERE A.USER_NAME = ? AND A.PASSWORD = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String gender = resultSet.getString("Gender");
            UserAccount userAccount = new UserAccount();
            userAccount.setUserName(userName);
            userAccount.setPassword(password);
            userAccount.setGender(gender);
            return userAccount;
        }
        return null;
    }
    public static UserAccount findUser(Connection connection, String userName) throws  SQLException {
        String sql = "SELECT A.USER_NAME, A.PASSWORD, A.GENDER FROM USER_ACCOUNT A" +
                " WHERE A.USER_NAME = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String password = resultSet.getString("Password");
            String gender = resultSet.getString("Gender");
            UserAccount userAccount = new UserAccount();
            userAccount.setUserName(userName);
            userAccount.setPassword(password);
            userAccount.setGender(gender);
            return userAccount;
        }
        return null;
    }

    public static List<Product> queryProduct(Connection connection) throws SQLException {
        String sql = "SELECT A.CODE, A.NAME, A.PRICE FROM PRODUCT A";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (resultSet.next()) {
            String code = resultSet.getString("Code");
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }

    public static Product findProduct(Connection connection, String code) throws SQLException {
        String sql = "SELECT A.CODE, A.NAME, A.PRICE FROM PRODUCT A WHERE A.CODE = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, code);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString("Name");
            float price = resultSet.getFloat("Price");
            Product product = new Product(code, name, price);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection connection, Product product) throws SQLException {
        String sql = "UPDATE PRODUCT SET NAME = ?, PRICE = ? WHERE CODE = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setFloat(2, product.getPrice());
        preparedStatement.setString(3, product.getCode());
        preparedStatement.executeUpdate();
    }

    public static void insertProduct(Connection connection, Product product) throws SQLException {
        String sql = "INSERT INTO PRODUCT(CODE,NAME,PRICE) VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, product.getCode());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setFloat(3, product.getPrice());
        preparedStatement.executeUpdate();
    }

    public static void deleteProduct(Connection connection, String code) throws SQLException {
        String sql = "DELETE FROM PRODUCT WHERE CODE = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, code);
        preparedStatement.executeUpdate();
    }
}
