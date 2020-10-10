<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="card-title">Добро пожаловать!</div>
                <div class="info-block-adv">
                    <div class="parent-div">
                        Блок с рекламой и данными
                        <div class="card-style">ДИВ БЛОЧНЫЙ</div>
                        <div class="card-style">ДИВ БЛОЧНЫЙ</div>
                    </div>
                    <div class="parent-div">
                        Блок с рекламой и данными
                        <div class="card-style">ДИВ БЛОЧНЫЙ</div>
                        <div class="card-style">ДИВ БЛОЧНЫЙ</div>
                    </div>
                </div>
            </div>
        </section>
    </div>

	<%@include file="footer.jsp" %>
</div>
</body>
</html>