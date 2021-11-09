
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="create_author_url" value="/app/save-author"/>

<fmt:bundle basename="messages">
    <fmt:message key="register.firstname" var="first_name"/>
    <fmt:message key="register.lastname" var="last_name"/>
    <fmt:message key="register.middlename" var="middle_name"/>
    <fmt:message key="register.book.name" var="book_name"/>
    <fmt:message key="register.book.description" var="description"/>
    <fmt:message key="register.book.genre" var="book_genre"/>
    <fmt:message key="register.book.author" var="book_author"/>
    <fmt:message key="register.book.year" var="book_year"/>
    <fmt:message key="register.book.legend.about.book" var="legend_about_book"/>
    <fmt:message key="register.book.legend.about.author" var="legend_about_author"/>
    <fmt:message key="register.book.pholder.description" var="ph_about_book"/>
    <fmt:message key="register.book.pholder.isbn" var="ph_isbn"/>
    <fmt:message key="register.book.pholder.description" var="ph_descrip_book"/>
    <fmt:message key="register.book.pholder.year" var="ph_year_book"/>
    <fmt:message key="register.book.pholder.name" var="ph_name_book"/>
    <fmt:message key="register.book.button.save" var="button_save"/>
    <fmt:message key="register.error.data" var="error_data"/>
    <fmt:message key="register.book.legend.about.author" var="author_info"/>
    <fmt:message key="required.field" var="required_field"/>
    <fmt:message key="submit" var = "submit"/>
    <fmt:message key="regis.author.last" var = "surn"/>
    <fmt:message key="regis.author.middle" var = "fath_name"/>
    <fmt:message key="regis.author.name" var = "name"/>

    <fmt:message key="reg.first.name" var="err_fir_name"/>
    <fmt:message key="reg.middle.name" var="err_mid_name"/>
    <fmt:message key="reg.last.name" var="err_last_name"/>
</fmt:bundle>
<style>
    <jsp:directive.include file="/WEB-INF/style_author.css"/>
</style>

<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${role.equals('admin')}">
<form method="post" action="${create_author_url}">
    <div>
        <label>${first_name}
            <input type="text" name="first_name" placeholder="${name}"></label>
        <label class="ss"> *${required_field}</label>
    </div>
    <div>
        <label>${last_name}
            <input type="text" name="last_name" placeholder="${surn}"></label>
        <label class="ss">  *${required_field}</label>
    </div>
    <div>
        <label>${middle_name}
            <input type="text" name="middle_name" placeholder="${fath_name}"></label>
        <label class="ss">  *${required_field}</label>
    </div>
    <input type="submit">
</form>
</c:if>
<c:if test="${not empty middle_name_error}">

    <p>${err_last_name}  </p>
</c:if>
<c:if test="${not empty last_name_error}">
    <p>${err_mid_name}</p>
</c:if>
<c:if test="${not empty first_name_error}">
    <p>${err_fir_name}</p>
</c:if>
</body>
</html>
