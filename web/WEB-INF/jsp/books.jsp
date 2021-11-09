<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="search_title_url" value="/app/search-title"/>
<c:url var="search_author_url" value="/app/search-author"/>
<c:url var="books_url" value="/app/books"/>

<style>
    <jsp:directive.include file="/WEB-INF/stylebook.css"/>
</style>

<fmt:bundle basename="messages">
    <fmt:message key="submit" var = "submit"/>
    <fmt:message key="tittle" var = "tittle22"/>
    <fmt:message key="genre" var = "genre22"/>
    <fmt:message key="author" var = "author22"/>
    <fmt:message key="description" var = "description22"/>
    <fmt:message key="amount" var = "amount22"/>
    <fmt:message key="action" var = "action22"/>
    <fmt:message key="order.invalid" var = "order_invalid22"/>
    <fmt:message key="enter.page" var = "enter_page22"/>
    <fmt:message key="search.book" var = "search_book22"/>
    <fmt:message key="search.author" var = "search_author22"/>
    <fmt:message key="del.book" var = "book_delete22"/>
    <fmt:message key="page" var = "page"/>
    <fmt:message key="add.basket" var = "add_basket22"/>
    <fmt:message key="register.book.pholder.name" var="ph_name_book"/>
    <fmt:message key="regis.author.last" var = "surn"/>
</fmt:bundle>

<BODY>
<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<div id="content">
<c:forEach items="${genres}" var="genre">
    <div style="text-align: center;">
        <a href="books?genre_id=${genre.id}">${genre.name}</a>
    </div>
</c:forEach>
    <br>
    <table border=1 class="tas" align=center>
        <caption>${books_genre}</caption>
        <thead>
            <tr>
                <th>${tittle22}</th>
                <th>${genre22}</th>
                <th>${author22}</th>
                <th>${description22}</th>
                <th>${amount22}</th>
                <th>${action22}</th>
            </tr>
        </thead>
        <c:forEach items="${books}" var="bookInfo">
            <tr>
                <td>${bookInfo.book.name}</td>
                <td>${bookInfo.book.genre.name}</td>
                <td>
                    <c:forEach items="${bookInfo.book.authorList}" var="auth">
                        ${auth.firstName} ${auth.lastName} |
                    </c:forEach>
                </td>
                <td>${bookInfo.book.description}</td>
                <td>${bookInfo.amount}</td>
                <c:if test="${role.equals('user')}">
                    <c:if test="${bookInfo.amount > 0}">
                        <td><a href="add-to-basket?id_book=${bookInfo.book.id}">${add_basket22}</a></td>
                    </c:if>
                    <c:if test="${bookInfo.amount <= 0}">
                        <td>${order_invalid22}</td>
                    </c:if>
                </c:if>

                <c:if test="${role.equals('admin')}">
                        <td><a href="remove-book?id_book=${bookInfo.book.id}">   ${book_delete22}  </a></td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <div align="center">
        ${page}
        ${currentPage} / ${noOfPages}
    </div>
    <form method="get" action="${books_url}" class="shirt">
        ${enter_page22}
        <input type="text" class="wid" placeholder="1" name="page">
        <input type="submit" value="${submit}">
    </form>
</div>

 <div class="block1">
<div>
    <form action="${search_title_url}" method="post">
        <div class="row">  <p> ${search_book22}   </p><input type="text" name="searcher" placeholder="${ph_name_book}"> </div>
          <input type="submit" value="${submit}">
    </form>
</div>

<div>
    <form action="${search_author_url}" method="post">
        <div class="row">  <p> ${search_author22}  </p><input type="text" name="searcher" placeholder="${surn}"></div>
        <input type="submit" value="${submit}">
    </form>
</div>
 </div>


<table>
<c:if test="${not empty findBooks}">
    <c:forEach items="${findBooks}" var="book">
        <tr>
            <td>${book.name}</td>
        </tr>
    </c:forEach>
</c:if>
</table>
</BODY>