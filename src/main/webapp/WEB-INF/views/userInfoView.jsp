<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/24/22
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
</head>
<body style="font-family: 'Fira Code',sans-serif">

<jsp:include page="_headerAndFooter.jsp"></jsp:include>

<h3>Hello: ${user.userName}</h3>
<h2>User Name: <b>${user.userName}</b></h2>
<h2>Gender: <b>${user.gender}</b></h2>
<br/>
</body>
</html>
