<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/17/22
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<h3>Home Page</h3>
This is demo Simple web app using JSP, Servlet and JDBC. <br><br>
<b>It includes the following functions:</b>
<ul>
    <li>Login</li>
    <li>Storing user information in cookies</li>
    <li>Product List</li>
    <li>Create Product</li>
    <li>Edit Product</li>
    <li>Delete Product</li>
</ul>

<jsp:include page="_footer.jsp"/>
</body>
</html>
