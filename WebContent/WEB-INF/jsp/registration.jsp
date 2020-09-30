<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css?v=1.2">
</head>
<body>
<div class="wrapper-box">
    <div class="wrapper-content">
        <header class="wrapper-header">
            <div class="content-header">
                <div class="box-links-main-page">
                    <a href="MainController?command=go_to_main_page" class="btn-link-auth">На главную</a>
                    <a href="MainController?command=go_to_auth_page" class="btn-link-auth">Авторизация</a>
                    <a href="MainController?command=go_to_registration_page" class="btn-link-auth">Регистрация</a>
                </div>
            </div>
        </header>

        <section class="welcome-section">
            <div class="wrapper-welcome-section">
                <div class="card-title"> Регистрация </div>
                <div class="info-block-adv">
                    <form class="auth-form">
                        <div class="parent-field">
                            <input type="text" placeholder="Ваше имя">
                        </div>
                        <div class="parent-field">
                            <input type="text" placeholder="Ваша фамилия">
                        </div>
                        <div class="parent-field">
                            <input type="email" placeholder="Ваш email">
                        </div>
                        <div class="parent-field">
                            <input type="password" placeholder="Пароль" required>
                        </div>
                        <div class="parent-submit-btn">
                            <button type="submit" class="submit-btn">Подтвердить</button>
                        </div>
                    </form>
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