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
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css?v=1.2">

</head>
<body>
<div class="wrapper-box">
    <div class="wrapper-content">
        <header class="wrapper-header">
            <div class="content-header">
                <div class="box-links-main-page">
                    <div class="btn-link-auth">
                        ${user.login}
                    </div>
                    <a href="#"
                       class="btn-link-auth">Выйти</a>
                </div>
            </div>
        </header>
        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <div class="card-title">Добро пожаловать, ${user.login}!</div>
                <div class="info-block-adv">
                    <div class="parent-div">
                        <div class="card-style">Предложения для пользователя.. TODO</div>
                    </div>
                    <div class="parent-div">
                        <div class="card-style">Предложения для пользователя.. TODO</div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <footer class="footer">
        <div class="wrapper-footer">
            <div class="footer-info">
                <div>Ⓒ 2020 All Right Reserved</div>
            </div>
        </div>
    </footer>
</div>
</body>
</html>