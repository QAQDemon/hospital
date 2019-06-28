<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>"/>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <title>登录</title>
    <link id="iconLink" rel="icon" href="images/HISicon.ico" type="image/x-icon">
    <link id="shortIconLink" rel="shortcut icon" href="images/HISicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/loginStyles.css">
</head>
<body>
<div id="alertAllDiv"></div>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="container">
            <h1>欢迎使用HIS！</h1>
            <form class="form" id="loginForm" onkeypress="return event.keyCode !== 13;">
                <input type="text" placeholder="LoginName" id="loginName" name="loginName">
                <input type="password" placeholder="Password" id="password" name="password">
                <button type="button" id="submitButton">Login</button>
            </form>
        </div>
        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/myJquery/outpatientDoctorJs.js"></script>
<script type="text/javascript" src="js/myJquery/loginJs.js"></script>
</body>
</html>