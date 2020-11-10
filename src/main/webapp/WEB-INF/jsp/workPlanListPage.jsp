<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>
<jsp:useBean id="noteList" type="java.util.List<by.epamtc.utilities.entity.Note>" scope="request"/>

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
                <div class="info-block-adv">
                    <table border="1">
                        <tr>
                            <th>ID</th>
                            <th>Адрес</th>
                            <th>Период</th>
                            <th>Имя заказчика</th>
                            <th>Телефон заказчика</th>
                            <th>Описание</th>
                            <th>Комментарий</th>
                            <th>Список сотрудников</th>

                        </tr>
                        <c:forEach items="${noteList}" var="note">
                            <tr>
                                <td>${note.id}</td>
                                <td>ул.${note.street} д.${note.house}/${note.building} кв.${note.flat}</td>
                                <td>${note.startDate} - <br/> ${note.endDate}</td>
                                <td>${note.name}</td>
                                <td>${note.phoneNumber}</td>
                                <td>${note.description}</td>
                                <td>${note.comment}</td>
                                <td>
                                    <c:forEach items="${note.employeeSurnames}" var="employee">
                                        ${employee} <br/>
                                    </c:forEach>
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