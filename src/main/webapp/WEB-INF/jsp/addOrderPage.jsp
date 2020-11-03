<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>

<jsp:useBean id="units" type="java.util.List<by.epamtc.utilities.entity.Unit>" scope="request"/>
<jsp:useBean id="workTypes" type="java.util.List<by.epamtc.utilities.entity.WorkType>" scope="request"/>


<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.placeholder.auth.login" var="login"/>
<fmt:message bundle="${local}" key="local.handle.button" var="handleBtn"/>
<fmt:message bundle="${local}" key="local.title.create.order" var="title"/>
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
                    <form action="MainController" class="auth-form">
                        <input type="hidden" name="command" value="create_order">
                        <div class="parent-field">
                            Тип работ:
                            <select name="workTypeId">
                                <c:forEach items="${workTypes}" var="type">
                                    <option value="${type.id}">${type.workType}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="parent-field">
                            <input type="text" name="scaleValue" placeholder="Количество">
                        </div>
                        <div class="parent-field">
                            Единицы:
                            <select name="scaleUnitsId">
                                <c:forEach items="${units}" var="type">
                                    <option value="${type.id}">${type.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="parent-field">
<%--                            <label for="datepicker"></label>--%>
                            <input type="datetime-local" name="startDate" id="datepicker" placeholder="Старт работ">
                        </div>
                        <div class="parent-field">
<%--                            <label for="datepicker1"></label>--%>
                            <input type="datetime-local" name="endDate" id="datepicker1" placeholder="Окончание работ">
                        </div>
<%--                        <div class="parent-field">--%>
<%--                            Не срочная--%>
<%--                            <input type="radio" name="isSeveral" value="false">--%>
<%--                        </div>--%>
                        <div class="parent-field">
                            <input type="text" name="description" placeholder="Описание">
                        </div>
                        <div class="parent-field">
                            Срочная<br/>
                            <input type="checkbox" name="isSeveral" value="true">
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