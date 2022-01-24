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
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Hello: ${user.userName}</h3>
<h2>User Name: <b>${user.userName}</b></h2>
<br/>
<h2>Gender: <b>${user.gender}</b></h2>
<br/>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
