<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body style="font-family: 'Fira Code',sans-serif">
<jsp:include page="_headerAndFooter.jsp"/>
<h3>Home Page</h3>
This is demo Simple web app using JSP, Servlet and JDBC. <br><br>
<b>It includes the following functions:</b>

<ul class="list-group list-group-numbered">
    <li class="list-group-item">Login</li>
    <li class="list-group-item">Storing user information in cookies</li>
    <li class="list-group-item">Product List</li>
    <li class="list-group-item">Create Product</li>
    <li class="list-group-item">Edit Product</li>
    <li class="list-group-item">Delete Product</li>
</ul>
</body>
</html>
