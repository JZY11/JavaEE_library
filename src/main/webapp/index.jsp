<%--
  Created by IntelliJ IDEA.
  User: Tony.Jaa
  Date: 2017/6/16
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
  <head>
    <title>主页</title>
      <style>
          @import "css/style.css";
      </style>
  </head>
  <body>
  <c:if test="${sessionScope.role ne '用户'}">
      <c:redirect url="default.jsp"/>
  </c:if>
  <h1>主页</h1>
  ${requestScope.username}
  <p><a href="user?action=logout">注销</a></p>
  <hr>

  </body>
</html>
