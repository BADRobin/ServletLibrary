
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="add_comment_url" value="/app/comment-user"/>

<fmt:bundle basename="messages">
    <fmt:message key="register.firstname" var = "first"/>
    <fmt:message key="topic.topic" var = "topic"/>
    <fmt:message key="create.theme" var = "create"/>
    <fmt:message key="name.topic" var = "name_topic"/>
    <fmt:message key="write.comment" var = "write_comment"/>
    <fmt:message key="comment" var = "comment"/>
    <fmt:message key="tittle" var = "tittle22"/>
    <fmt:message key="submit" var = "submit"/>
    <fmt:message key="del.comm" var = "del_comm"/>
    <fmt:message key="date" var = "date_comm"/>
</fmt:bundle>
<style>
    <jsp:directive.include file="/WEB-INF/stylecomment.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>

<html>
<head>
    <title>${tittle22}</title>
</head>
<body>
<table  border=1 class="task" align=center>
    <tr>
        <th>${first}</th>
        <th>${comment}</th>
        <th>${date_comm}</th>
    </tr>
<c:forEach items="${comments}" var="comment">
    <tr>
        <td>${comment.user.person.firstName}</td>
        <td class="wid">${comment.message}</td>
        <td >${comment.date}</td>
        <c:if test="${role.equals('admin')}">
            <td><a href="delete-comment?comment_id=${comment.numbTopic}">${del_comm}</a></td>
        </c:if>
    </tr>
</c:forEach>
</table>

<form method="post" action="${add_comment_url}">
    <p><b>${write_comment}</b></p>
    <p><textarea name="opinion"></textarea></p>
    <input type="submit" value="${submit}">
</form>

</body>
</html>
