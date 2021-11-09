
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="create_book_url" value="/app/create-book"/>

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
    <fmt:message key="reg.first.name" var="err_fir_name"/>
    <fmt:message key="reg.middle.name" var="err_mid_name"/>
    <fmt:message key="reg.last.name" var="err_last_name"/>
    <fmt:message key="reg.isbn.name" var="err_isbn_book"/>
    <fmt:message key="reg.descr.name" var="err_desc_book"/>
    <fmt:message key="reg.book.name" var="err_book_book"/>
    <fmt:message key="reg.year.name" var="err_year_book"/>
    <fmt:message key="reg.amount.name" var="err_amount_book"/>
    <fmt:message key="register.book.legend.about.author" var="author_info"/>
    <fmt:message key="register.book.amount" var="amount"/>
    <fmt:message key="submit" var = "submit"/>
    <fmt:message key="add.author" var = "add_author"/>
    <fmt:message key="new.author" var = "new_author"/>
    <fmt:message key="regis.author.last" var = "surn"/>
    <fmt:message key="regis.author.middle" var = "fath_name"/>
    <fmt:message key="regis.author.name" var = "name"/>
<fmt:message key="register.error.data" var="error_data"/>
<fmt:message key="reg.first.name" var="err_fir_name"/>
<fmt:message key="reg.middle.name" var="err_mid_name"/>
<fmt:message key="reg.last.name" var="err_last_name"/>
<fmt:message key="reg.isbn.name" var="err_isbn_book"/>
<fmt:message key="reg.descr.name" var="err_desc_book"/>
<fmt:message key="reg.book.name" var="err_book_book"/>
<fmt:message key="reg.year.name" var="err_year_book"/>
<fmt:message key="reg.amount.name" var="err_amount_book"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style_new_book.css"/>
</style>
<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${role.equals('admin')}">
<form action="${create_book_url}" method="post">
    <legend>${legend_about_book}</legend>
    <div>
        <label>${book_name}
            <input type="text" name="book_name" placeholder="${ph_name_book}">
        </label>
    </div>
    <div>
        <label>ISBN
            <input type="text" name="isbn" placeholder="${ph_isbn}"></label>

    </div>
    <div>
        <label>${book_year}
            <input type="text" name="year" placeholder="${ph_year_book}"></label>
    </div>
    <div>
        <label>${book_genre}
            <select name="genre_name">
                <c:forEach items="${genres}" var="genres">
                    <option value="${genres.id}">${genres.name}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div>
        <label>${description}
            <textarea placeholder="${ph_descrip_book}" name="description" rows="4"></textarea></label>

    </div>
    <div>
        <label>${amount}
            <input type="text" name="amount" placeholder="10"></label>

    </div>
    <div>
    ${book_author}
    <select name="author1">
        <option selected disabled> </option>
        <c:forEach items="${authors}" var="author">

            <option value="${author.id}">${author.lastName} ${author.firstName} ${author.middleName}</option>
        </c:forEach>
    </select>
    </div>


        <details>  <summary>${new_author}</summary>
    <select name="author2">
        <option selected disabled> </option>
        <c:forEach items="${authors}" var="author">
            <option value="${author.id}">${author.lastName} ${author.firstName} ${author.middleName}</option>
        </c:forEach>
    </select>
        </details>
    <details>  <summary>${new_author}</summary>
    <div>
    <select name="author3"><label>
        <option selected disabled> </option>
        <c:forEach items="${authors}" var="author">

            <option value="${author.id}">${author.lastName} ${author.firstName} ${author.middleName}</option>
        </c:forEach> </label>
    </select>

    </div>
    </details>

    <input type="submit"  value="${submit}">

</form>

</c:if>
<div>
<a href="create-author">${new_author}</a>
    <div>


<c:if test="${not empty year_error}">
    <p>${err_year_book}</p>
</c:if>

<c:if test="${not empty middle_name_error}">
    <p>${err_mid_name}</p>
</c:if>

<c:if test="${not empty last_name_error}">
    <p>${err_last_name}</p>
</c:if>

<c:if test="${not empty first_name_error}">
    <p>${err_fir_name}</p>
</c:if>

<c:if test="${not empty amount_error}">
    <p>${err_amount_book}</p>
</c:if>

<c:if test="${not empty description_error}">
    <p>${err_desc_book}</p>
</c:if>

<c:if test="${not empty book_name_error}">
    <p>${err_book_book}</p>
</c:if>

<c:if test="${not empty isbn_error}">
    <p>${err_isbn_book}</p>
</c:if>
</body>
</html>
