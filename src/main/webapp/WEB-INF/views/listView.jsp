<%--
  Created by IntelliJ IDEA.
  User: yohan
  Date: 2021/12/07
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:forEach var="member" items="${members}">
        <li>${member.id}</li>
        <li>${member.username}</li>
        <li>${member.age}</li>
    </c:forEach>



</head>
<body>

</body>
</html>
