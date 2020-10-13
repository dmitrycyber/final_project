<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.home" var="home"/>
<fmt:message bundle="${local}" key="local.auth" var="auth"/>
<fmt:message bundle="${local}" key="local.register" var="register"/>
<fmt:message bundle="${local}" key="local.logout" var="logout"/>
<fmt:message bundle="${local}" key="local.profile" var="profile"/>
<fmt:message bundle="${local}" key="local.orders" var="orders"/>
<fmt:message bundle="${local}" key="local.work_planes" var="planes"/>
<fmt:message bundle="${local}" key="local.employees" var="employees"/>
<fmt:message bundle="${local}" key="local.brigades" var="brigades"/>

<!DOCTYPE html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

    <header class="wrapper-header">
        <div class="content-header">
            <form action="MainController">
                <input type="hidden" name="command" value="change_language">
                <input type="hidden" name="local" value="ru_RU">
                <input type="hidden" name="page" value="${pageContext.request.getParameter("command")}">
                <input class="button-language" type="submit" value="RU">
            </form>
            <form action="MainController">
                <input type="hidden" name="command" value="change_language">
                <input type="hidden" name="local" value="en_US">
                <input type="hidden" name="page" value="${pageContext.request.getParameter("command")}">
                <input class="button-language" type="submit" value="EN">
            </form>

            <c:if test="${sessionScope.user == null}">
                <c:if test="${param.command != 'go_to_main_page'}">
                    <a href="MainController?command=go_to_main_page" class="btn-link-auth">${home}</a>
                </c:if>
                <a href="MainController?command=go_to_auth_page" class="btn-link-auth">${auth}</a>
                <a href="MainController?command=go_to_registration_page" class="btn-link-auth">${register}</a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                ${sessionScope.user.login}
                <div class="box-links-main-page">
                    <a href="MainController?command=profile" class="btn-link-auth">${profile}</a>
                    <a href="#" class="btn-link-auth">${orders}</a>
                    <a href="#" class="btn-link-auth">${planes}</a>
                    <a href="#" class="btn-link-auth">${employees}</a>
                    <a href="#" class="btn-link-auth">${brigades}</a>
                    <a href="#" class="btn-link-auth">${logout}</a>
                </div>

            </c:if>
        </div>
    </header>