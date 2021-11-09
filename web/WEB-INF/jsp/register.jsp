<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="register_url" value="/app/register"/>
<c:url var="home_url" value="/app/welcome"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="messages">
    <fmt:message key="register.title" var = "title"/>
    <fmt:message key="register.birthday" var = "birthday"/>
    <fmt:message key="register.firstname" var = "firstname"/>
    <fmt:message key="register.lastname" var = "lastname"/>
    <fmt:message key="register.middlename" var = "middlename"/>
    <fmt:message key="register.phone" var = "phone"/>
    <fmt:message key="register.password" var = "password"/>
    <fmt:message key="register.email" var = "email"/>
    <fmt:message key="register.confirm.password" var = "confirm_password"/>
    <fmt:message key="register.pholder.firstname" var = "pholder_firstname"/>
    <fmt:message key="register.pholder.lastname" var = "pholder_lastname"/>
    <fmt:message key="register.pholder.middlename" var = "pholder_middlename"/>
    <fmt:message key="register.pholder.phone" var = "pholder_phone"/>
    <fmt:message key="register.pholder.email" var = "pholder_email"/>
    <fmt:message key="register.pholder.birthday" var = "pholder_birthday"/>
    <fmt:message key="register.button.register" var = "button_register"/>
    <fmt:message key="register.button.home" var = "home"/>
    <fmt:message key="register.passwordlen" var = "passwordlen"/>

    <fmt:message key="reg.email.name" var = "err_email_exist"/>
    <fmt:message key="reg.email.correct" var = "err_email"/>
    <fmt:message key="reg.password.confirm" var = "sec_pass_err"/>
    <fmt:message key="reg.password.first" var = "password_first"/>
    <fmt:message key="reg.error.name" var = "error_name"/>
    <fmt:message key="reg.err.last.name" var = "err_last_name"/>
    <fmt:message key="reg.err.middle.name" var = "err_middle_name"/>
    <fmt:message key="reg.err.phone" var = "err_phone"/>
    <fmt:message key="reg.err.birthday" var = "err_birthday"/>
    <fmt:message key="req.field" var="req"/>
</fmt:bundle>

<style>
    <jsp:directive.include file="/WEB-INF/style.css"/>
</style>

<jsp:directive.include file="/WEB-INF/jsp/navbar.jsp"/>
<div id="content">

    <form action="${register_url}" method="POST">
        <fieldset>
            <legend id="header">${title}</legend>
            <legend>${req}</legend>
            <div>
                <label>${firstname}</label>
                <input name="first_name" type="text" placeholder="${pholder_firstname}">

            </div>
            <div>
                <label>${lastname}</label>
                <input name="last_name" type="text" placeholder="${pholder_lastname}">
            </div>
            <div>
                <label>${middlename}</label>
                <input name="middle_name" type="text" placeholder="${pholder_middlename}">
            </div>
            <div>
                <label>${birthday}</label>
                <input name="birthday" type="text" placeholder="${pholder_birthday}">
            </div>
            <div>
                <label>${phone}</label>
                <input name="phone" type="text" placeholder="${pholder_phone}" >
            </div>
            <div>
                <label>${email}</label>
                <input type="text" name="email" placeholder="${pholder_email}" >
            </div>
            <div>
                <label>${password}</label>
                <input type="password" name="password">
                ${passwordlen}
            </div>
            <div>
                <label>${confirm_password}</label>
                <input type="password" name="password_confirm">
            </div>
            <div>
                <button id="submit" name="submit">${button_register}</button>
            </div>
            <div>
                <a href="${home_url}" role="button">${home}</a>
            </div>
        </fieldset>
    </form>

    <c:if test="${not empty email_exist}">
        <p> ${err_email_exist}</p>
    </c:if>
    <c:if test="${not empty email_error}">
        <p> ${err_email}</p>
    </c:if>
    <c:if test="${not empty password_not_mach}">
        <p> ${sec_pass_err} </p>
    </c:if>
    <c:if test="${not empty password_error}">
        <p> ${password_first} </p>
    </c:if>
    <c:if test="${not empty first_name_error}">
        <p> ${error_name}</p>
    </c:if>
    <c:if test="${not empty last_name_error}">
        <p> ${err_last_name} </p>
    </c:if>
    <c:if test="${not empty middle_name_error}">
        <p> ${err_middle_name}</p>
    </c:if>
    <c:if test="${not empty phone_error}">
        <p> ${err_phone}</p>
    </c:if>
    <c:if test="${not empty birthday_error}">
        <p> ${err_birthday} </p>
    </c:if>

</div>
</BODY>
