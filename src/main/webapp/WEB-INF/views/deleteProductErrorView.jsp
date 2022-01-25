<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/24/22
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Delete Product</title>
</head>
<body>

<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>

<h3>Delete Product</h3>

<p style="color: red;">${errorString}</p>
<a href="productList">Product List</a>

<jsp:include page="_footer.jsp"/>

</body>
</html>
