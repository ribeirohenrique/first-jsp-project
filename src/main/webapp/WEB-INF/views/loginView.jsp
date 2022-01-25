<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</head>
<body style="font-family: 'Fira Code',sans-serif">
<jsp:include page="_headerAndFooter.jsp"></jsp:include>
<h3>Login Page</h3>
<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/login">
        <div class="mb-3">
            <label for="userName" class="form-label">User Name</label>
            <input type="text" name="userName" id="userName" aria-describedby="emailHelp" value="${user.userName}" class="form-control">
            <div id="userNameHelp" class="form-text">We'll never share your User Name.</div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" id="password" value="${user.password}" class="form-control"/>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe" value="Y">
            <label class="form-check-label" for="rememberMe">Remember Me</label>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
</form>
<p style="color: blue;">tom/tom001 or jerry/jerry001</p>
</body>
</html>
