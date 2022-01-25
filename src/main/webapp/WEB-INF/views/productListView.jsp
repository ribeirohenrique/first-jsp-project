<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body style="font-family: 'Fira Code',sans-serif">

<jsp:include page="_headerAndFooter.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Code</th>
        <th scope="col">Name</th>
        <th scope="col">Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="product" >
        <tr>
            <th scope="row">${product.code}</th>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="editProduct?code=${product.code}">Edit</a>
            </td>
            <td>
                <a href="deleteProduct?code=${product.code}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a class="btn btn-primary" href="createProduct">Create Product</a>
</body>
</html>
