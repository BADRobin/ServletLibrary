<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="messages">
    <fmt:message key="readers.title" var = "title"/>
    <fmt:message key="register.birthday" var = "birthday"/>
    <fmt:message key="register.firstname" var = "firstname"/>
    <fmt:message key="register.lastname" var = "lastname"/>
    <fmt:message key="register.middlename" var = "middlename"/>
    <fmt:message key="register.phone" var = "phone"/>
    <fmt:message key="register.email" var = "email"/>
    <fmt:message key="readers.more" var = "more"/>
    <fmt:message key="page" var = "page"/>
    <fmt:message key="readers.date" var = "regdate"/>
    <fmt:message key="order.user" var = "order_user"/>
    <fmt:message key="delete.user" var = "delete_user"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/styleuser.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<div id="content">
    <c:if test="${role.equals('admin')}">
        <table border=1 class="task" align=center>
            <caption>${title}</caption>
            <thead>
                <tr>
                    <th>${firstname}</th>
                    <th>${lastname}</th>
                    <th>${email}</th>
                    <th>${regdate}</th>
                    <th>${more}</th>
                    <th>${delete_user}</th>
                </tr>
            </thead>
            <c:forEach items="${readers}" var="reader">
                <tr>
                    <td>${reader.person.firstName}</td>
                    <td>${reader.person.lastName}</td>
                    <td>${reader.email}</td>
                    <td>${reader.registerDate}</td>
                    <td class="text-center"><a href="about-order?user_id=${reader.id}">${order_user}</a></td>
                    <td class="text-center"><a href="delete-profile?user_id=${reader.id}">${delete_user}</a></td>
                </tr>
            </c:forEach>
        </table>
        <div align="center">${page} ${currentPage} / ${noOfPages}</div>
    </c:if>
</div>
</BODY>