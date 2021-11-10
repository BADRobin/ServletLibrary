<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="register_url" value="/app/register"/>
<c:url var="login_url" value="/app/login"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="messages">
    <fmt:message key="authentication.signin" var="sign_in"/>
    <fmt:message key="authentication.login" var="login"/>
    <fmt:message key="register.button.register" var="registration"/>
    <fmt:message key="register.email" var="email"/>
    <fmt:message key="register.password" var="password"/>

    <fmt:message key="login.error" var="login_error"/>
    <fmt:message key="login.error" var="log"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>
<body>
<c:choose>
    <c:when test="${role.equals('user')}">
        <c:redirect url="/app/main"/>
    </c:when>
    <c:when test="${role.equals('admin')}">
        <c:redirect url="/app/admin"/>
    </c:when>
    <c:otherwise>
        <jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>

        <div id="content">
            <form action="${login_url}" method="POST">
                <fieldset>
                    <legend align="center">${sign_in}</legend>
                    <div align="center">
                        <label>${email}</label>
                        <input type="text" name="login">
                        <label>${password}</label>
                        <input type="password" name="password">
                    </div>
                    <div align="center">
                        <input type="submit" value="${login}"/>
                    </div>
                </fieldset>
            </form>
            <form action="${register_url}" method="get">
                <div align="center"><input type="submit" value=${registration} /></div>
            </form>
        </div>
    </c:otherwise>

</c:choose>


<c:if test="${not empty not_auth}">
   ${log}
</c:if>
</body>
