<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Registration</title>
<meta charset="utf-8">
</head>
<body>
<h2>Пожалуйста зарегистрируйтесь:</h2>
<form name="loginForm" method="POST" action="registration">

Логин:<br/>
<input type="text" name="login" value=""/>
<br/>Пароль:<br/>
<input type="password" name="password" value=""/>
<br/>

<input type="submit" value="Регистрация"/>
</form><hr/>
${errorLoginPassMessage}
</body></html>
