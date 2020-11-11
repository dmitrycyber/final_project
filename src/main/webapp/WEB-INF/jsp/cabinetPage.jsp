<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="prop" var="local"/>
<fmt:message bundle="${local}" key="local.title.main" var="title"/>

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
        <%@include file="header.jsp"%>

        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <div class="card-title">${title}, ${user.name}!</div>
                <div class="info-block-adv">
                    Адрес ЖКХ: <br/>
                    Минск, ул. Ротмистрова д. 2, корпус 13 <br/>
                    <br/>
                    Телефоны ЖКХ:<br/>
                    8 029 640 44 77 <br/>
                    8 029 649 90 46 <br/>
                    8 029 649 00 21 <br/>
                    8 029 648 82 03 <br/>
                    8 029 008 56 20 (дежурный) <br/>
                    8 029 588 02 02<br/>
                    <br/>
                </div>
            </div>
        </section>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>