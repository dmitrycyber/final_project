<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.error.registration" var="regError"/>
<fmt:message bundle="${local}" key="local.title.registration" var="title"/>
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
                <div class="card-title"> ${title}</div>
                <div class="info-block-adv">
                    <form action="MainController" class="auth-form">
                        <input type="hidden" name="command" value="registration">
                        <div class="parent-field">
                            <input type="text" name="name" placeholder="Ваше имя" pattern="^[A-zА-я]{1,20}$" title="только латиница или кириллица от 1 до 20 символов" required>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="surname" placeholder="Ваша фамилия" pattern="^[A-zА-я]{1,20}$" title="только латиница или кириллица от 1 до 20 символов" required>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="login" placeholder="Ваш логин" required>
                        </div>
                        <div class="parent-field">
                            <input type="password" name="password" placeholder="Пароль" required>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="phoneNumber" placeholder="Телефон" pattern="^\+375(29|33|44)\d{7}$" title="+375291111111" required>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="street" placeholder="Улица" required>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="house" placeholder="Дом" pattern="^\d{1,5}$" title="от 1 до 5 цифр" required>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="building" placeholder="Корпус" pattern="^\d{1,5}$" title="от 1 до 5 цифр">
                        </div>
                        <div class="parent-field">
                            <input type="text" name="flat" placeholder="Квартира" pattern="^\d{1,5}$" title="от 1 до 5 цифр" required>
                        </div>


                        <c:if test="${sessionScope.userExist == true}">
                            <div>
                                <p class="error-message">${regError}!</p>
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