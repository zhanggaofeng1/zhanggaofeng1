<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to Spring Web MVC project</title>
  </head>

  <body>
    <table>
      <tr><th>ID</th><th>StuID</th><th>StuName</th><th>StuTel</th><th>TeaId</th></tr>
      <c:forEach items="${info}" var="stu">
	<tr><td><c:out value="${stu.id}"/></td><td><c:out value="${stu.stuId}"/></td><td><c:out value="${stu.stuName}"/></td><td><c:out value="${stu.stuTel}"/></td><td><c:out value="${stu.teaId}"/></td></tr>
      </c:forEach>
    </table>
  </body>
</html>
