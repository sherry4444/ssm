<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.min.js"/>'></script>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</head>
<body>
	<form action="/login" method="post">
		<table>
			<tr>
				<td>account:</td>
				<td><input id="username" name="username" type="text"></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input id="password" name="password" type="password"></td>
			</tr>
			<tr>
				<td>
					<button type="submit" id="login">Login</button>
				</td>
			</tr>
		</table>
	</form>

        <a href="/signup">注册</a>
</body>
</html>