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
      <tr><th>等级</th><th>玩家经验值</th><th>战斗经验值</th></tr>
      <c:forEach items="${info}" var="cdLv">
	<tr>
	  <td><c:out value="${cdLv.player_lv}"/></td>
	  <td><c:out value="${cdLv.player_exp}"/></td>
	  <td><c:out value="${cdLv.bat_exp}"/></td>
	</tr>
      </c:forEach>
    </table>
  </body>
</html>
