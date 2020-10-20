<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.error.registration" var="regError"/>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="wrapper-box">
    <%@include file="header.jsp" %>
    <div class="wrapper-content">
        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <%--                name: ${requestScope.userProfile.name} </br>--%>
                <%--                surname: ${requestScope.userProfile.surname} </br>--%>
                <%--                login: ${requestScope.userProfile.login} </br>--%>
                <%--                phoneNumber: ${requestScope.userProfile.phoneNumber} </br>--%>

                <%--                address: ул. ${requestScope.userProfile.street}, д.${requestScope.userProfile.house}--%>
                <%--                <c:if test="${requestScope.userProfile.building != null}">--%>
                <%--                    /${requestScope.userProfile.building}--%>
                <%--                </c:if>--%>
                <%--                кв. ${requestScope.userProfile.flat} </br>--%>
                <form action="MainController" class="profile-form">
                    <input type="hidden" name="command" value="change_profile">
                    <input type="hidden" name="userId" value="${user.id}">
                    <div>
                        <p class="field-name">Имя</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="name" value="${requestScope.userProfile.name}">
                    </div>

                    <div>
                        <p class="field-name">Фамилия</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="surname" value="${requestScope.userProfile.surname}" required>
                    </div>

                    <div>
                        <p class="field-name">Логин</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="login" value="${requestScope.userProfile.login}" required>
                    </div>

                    <div>
                        <p class="field-name">Телефон</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="phoneNumber" value="${requestScope.userProfile.phoneNumber}" required>
                    </div>

                    <div>
                        <p class="field-name">Улица</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="street" value="${requestScope.userProfile.street}" required>
                    </div>

                    <div>
                        <p class="field-name">Дом</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="house" value="${requestScope.userProfile.house}" required>
                    </div>

                    <div>
                        <p class="field-name">Корпус</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="building" value="${requestScope.userProfile.building}" required>
                    </div>

                    <div>
                        <p class="field-name">Квартира</p>
                    </div>
                    <div class="parent-field">
                        <input type="text" name="flat" value="${requestScope.userProfile.flat}" required>
                    </div>

                    <c:if test="${sessionScope.userExist == true}">
                        <div>
                            <p class="error-message">${regError}!</p>
                        </div>
                    </c:if>


                    <div class="parent-submit-btn">
                        <button type="submit" class="submit-btn">Редактировать</button>
                    </div>
                </form>
            </div>
        </section>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>