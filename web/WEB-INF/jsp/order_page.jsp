
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="messages">
    <fmt:message key="order.user" var = "order_user"/>
    <fmt:message key="numb.order" var = "number_of_order"/>
    <fmt:message key="status" var = "status"/>
    <fmt:message key="book.order" var = "books_of_order"/>
    <fmt:message key="take.book" var = "take_book"/>
    <fmt:message key="return.book" var = "return_book"/>
    <fmt:message key="delete.order" var = "delete_order"/>
</fmt:bundle>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    <jsp:directive.include file="/WEB-INF/styleorder.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<c:if test="${role.equals('user')}">
<table border=1 class="task" align=center>
<caption>${order_user}</caption>
<thead>
<tr>
    <th>${number_of_order}</th>
    <th>${status}</th>
    <th>${books_of_order}</th>
</tr>
</thead>

<c:forEach items="${orders}" var="order">
    <tr>
    <td>${order.id}</td>
    <td>${order.status}</td>
    <td>
    <c:forEach items="${order.books}" var="book">
        <div>${book.name}</div>
    </c:forEach>
    </td>
        </c:forEach>
    </tr>
</table>
</c:if>
</body>
</html>
