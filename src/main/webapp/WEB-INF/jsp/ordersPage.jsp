<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>

<jsp:useBean id="orderList" type="java.util.List<by.epamtc.utilities.entity.Order>" scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.placeholder.auth.login" var="login"/>
<fmt:message bundle="${local}" key="local.handle.button" var="handleBtn"/>
<fmt:message bundle="${local}" key="local.title.orders" var="title"/>


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
                    <input type="hidden" name="command" value="go_to_create_order_page">
                    <div class="parent-submit-btn">
                        <button type="submit" class="submit-btn">СОЗДАТЬ ЗАКАЗ</button>
                    </div>
                </form>
                <div class="info-block-adv">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Описание</th>
                            <th>Адрес</th>
                            <th>Количество</th>
                            <th>Дата старта</th>
                            <th>Дата окончания</th>
                            <th>Срочность</th>
                            <th>Заказчик</th>
                            <th>Статус</th>

                        </tr>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.description}</td>
                                <td>ул.${order.street} д.${order.house}/${order.building} кв.${order.flat}</td>
                                <td>${order.scaleValue} ${order.scaleUnit}</td>
                                <td>${order.startDate}</td>
                                <td>${order.endDate}</td>
                                <td>${order.several}</td>
                                <td>${order.userName} ${order.userSurname}</td>
                                <td>${order.status}</td>
                                <td>
                                    <form action="MainController">
                                        <input type="hidden" name="command" value="profile">
                                        <input type="hidden" name="order" value="${order}">
                                        <input class="button-language" type="submit" value="${handleBtn}">
                                    </form>
                                </td>
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