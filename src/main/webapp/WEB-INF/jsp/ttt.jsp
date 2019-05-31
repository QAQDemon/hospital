﻿<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>挂号</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
</head>
<body style="margin-top: 50px">
<div class="container">
    <div class="row">
        <div class="col-md-2" style="border-right: 2px solid;">
            <div class="row">
                <form class="" id="searchPatientForm" role="form">
                    <label for="searchPatientKey">患者查询：</label>
                    <input type="text" class="form-control small" id="searchPatientKey" name="searchPatientKey"
                           placeholder="名字、病历号或身份证"/>
                </form>
            </div>
            <div class="row" >
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">本人</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">科室</a>
                    </li>
                </ul>
            </div>
            <div class="row" ><form class="" role="form">
                <table class="table table-condensed table-striped table-hover table-sm">
                    <thead>
                    <tr>
                        <th colspan="3">待诊患者<span class="badge badge-pill badge-danger">4</span></th>
                        <th style="text-align:right;">
                            <img src="../../static/images/reflush_img.jpg" style="height: 15px;width: 15px">
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td style="width: 15px">
                            <input type="radio" id="customRadio" name="example1" />
                        </td>
                        <td>00001</td>
                        <td>哒哒哒</td>
                        <td>35岁</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" id="customRadio1" name="example1" />
                        </td>
                        <td for="customRadio1">00001</td>
                        <td for="customRadio1">哒哒哒</td>
                        <td for="customRadio1">35岁</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" id="customRadio2" name="example1" />
                        </td>
                        <td>00001</td>
                        <td>哒哒哒</td>
                        <td>35岁</td>
                    </tr>
                    </tbody>
                </table>
                <table class="table table-condensed table-striped table-hover">
                    <thead>
                    <tr>
                        <th colspan="3">已诊患者<span class="badge badge-pill badge-success">3</span></th>
                        <th style="text-align:right;">
                            <img src="../../static/images/reflush_img.jpg" style="height: 15px;width: 15px">
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="radio" id="customRadio3" name="example1" />
                        </td>
                        <td>00001</td>
                        <td>哒哒哒</td>
                        <td>35岁</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" id="customRadio4" name="example1" />
                        </td>
                        <td for="customRadio1">00001</td>
                        <td for="customRadio1">哒哒哒</td>
                        <td for="customRadio1">35岁</td>
                    </tr>
                    <tr>
                        <td>
                            <input type="radio" id="customRadio5" name="example1" />
                        </td>
                        <td>00001</td>
                        <td>哒哒哒</td>
                        <td>35岁</td>
                    </tr>
                    </tbody>
                </table>
            </form></div>
        </div>
        <div class="col-md-10">s帆帆帆帆</div>
    </div>
</div>


<form id="registrationform" class="" role="form"><div class="container">
    <fieldset>
        <div id="" class="">
            <legend class="">挂号信息</legend>
        </div>
    </fieldset>
    <div class="row">
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="medicalRecordNo">病例号</label>
            <div class="col-md-8 input-group">
                <input type="text" class="form-control" id="medicalRecordNo" name="medicalRecordNo" readonly="true" value="${serialnumber}" />
                <span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="patientName">姓名</label>
            <div class="col-md-8 input-group">
                <input type="name" class="form-control" placeholder="请输入姓名" id="patientName" name="patientName"/>
                <span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="gender">性别</label>
            <div class="col-md-8 input-group">
                <select class="form-control" placeholder="请选择性别" id="gender" name="gender"><option value="1">男</option><option value="2">女</option></select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-3 control-label text-right" for="age">年龄</label>
            <div class="col-md-9">
                <div class="input-group">
                    <input type="number" class="form-control" placeholder="请输入年龄" id="age" name="age"/>
                    <span class="input-group-addon">
							<select name="monthoryear">
								<option value="year">岁</option>
								<option value="month">月</option>
							</select>
						</span><span class="input-group-addon" style="color: red">*</span>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="settleAccountsCategory">结算类别</label>
            <div class="col-md-8 input-group">
                <select class="form-control" id="settleAccountsCategory" name="settleAccountsCategory">
                    <option value="01">自费</option>
                    <option value="02">医保</option>
                </select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="registrationCategory">挂号类别</label>
            <div class="col-md-8 input-group">
                <select class="form-control" placeholder="请选择挂号类别" id="registrationCategory" name="registrationCategory">
                    <option value="01">普通</option><option value="02">急诊</option><option value="03">专家</option>
                </select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="departmentId">挂号科室</label>
            <div class="col-md-8 input-group">
                <select class="form-control" placeholder="请选择科室" id="departmentId" name="departmentId"><option>1</option></select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="doctorId">看诊医生</label>
            <div class="col-md-8 input-group">
                <select class="form-control" id="doctorId" name="doctorId"><option>1</option></select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4">
            <label class="col-md-3 control-label text-right" for="registrationDate">看诊日期</label>
            <div class="col-md-9 input-group">
                <input type="date" class="form-control" id="registrationDate" name="registrationDate"/><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="identityCardNo">身份证号</label>
            <div class="col-md-8">
                <input type="text" class="form-control" placeholder="请输入身份证号码" id="identityCardNo" name="identityCardNo"/>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="birthday">出生日期</label>
            <div class="col-md-8">
                <input type="date" class="form-control" id="birthday" name="birthday"/>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="familyAddress">家庭住址</label>
            <div class="col-md-8">
                <input type="text" class="form-control" id="familyAddress" name="familyAddress"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="medicalCategory">医疗类别</label>
            <div class="col-md-8">
                <select class="form-control" id="medicalCategory" name="medicalCategory"><option value="01">自付</option><option value="02">城镇职工</option><option value="03">城镇居民</option><option value="04">新农合</option>
                </select>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="registrationSource">挂号来源</label>
            <div class="col-md-8">
                <select class="form-control" placeholder="请选择挂号来源" id="registrationSource" name="registrationSource"><option value="01">医院挂号</option><option value="02">预约挂号</option></select>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="age">病历本</label>
            <div class="col-md-8">
                <input type="checkbox" class="form-control" id="book" name="book" checked="true" />
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="expense">应收金额</label>
            <div class="col-md-8 input-group">
                <span class="input-group-addon">￥</span>
                <input type="number" class="form-control" placeholder="应缴费金额" id="expense" name="expense"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 pull-right">
            <input type="reset" class="btn btn-default" value="清空" />&nbsp;&nbsp;
            <button type="button" id="rfs" class="btn btn-primary">&nbsp;&nbsp;&nbsp;&nbsp;挂号&nbsp;&nbsp;&nbsp;&nbsp;</button>
        </div>
    </div>
</div></form>
<form class="" role="form" id="registrationsearchform"><div class="container">
    <fieldset>
        <div id="legend" class="">
            <legend class="">检索挂号信息</legend>
        </div>
    </fieldset>
    <div class="row">
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="medicalRecordNoS">病例号</label>
            <div class="col-md-8 input-group">
                <input type="text" class="form-control" id="medicalRecordNoS" name="medicalRecordNo" value="1234567890123" />
                <span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="patientNameS">姓名</label>
            <div class="col-md-8 input-group">
                <input type="name" class="form-control" placeholder="请输入姓名" id="patientNameS" name="patientName"/>
                <span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="departmentIdS">挂号科室</label>
            <div class="col-md-8 input-group">
                <select class="form-control" placeholder="请选择科室" id="departmentIdS" name="departmentId"><option>1</option></select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="form-group col-md-3">
            <label class="col-md-4 control-label text-right" for="doctorIdS">看诊医生</label>
            <div class="col-md-8 input-group">
                <select class="form-control" id="doctorIdS" name="doctorId"><option>1</option></select><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-4">
            <label class="col-md-3 control-label text-right" for="registrationDateS">看诊日期</label>
            <div class="col-md-9 input-group">
                <input type="date" class="form-control" id="registrationDateS" name="registrationDate"/><span class="input-group-addon" style="color: red">*</span>
            </div>
        </div>
        <div class="col-md-2 pull-right">
            <button class="btn btn-primary">检索</button>
        </div>
    </div>
</div></form>
<div class="container">
    <table class="table table-hover table-striped">
    <tbody>
    <tr>
        <td>1</td>
        <td>张三</td>
        <td>男</td>
        <td>50</td>
    </tr>
    <tr>
        <td>2</td>
        <td>李四</td>
        <td>女</td>
        <td>48</td>
    </tr>
    <tr>
        <td>3</td>
        <td>王五</td>
        <td>男</td>
        <td>52</td>
    </tr>
    <tr>
        <td>4</td>
        <td>马六</td>
        <td>男</td>
        <td>55</td>
    </tr>
    </tbody>
</table>
    <div align="center"><ul class="pagination pagination-lg">
        <li><a href="#">&laquo;</a></li>
        <li class="active"><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li class="disabled"><a href="#">5</a></li>
        <li><a href="#">&raquo;</a></li>
    </ul></div></div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#rfs").click(function(){
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "registrationinfo/add" ,//url
                data: $('#registrationform').serialize(),
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    };
                },
                error : function() {
                    alert("异常！");
                }
            });
        });
    });
</script>
</body>
</html>