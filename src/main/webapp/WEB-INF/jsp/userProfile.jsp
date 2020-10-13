<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="by.epamtc.utilities.entity.User" scope="session"/>

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
        <%@include file="header.jsp"%>
    <div class="wrapper-content">
        name: ${requestScope.userProfile.name} </br>
        surname: ${requestScope.userProfile.surname} </br>
        login: ${requestScope.userProfile.login} </br>
        phoneNumber: ${requestScope.userProfile.phoneNumber} </br>

        address: ул. ${requestScope.userProfile.street}, д.${requestScope.userProfile.house}
        <c:if test="${requestScope.userProfile.building != null}">
            /${requestScope.userProfile.building}
        </c:if>
         кв. ${requestScope.userProfile.flat} </br>

    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>