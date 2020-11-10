<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>
<jsp:useBean id="order" class="by.epamtc.utilities.entity.Order" scope="request"/>

<jsp:useBean id="employeeMap"
             type="java.util.Map<java.lang.String, java.util.List<by.epamtc.utilities.entity.UserProfile>>"
             scope="request"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.placeholder.auth.login" var="login"/>
<fmt:message bundle="${local}" key="local.handle.button" var="handleBtn"/>
<fmt:message bundle="${local}" key="local.title.create.note" var="title"/>
<fmt:message bundle="${local}" key="local.submit.button" var="submit"/>
<fmt:message bundle="${local}" key="local.error.registration" var="regError"/>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        $("#datepicker").datepicker();
        $("#datepicker1").datepicker();
    });
</script>


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
                    Информация о заявке: <br/>
                    Описание: ${order.description} <br/>
                    Количество: ${order.scaleValue} ${order.scaleUnit} <br/>
                    Заявитель: ${order.userName} ${order.userSurname} <br/>
                    Адрес: ул.${order.street} д.${order.house}/${order.building} кв.${order.flat} <br/>
                    Дата старта: ${order.startDate} <br/>
                    Дата окончания: ${order.endDate} <br/>

                    <c:set var="electricianKey" value="Электрик"/>
                    <c:set var="cleanerKey" value="Уборщик"/>
                    <c:set var="plumberKey" value="Сантехник"/>
                    <c:set var="foremanKey" value="Бригадир"/>
                    <c:set var="accountantKey" value="Бухгалтер"/>
                    <c:set var="systemAdminKey" value="Системный администратор"/>
                    <c:set var="dispatcherKey" value="Диспетчер"/>

                    <c:set var="electriciansList" value="${employeeMap[electricianKey]}"/>
                    <c:set var="cleanersList" value="${employeeMap[cleanerKey]}"/>
                    <c:set var="plumbersList" value="${employeeMap[plumberKey]}"/>
                    <c:set var="foremansList" value="${employeeMap[foremanKey]}"/>
                    <c:set var="accountantsList" value="${employeeMap[accountantKey]}"/>
                    <c:set var="systemAdminsList" value="${employeeMap[systemAdminKey]}"/>
                    <c:set var="dispatchersList" value="${employeeMap[dispatcherKey]}"/>


                    <form action="MainController" class="auth-form">
                        <input type="hidden" name="command" value="create_note">
                        <input type="hidden" name="orderId" value=${order.id}>
                        <input type="hidden" name="workTypeId" value=${order.workTypeId}>
                        <div class="parent-field">
                            Добавить сотрудников на выполение: <br/>
                        </div>


                        <div class="parent-field">
                            Электрики: <br/>
                            <select name="electricians" multiple="multiple">
                                <c:forEach items="${electriciansList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            Сантехники: <br/>
                            <select name="plumbers" multiple>
                                <c:forEach items="${plumbersList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            Бригадиры: <br/>
                            <select name="foremans" multiple>
                                <c:forEach items="${foremansList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            Бухгалтеры: <br/>
                            <select name="accountants" multiple>
                                <c:forEach items="${accountantsList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            Диспетчеры: <br/>
                            <select name="dispatchers" multiple>
                                <c:forEach items="${dispatchersList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            Системныe администраторы... <br/>
                            <select name="systemAdmins" multiple>
                                <c:forEach items="${systemAdminsList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            Уборщики... <br/>
                            <select name="cleaners" multiple>
                                <c:forEach items="${cleanersList}" var="empProfile">
                                    <option value="${empProfile.userId}">${empProfile.name} ${empProfile.surname}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="parent-field">
                            <input type="datetime-local" name="startDate" id="datepicker" placeholder="Старт работ">
                            <%--                            <p>Enter Date: <input type="text" id="datepicker"></p>--%>
                        </div>
                        <div class="parent-field">
                            <input type="datetime-local" name="endDate" id="datepicker1" placeholder="Окончание работ">
                        </div>
                        <div class="parent-field">
                            <input type="text" name="comment" placeholder="Комментарий">
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
                    <form action="MainController" class="auth-form">
                        <input type="hidden" name="command" value="create_note">
                        <input type="hidden" name="isDecline" value="true">
                        <input type="hidden" name="orderId" value=${order.id}>
                        <div class="parent-submit-btn">
                            <button type="submit" class="decline-btn">Отклонить</button>
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