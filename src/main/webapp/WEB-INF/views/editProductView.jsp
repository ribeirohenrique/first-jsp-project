<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body style="font-family: 'Fira Code',sans-serif">

<jsp:include page="_headerAndFooter.jsp"/>

<h3>Edit Product</h3>
<p style="color: red;">${errorString}</p>

<c:if test="${not empty product}">
    <form method="POST" action="${pageContext.request.contextPath}/editProduct">
    <input type="hidden" name="code" value="${product.code}"/>
    <table border="0">
    <tr>
    <td>Code</td>
    <td style="color: red;">${product.code}</td>
    </tr>
    <tr>
    <td>Name</td>
    <td>
    <input type="text" name="name" value="${product.name}"/>
    </td>
    </tr>
    <tr>
    <td>Price</td>
    <td>
    <input type="text" name="price" value="${product.price}"/>
    </td>
    </tr>
    <tr>
    <td colspan="2">
    <input type="submit" value="Submit"/>
    <a href="${pageContext.request.contextPath}/productList">Cancel</a>
    </td>
    </tr>
    </table>
    </form>
</c:if>
</body>
</html>
