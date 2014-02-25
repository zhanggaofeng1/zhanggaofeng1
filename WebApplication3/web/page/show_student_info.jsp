<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>build progect</title>
  </head>

  <body>
    <table align="center">
      <tr><th>数据库id</th><th>编号</th><th>名字</th><th>TEL</th></tr>
      <c:forEach items="${stus}" var="stu">
	<tr>
	  <td><c:out value="${stu.id}"/></td>
	  <td><c:out value="${stu.stuId}"/></td>
	  <td><c:out value="${stu.stuName}"/></td>
	  <td><c:out value="${stu.stuTel}"/></td>
	</tr>
      </c:forEach>
    </table>
  </body>
</html>
