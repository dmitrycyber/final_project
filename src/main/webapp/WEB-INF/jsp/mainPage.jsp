<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <%@include file="header.jsp" %>


        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <div class="card-title">${title}</div>
                <div class="info-block-adv">
                    <%--                    <div class="parent-div">--%>
                    <%--                        РЕКЛАМА 1--%>
                    <%--                        <div class="card-style">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="parent-div">--%>
                    <%--                        РЕКЛАМА 2--%>
                    <%--                        <div class="card-style">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</div>--%>
                    <%--                    </div>--%>
                    <%--                    <div class="parent-div">--%>
                    <%--                        РЕКЛАМА 3--%>
                    <%--                        <div class="card-style">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.</div>--%>
                    <%--                    </div>--%>
                    ООО "ШАБАНЫ ЖКХ-СЕРВИС" презентовала на 11 Минском Международном инновационном форуме "ТОЧНЫЕ
                    ИЗМЕРЕНИЯ - ОСНОВА КАЧЕСТВА И БЕЗОПАСНОСТИ" новый профсоюз - "Всероссийский Профсоюз Метрологов".
                    ЖКХ-Сервис уже более 6 лет проводит поверку счетчиков холодной и горячей воды на месте установки без
                    снятия с использованием переносной установки ТЕСТ-ВС. Своим клиентам мы предлагаем высокое качество
                    обслуживания, низкие цены и скидки для льготных категорий граждан.
                    <br/>
                    <br/>
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
                    Участник рейтинга с:<br/>
                    02/04/2013<br/>

                </div>
            </div>
        </section>
    </div>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>