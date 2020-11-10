<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>
<jsp:useBean id="employeesList" type="java.util.List<by.epamtc.utilities.entity.UserProfile>" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.placeholder.auth.login" var="login"/>
<fmt:message bundle="${local}" key="local.edit.button" var="editBtn"/>
<fmt:message bundle="${local}" key="local.title.employeeList" var="title"/>


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
    <div class="wrapper-content">
        <%@include file="header.jsp" %>


        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <div class="card-title">${title}</div>
                <form action="MainController" class="auth-form">
                    <input type="hidden" name="command" value="go_to_create_employee">
                    <c:if test="${user.role eq roleConsts.ADMIN}">
                        <div class="parent-submit-btn">
                            <button type="submit" class="submit-btn">Создать сотрудника</button>
                        </div>
                    </c:if>
                </form>
                <div class="info-block-adv">
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Имя</th>
                            <th>Фамилия</th>
                            <th>Логин</th>
                            <th>Телефон</th>
                            <th>Дата нанятия</th>

                        </tr>
                        <c:forEach items="${employeesList}" var="employee">
                            <tr>
                                <td>${employee.userId}</td>
                                <td>${employee.name}</td>
                                <td>${employee.surname}</td>
                                <td>${employee.login}</td>
                                <td>${employee.phoneNumber}</td>
                                <td>${employee.hiringDate}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </section>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>