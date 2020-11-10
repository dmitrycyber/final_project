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
                    <c:if test="${user.role eq roleConsts.USER}">
                        <div class="parent-submit-btn">
                            <button type="submit" class="submit-btn">Создать заявку</button>
                        </div>
                    </c:if>
                </form>
                <div class="info-block-adv">
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Описание</th>
                            <th>Адрес</th>
                            <th>Количество</th>
                            <th>Период</th>
                            <th>Срочная</th>
                            <th>Заказчик</th>
                            <th>Статус</th>
                            <th>Комментарий</th>

                        </tr>
                        <c:forEach items="${orderList}" var="order">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.description}</td>
                                <td>ул.${order.street} д.${order.house}/${order.building} кв.${order.flat}</td>
                                <td>${order.scaleValue} ${order.scaleUnit}</td>
                                <td>${order.startDate} - <br/>${order.endDate}</td>
                                <td align="center">
                                    <c:if test="${order.several == true}">
                                        &#9745;
                                    </c:if>
                                </td>
                                <td>${order.userName} ${order.userSurname}</td>
                                <td>${order.status}</td>
                                <td>${order.dispatcherComment}</td>
                                <c:if test="${user.role eq roleConsts.DISPATCHER}">
                                <td>
                                    <form action="MainController">
                                        <input type="hidden" name="command" value="go_to_create_note_page">
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input class="button-language" type="submit" value="${handleBtn}">
                                    </form>
                                </td>
                                </c:if>
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