<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.error.auth" var="authError"/>
<fmt:message bundle="${local}" key="local.title.auth" var="title"/>
<fmt:message bundle="${local}" key="local.placeholder.auth.login" var="placeholderLogin"/>
<fmt:message bundle="${local}" key="local.placeholder.auth.password" var="placeholderPassword"/>
<fmt:message bundle="${local}" key="local.submit.button" var="submit"/>



<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper-box">
    <div class="wrapper-content">
        <%@include file="header.jsp" %>

        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <div class="card-title"> ${title} </div>
                <div class="info-block-adv">
                    <form action="MainController" class="auth-form">
                    <input type="hidden" name="command" value="auth">
                        <div class="parent-field">
                            <input type="text" name="login" placeholder=${placeholderLogin}>
                        </div>
                        <div class="parent-field">
                            <input type="password" name="password" placeholder=${placeholderPassword} required>
                        </div>
                        <c:if test="${sessionScope.invalidData == true}">
                            <div>
                                <p class="error-message">${authError}</p>
                            </div>
                        </c:if>
                        <div class="parent-submit-btn">
                            <button type="submit" class="submit-btn">${submit}</button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>