<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

<%@ taglib uri="http://127.0.0.1:8080/customTag" prefix="customTag" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

    <footer class="footer">
        <div class="wrapper-footer">
            <div class="footer-info">
                <div><customTag:footerMessage footerMessage="Ⓒ 2020 All Right Reserved"/></div>
            </div>
        </div>
    </footer>
