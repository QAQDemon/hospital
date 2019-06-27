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
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no">
    <title>登录</title>
    <link id="iconLink" rel="icon" href="images/HISicon.ico" type="image/x-icon">
    <link id="shortIconLink" rel="shortcut icon" href="images/HISicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body>
<div id="alertAllDiv"></div>
<form id="loginForm">
    <div class="form-group">
        <label class="custom-control-label" for="loginName">登录名：</label>
        <input type="text" class="form-control" id="loginName" name="loginName">
    </div>
    <div class="form-group">
        <label class="custom-control-label" for="password">密码：</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <button type="button" id="submitButton"> 提交</button>
</form>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/myJquery/outpatientDoctorJs.js"></script>
<script type="text/javascript" src="js/myJquery/loginJs.js"></script>
</body>
</html>