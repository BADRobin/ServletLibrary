<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:url var="books_url" value="/app/books"/>
<c:url var="readers_url" value="/app/readers"/>
<c:url var="addBook_url" value="/app/add-book"/>
<c:url var="logout_url" value="/app/logout"/>
<c:url var="home_url" value="/app/welcome"/>
<c:url var="order_user_url" value="/app/order-user"/>
<c:url var="basket_url" value="/app/basket"/>

<c:url var="selectLanguageEn_url" value="/app/select-language?lang=en"/>
<c:url var="selectLanguageRu_url" value="/app/select-language?lang=ru"/>

<html lang=en>
<meta charset="UTF-8">
<fmt:bundle basename="messages">
    <fmt:message key="title" var="global_title"/>
    <fmt:message key="navbar.books" var="navBooks"/>
    <fmt:message key="navbar.readers" var="navReaders"/>
    <fmt:message key="navbar.addBook" var="addBook"/>
    <fmt:message key="navbar.logout" var="logout"/>
    <fmt:message key="navbar.basket" var="basket"/>
    <fmt:message key="authentication.signin" var="sign_in"/>
    <fmt:message key="topic" var = "topic"/>
    <fmt:message key="order.user" var = "order_user"/>
</fmt:bundle>
<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>
<BODY background="../images/fon.jpg">
<div id="header">${global_title}</div>
<div id="navbar">
    <ul>
        <c:choose>
            <c:when test="${role.equals('user')}">
                <li><a href=${books_url}>${navBooks}</a></li>
                <li><a href=${order_user_url}>${order_user}</a></li>
                <li><a href=${logout_url}>${logout}</a></li>
                <li>
                    <a href=${basket_url}>${basket}
                        <c:if test="${not empty basket_size}">
                            (${basket_size})
                        </c:if>
                    </a>
                </li>
                <li><a href=${selectLanguageEn_url}><img src="../images/en.png" width="25" height="25"></a></li>
                <li><a href=${selectLanguageRu_url}><img src="../images/ru.png" width="25" height="25"></a></li>
            </c:when>
            <c:when test="${role.equals('admin')}">
                <li><a href=${books_url}>${navBooks}</a></li>
                <li><a href=${readers_url}>${navReaders}</a></li>
                <li><a href=${addBook_url}>${addBook}</a></li>
                <li><a href=${logout_url}>${logout}</a></li>
                <li>
                    <a href=${selectLanguageEn_url}><img src="../images/en.png" width="25" height="25"></a>
                </li>
                <li><a href=${selectLanguageRu_url}><img src="../images/ru.png" width="25" height="25"></a></li>
            </c:when>
            <c:otherwise>
                <li><a href=${home_url}>${sign_in}</a></li>
                <li><a href=${books_url}>${navBooks}</a></li>
                <li><a href=${selectLanguageEn_url}><img src="../images/en.png" width="25" height="25"></a></li>
                <li><a href=${selectLanguageRu_url}><img src="../images/ru.png" width="25" height="25"></a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>




