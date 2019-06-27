<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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
    <title>门诊医生工作站</title>
    <link id="iconLink" rel="icon" href="images/HISicon.ico" type="image/x-icon">
    <link id="shortIconLink" rel="shortcut icon" href="images/HISicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/pagination.css">
</head>
<body>
<div id="alertAllDiv"></div>
<!-- 药品弹窗 -->
<div class="modal fade" id="DrugsModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1200px;margin-left: -70%;margin-top: 20%">
            <div class="modal-header">
                <h4 class="modal-title">药品</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <form class="form-inline" onkeypress="return event.keyCode !== 13;">
                            <label class="control-label font-weight-bold" for="drugsKey">药品查询：</label>
                            <div  id="drugsSearchInput" class="input-group">
                                <input type="search" class="form-control" id="drugsKey" name="searchDrugsKey" placeholder="输入拼音首字母"/>
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">搜索</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-6"><div style="margin-top: 7px">
                        <div class="col-md-3"><label class="control-label font-weight-bold">选择结果</label></div>
                    </div></div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-bordered" >
                            <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>ID</th>
                                <th>名称</th>
                                <th>规格</th>
                                <th>单价</th>
                            </tr>
                            </thead>
                            <tbody id="drugsNotCheckedTbody"></tbody>
                        </table>
                        <!--分页-->
                        <div class="row">
                            <div class="col-md-9 text-center">
                                <div id="drugsPagination" class="myPagination"></div>
                            </div>
                            <div id="drugsPageJump" class="col-md-3">
                                <div class="input-group">
                                    <label for="searchDrugsPage"></label>
                                    <input type="number" class="form-control" id="searchDrugsPage" style="width:40px"/>
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">Go</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>ID</th>
                                <th>名称</th>
                                <th>规格</th>
                                <th>单价</th>
                            </tr>
                            </thead>
                            <tbody id="drugsCheckedTbody"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" >导入结果</button>
                <button type="button" class="btn btn-primary" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 检查结果弹窗 -->
<div class="modal fade" id="ResultModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1000px;margin-left: -50%;margin-top: 20%">
            <div class="modal-header">
                <h4 class="modal-title">检查结果</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <label  class="control-label font-weight-bold">项目名称：</label><span></span>
                <br>
                <label  class="control-label font-weight-bold">结果描述：</label><span ></span>
                <br>
                <img class="card-img-bottom" src="" alt="无结果图" style="width:50%;margin-left: 25%">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- 项目弹窗 -->
<div class="modal fade" id="ItemModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1000px;margin-left: -50%;margin-top: 20%">
            <div class="modal-header">
                <h4 class="modal-title">项目</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <form class="form-inline" onkeypress="return event.keyCode !== 13;">
                            <label class="control-label font-weight-bold" for="itemKey">项目查询：</label>
                            <div  id="itemSearchInput" class="input-group">
                                <input type="search" class="form-control" id="itemKey" name="searchItemKey" placeholder="输入拼音首字母"/>
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">搜索</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-6"><div style="margin-top: 7px">
                        <div class="row">
                            <div class="col-md-3"><label class="control-label font-weight-bold">选择结果</label></div>
                            <div id="itemAllAmountDiv" class="col-md-9 text-right">
                                <label class="control-label font-weight-bold">总金额：</label>
                                <span id="itemModalAmount"></span>
                            </div>
                        </div>
                    </div></div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-bordered" >
                            <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>ID</th>
                                <th>名称</th>
                                <th>价格</th>
                            </tr>
                            </thead>
                            <tbody id="itemNotCheckedTbody"></tbody>
                        </table>
                        <!--分页-->
                        <div class="row">
                            <div class="col-md-9 text-center">
                                <div id="itemPagination" class="myPagination"></div>
                            </div>
                            <div id="itemPageJump" class="col-md-3">
                                <div class="input-group">
                                    <label for="searchItemPage"></label>
                                    <input type="number" class="form-control" id="searchItemPage" style="width:40px" />
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">Go</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>ID</th>
                                <th>名称</th>
                                <th>价格</th>
                            </tr>
                            </thead>
                            <tbody id="itemCheckedTbody"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" >导入结果</button>
                <button type="button" class="btn btn-primary" >保存</button>
            </div>
        </div>
    </div>
</div>
<!-- 诊断弹窗 -->
<div class="modal fade" id="DiagnosisModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1000px;margin-left: -50%;margin-top: 20%">
            <div class="modal-header">
                <h4 class="modal-title">诊断</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <form class="form-inline" onkeypress="return event.keyCode !== 13;">
                            <label class="control-label font-weight-bold" for="diagnosisKey">诊断查询：</label>
                            <div  id="diagnosisSearchInput" class="input-group">
                                <input type="search" class="form-control" id="diagnosisKey" name="searchDiagnosisKey" placeholder="输入拼音首字母"/>
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="button">搜索</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-6"><div style="margin-top: 7px">
                        <label class="control-label font-weight-bold">选择结果</label>
                    </div></div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-bordered" >
                            <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>ID</th>
                                <th>ICD编码</th>
                                <th>名称</th>
                            </tr>
                            </thead>
                            <tbody id="diagnosisNotCheckedTbody"></tbody>
                        </table>
                        <!--分页-->
                        <div class="row">
                            <div class="col-md-9 text-center">
                                <div id="diagnosisPagination" class="myPagination"></div>
                            </div>
                            <div id="diseasePageJump" class="col-md-3">
                                <div class="input-group">
                                    <label for="searchDiagnosisPage"></label>
                                    <input type="number" class="form-control" id="searchDiagnosisPage" style="width: 40px"/>
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">Go</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-bordered">
                            <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>ID</th>
                                <th>ICD编码</th>
                                <th>名称</th>
                            </tr>
                            </thead>
                            <tbody id="diagnosisCheckedTbody"></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" >导入确诊</button>
                <button type="button" class="btn btn-primary" >导入结果</button>
                <button type="button" class="btn btn-primary" >保存</button>
            </div>
        </div>
    </div>
</div>
<!--整体界面-->
<div class="container-fluid">
    <nav class="navbar navbar-expand-md ">
        <div class="col-md-9">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="#">
                <img src="images/logo.png" alt="logo" style="height: 45px;margin-left: -5px;">
            </a>
        </div>
        <div class="col-md-3 text-right" >
            <span ><strong>门诊医生：</strong></span>
            <span>${userName}</span>&nbsp;
            <input type="hidden" id="doctorId" value="${userId}"><!--doctorId-->
            <button type="button" class="btn btn-secondary">退出</button>
        </div>
    </nav>
    <!--左侧患者搜索卡片-->
    <div id="searchCard" class="collapse" style="position:absolute ;z-index: 100 ;margin-top: 65px ;margin-left: 15px">
        <div class="card">
            <div class="card-body">
                <!--患者搜索框-->
                <form id="searchPatientForm" role="form" onkeypress="return event.keyCode !== 13;">
                    <label class="control-label font-weight-bold" for="searchPatientKey">患者查询：</label>
                    <div class="input-group">
                        <input type="search" class="form-control" id="searchPatientKey" name="searchPatientKey" style="width: 175px"
                               placeholder="输入姓名或病历号"/>
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="button">搜索</button>
                        </div>
                    </div>
                </form>
                <!--患者搜索结果-->
                <ul class="nav nav-pills mt-2">
                    <li class="nav-item">
                        <a id="patientSearchCategory1" class="nav-link active font-weight-bold" href="#">本人</a>
                    </li>
                    <li class="nav-item">
                        <a id="patientSearchCategory2" class="nav-link font-weight-bold" href="#">科室</a>
                    </li>
                </ul>
                <form id="patientListForm" role="form" onkeypress="return event.keyCode !== 13;">
                    <table class="table table-condensed table-striped table-hover table-bordered mt-2">
                        <thead>
                        <tr>
                            <th colspan="3">待诊患者<span id="notSeenNumSpan" class="badge badge-pill badge-success">0</span></th>
                            <th style="text-align:center">
                                <a href="#"><img src="images/reflush_img.jpg" alt="刷新" style="height: 20px;width: 20px"></a>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="searchPatientTbody1"></tbody>
                    </table>
                    <table class="table table-condensed table-striped table-hover table-bordered">
                        <thead>
                        <tr>
                            <th colspan="3">已诊患者<span id="isSeenNumSpan" class="badge badge-pill badge-danger">0</span></th>
                            <th style="text-align:center">
                                <a href="#"><img src="images/reflush_img.jpg" alt="刷新" style="height: 20px;width: 20px"></a>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="searchPatientTbody2"></tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <!--左侧统计卡片-->
    <div id="statisticsCard" class="collapse" style="position:absolute ;z-index: 100 ;margin-top: 65px ;margin-left: 15px">
        <div class="card">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-10">
                        <div class="input-group mb-2 ">
                            <div class="input-group-prepend">
                                <span class="input-group-text">起始时间</span>
                            </div>
                            <input type="datetime-local" class="form-control">
                        </div>
                        <div class="input-group ">
                            <div class="input-group-prepend">
                                <span class="input-group-text">截止时间</span>
                            </div>
                            <input type="datetime-local" class="form-control">
                        </div>
                    </div>
                    <div class="col-md-2">
                        <button type="button" class="btn btn-info btn-sm mt-4">查询</button>
                    </div>
                </div>
                <div class="card-text mt-2">
                    <strong>看诊人数：</strong><span></span>&nbsp;&nbsp;<strong>总费用：</strong><span></span>
                </div>
                <table class="table table-condensed table-striped table-hover table-bordered mt-2">
                    <thead>
                    <tr>
                        <th>病历号</th>
                        <th>检查费</th>
                        <th>检验费</th>
                        <th>处置费</th>
                        <th>成药费</th>
                        <th>草药费</th>
                    </tr>
                    </thead>
                    <tbody></tbody>
                    <tbody>
                    <tr>
                        <th>费用和</th><th></th><th></th><th></th><th></th><th></th>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!--主体-->
    <div class="container-fluid">
        <!--患者信息-->
        <div class="card"><div class="card-body">
            <div class="row" >
                <div id="patientInfoDiv" class="col-md-11 form-inline" >
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#searchCard" id="searchPatientButton">搜索</button>
                        <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#statisticsCard" id="statisticsButton">统计</button>
                    </div>
                    &nbsp;&nbsp;
                    <label class="control-label font-weight-bold">就诊状态：</label><span id="visitStatusSpan" class="text-success font-weight-bold"></span>&nbsp;&nbsp;
                    <label class="control-label font-weight-bold">病单号：</label><span></span>&nbsp;&nbsp;
                    <label class="control-label font-weight-bold">姓名：</label><span></span>&nbsp;&nbsp;
                    <label class="control-label font-weight-bold">性别：</label><span></span>&nbsp;&nbsp;
                    <label class="control-label font-weight-bold">年龄：</label><span></span>&nbsp;&nbsp;
                </div>
                <div class="col-md-1 text-right">
                    <button id="overVisitBtn" type="button" class="btn btn-danger">诊毕</button>
                </div>
            </div>
        </div></div>
        <!--可变部分-->
        <div class="card">
            <!--功能导航栏-->
            <div class="card-header">
                <ul id="allNavTab" class="nav nav-tabs  card-header-tabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold active" data-toggle="tab" href="#home1" style="font-size: 28px;">病历首页</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link font-weight-bold dropdown-toggle" data-toggle="dropdown" href="#" style="font-size: 28px;">申请</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" data-toggle="tab" href="#menu1" style="font-size: 20px;">检查</a>
                            <a class="dropdown-item" data-toggle="tab" href="#menu1" style="font-size: 20px;">检验</a>
                            <a class="dropdown-item" data-toggle="tab" href="#menu1" style="font-size: 20px;">处置</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold" data-toggle="tab" href="#menu2" style="font-size: 28px;">门诊确诊</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link font-weight-bold dropdown-toggle" data-toggle="dropdown" href="#" style="font-size: 28px;">处方</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" data-toggle="tab" href="#menu3" style="font-size: 20px;">成药</a>
                            <a class="dropdown-item" data-toggle="tab" href="#menu3" style="font-size: 20px;">草药</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link font-weight-bold" data-toggle="tab" href="#menu4" style="font-size: 28px;">患者账单</a>
                    </li>
                </ul>
            </div>
            <!--功能主体-->
            <div class="card-body">
                <div class="tab-content">
                    <!--1.病历首页-->
                    <div id="home1" class="container-fluid tab-pane active"><br>
                        <div class="row">
                            <div class="col-md-8">
                                <!--病历信息表格-->
                                <form id="medicalRecordInfoForm" role="form" onkeypress="return event.keyCode !== 13;">
                                    <!--空白内容防止出错-->
                                    <input type="hidden" name="xDiagnosisList[0].diseaseId" value="-1">
                                    <input type="hidden" name="zDiagnosisList[0].diseaseId" value="-1">
                                    <input type="hidden" name="isNewMajorDiagnosisCheckded" value="-1">
                                    <input type="hidden" name="isNewSuspectChecked" value="-1">
                                    <div class="row ">
                                        <div class="col-md-7">
                                            <h4 class="font-weight-bold">病历信息</h4>
                                        </div>
                                        <div class="col-md-5 text-right">
                                            <div id="medicalInfoBtnGroup" class="btn-group">
                                                <button type="button" class="btn btn-outline-danger">清屏</button>
                                                <button type="button" class="btn btn-outline-secondary">暂存</button>
                                                <button type="button" class="btn btn-outline-success">提交</button>
                                                <button type="button" class="btn btn-outline-info">存为模板</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!--病史输入部分-->
                                    <div id="medicalInfoCard1" class="card">
                                        <div class="card-header font-weight-bold">病史内容</div>
                                        <div class="card-body">
                                            <div class="form-group form-inline">
                                                <label class="col-md-2 font-weight-bold" for="chiefComplaint">主诉</label>
                                                <input type="text" class="form-control col-md-10" id="chiefComplaint" name="chiefComplaint">
                                            </div>
                                            <div class="form-group form-inline">
                                                <label class="col-md-2 font-weight-bold" for="currentMedicalHistory">现病史</label>
                                                <textarea class="form-control col-md-10" rows="2" id="currentMedicalHistory" name="currentMedicalHistory"></textarea>
                                            </div>
                                            <div class="form-group form-inline text-center">
                                                <label class="col-md-2 font-weight-bold" for="currentTreatmentSituation">&nbsp;现病治疗情况</label>
                                                <textarea class="form-control col-md-10" rows="2" id="currentTreatmentSituation" name="currentTreatmentSituation"></textarea>
                                            </div>
                                            <div class="form-group form-inline">
                                                <label class="col-md-2 font-weight-bold" for="pastHistory">既往史</label>
                                                <textarea class="form-control col-md-10" rows="2" id="pastHistory" name="pastHistory"></textarea>
                                            </div>
                                            <div class="form-group form-inline">
                                                <label class="col-md-2 font-weight-bold" for="allergiesHistory">过敏史</label>
                                                <textarea class="form-control col-md-10" rows="2" id="allergiesHistory" name="allergiesHistory"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <!--检查输入部分-->
                                    <div id="medicalInfoCard2" class="card">
                                        <div class="card-header font-weight-bold">检查及结果</div>
                                        <div class="card-body">
                                            <div class="form-group form-inline">
                                                <label class="col-md-2 font-weight-bold" for="physicalExamination">体格检查</label>
                                                <textarea class="form-control col-md-10" rows="2" id="physicalExamination" name="physicalExamination"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <!--诊断输入部分-->
                                    <div class="card">
                                        <div class="card-header font-weight-bold">评估诊断</div>
                                        <div id="diagnosisContentCard" class="card-body">
                                            <div class="form-group">
                                                <label class="font-weight-bold">西医诊断</label>
                                                <table class="table table-condensed table-striped table-hover table-bordered" >
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>ICD编码</th>
                                                        <th>名称</th>
                                                        <th>主诊</th>
                                                        <th>疑似</th>
                                                        <th>发病日期<small>(不完整会丢失)</small></th>
                                                        <th>&nbsp;</th>
                                                        <th class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#DiagnosisModal" style="width: 100%;height: 100%">+
                                                        </button></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="diagnosisContextTbody1"></tbody>
                                                </table>
                                            </div>
                                            <!--中医诊断输入-->
                                            <div class="form-group ">
                                                <label class="font-weight-bold">中医诊断</label>
                                                <table class="table table-condensed table-striped table-hover table-bordered" >
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>ICD编码</th>
                                                        <th>名称</th>
                                                        <th>主诊</th>
                                                        <th>疑似</th>
                                                        <th>发病日期<small>(不完整会丢失)</small></th>
                                                        <th>&nbsp;</th>
                                                        <th class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#DiagnosisModal" style="width: 100%;height: 100%">+
                                                        </button></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="diagnosisContextTbody2"></tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!--1.右侧功能-->
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <!-- Nav pills -->
                                        <ul id="homeRightNav" class="nav nav-pills " role="tablist" >
                                            <li class="nav-item">
                                                <a class="nav-link active " data-toggle="pill" href="#home1_1">病历模板</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="pill" href="#home1_2">常用诊断</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="pill" href="#home1_3">历史病历</a>
                                            </li>
                                        </ul>
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <!--病历模板部分-->
                                            <div id="home1_1" class="container tab-pane active">
                                                <hr>
                                                <!--模板搜索框-->
                                                <form id="searchMedrecTempForm" role="form" onkeypress="return event.keyCode !== 13;">
                                                    <label class="control-label font-weight-bold" for="searchMedrecTempKey"></label>
                                                    <div class="input-group">
                                                        <input type="search" class="form-control" id="searchMedrecTempKey" name="searchPatientKey"
                                                               placeholder="输入关键词"/>
                                                        <div class="input-group-append">
                                                            <button class="btn btn-primary" type="button">搜索</button>
                                                        </div>
                                                    </div>
                                                </form>
                                                <!--病历模板分类选择-->
                                                <div id="medrecTempChooseDiv" class="bg-light" style="border: 1px solid rgba(0, 0, 0, 0.125); border-radius: 0.25rem;padding: 0.75rem 0rem;">
                                                    &nbsp;&nbsp;
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input active" id="wholeRadio" name="medrecTemplateRadioGroup" value="1">
                                                        <label class="custom-control-label" for="wholeRadio">全院</label>
                                                    </div>
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input" id="departRadio" name="medrecTemplateRadioGroup" value="2">
                                                        <label class="custom-control-label" for="departRadio">科室</label>
                                                    </div>
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input" id="personRadio" name="medrecTemplateRadioGroup" value="3">
                                                        <label class="custom-control-label" for="personRadio">个人</label>
                                                    </div>
                                                </div>
                                                <div class="card">
                                                    <!--病历模板列表-->
                                                    <div id="MedrecTempListDiv" class="list-group list-group"></div>
                                                </div>
                                                <div id="MedrecTempContextDiv" >
                                                    <div class="text-right" style="margin-top: 8px">
                                                        <div id="medreTempBtnGroup" class="btn-group">
                                                            <button type="button" class="btn btn-outline-primary">新增</button>
                                                            <button type="button" class="btn btn-outline-warning">修改</button>
                                                            <button type="button" class="btn btn-outline-danger ">删除</button>
                                                            <button type="button" class="btn btn-outline-success">引用</button>
                                                        </div>
                                                    </div>
                                                    <!--模板内容-->
                                                    <div class="card">
                                                        <div class="card-header"><strong>模板内容</strong></div>
                                                        <div class="card-body">
                                                            <form id="medrecTempContextForm" role="form" onkeypress="return event.keyCode !== 13;">
                                                                <input id="idTemplate" type="hidden" name="id">
                                                                <div id="medrecTempCategoryDiv" class="form-group">
                                                                    <label class="font-weight-bold" for="categoryTemplate">适用范围：</label>
                                                                    <select id="categoryTemplate" name="category" class="custom-select">
                                                                        <option value="0" selected></option>
                                                                        <option value="1">全院</option>
                                                                        <option value="2">科室</option>
                                                                        <option value="3">个人</option>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="templateCodeTemplate">模板编码：</label>
                                                                    <input type="text" class="form-control " id="templateCodeTemplate" name="templateCode" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="templateNameTemplate">模板名称：</label>
                                                                    <input type="text" class="form-control " id="templateNameTemplate" name="templateName" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="chiefComplaintTemplate">主诉：</label>
                                                                    <input type="text" class="form-control " id="chiefComplaintTemplate" name="chiefComplaint" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="currentMedicalHistoryTemplate">现病史：</label>
                                                                    <textarea class="form-control " rows="2" id="currentMedicalHistoryTemplate" name="currentMedicalHistory" readonly="readonly"></textarea>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="font-weight-bold" for="physicalExaminationTemplate">体格检查：</label>
                                                                    <textarea class="form-control " rows="2" id="physicalExaminationTemplate" name="physicalExamination" readonly="readonly"></textarea>
                                                                </div>
                                                                <!--模板诊断*2-->
                                                                <div class="form-group"><input type="hidden" name="diseaseId0" value="-1"><input type="hidden" name="diseaseId1" value="-1">
                                                                    <label class="font-weight-bold">西医诊断</label>&nbsp;<button type="button" class="btn btn-outline-warning"  data-toggle="modal" data-target="#DiagnosisModal">修改</button>
                                                                    <table class="table table-condensed table-striped table-hover table-bordered" >
                                                                        <thead>
                                                                        <tr>
                                                                            <th>ID</th>
                                                                            <th>ICD编码</th>
                                                                            <th>名称</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody></tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="font-weight-bold">中医诊断</label>&nbsp;<button type="button" class="btn btn-outline-warning "  data-toggle="modal" data-target="#DiagnosisModal">修改</button>
                                                                    <table class="table table-condensed table-striped table-hover  table-bordered" >
                                                                        <thead>
                                                                        <tr>
                                                                            <th>ID</th>
                                                                            <th>ICD编码</th>
                                                                            <th>名称</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody></tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="text-right" style="margin-top: 8px">
                                                                    <button type="button" class="btn btn-outline-success ">提交</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--常用诊断部分-->
                                            <div id="home1_2" class="container tab-pane fade">
                                                <hr>
                                                <div class="card">
                                                    <div class="card-header"><strong>西医诊断</strong></div>
                                                    <!--诊断内容-->
                                                    <div class="list-group"></div>
                                                </div>
                                                <div class="card" style="margin-top: 8px">
                                                    <div class="card-header"><strong>中医诊断</strong></div>
                                                    <!--诊断内容-->
                                                    <div class="list-group"></div>
                                                </div>
                                            </div>
                                            <!--历史病历部分-->
                                            <div id="home1_3" class="container tab-pane fade" >
                                                <hr>
                                                <!--历史病历信息部分-->
                                                <div class="card">
                                                    <div class="card-header"><strong>历史病历信息</strong></div>
                                                    <div id="historyMedicalInfoLabelDiv" class="list-group"></div>
                                                </div>
                                                <!--模板内容-->
                                                <div class="card" style="margin-top: 8px">
                                                    <div class="card-header"><strong>历史病历明细</strong></div>
                                                    <div id="historyContextDiv" class="card-body">
                                                        <p><strong>主诉：</strong><span></span></p>
                                                        <p><strong>现病史：</strong><span></span></p>
                                                        <p><strong>现病治疗情况：</strong><span></span></p>
                                                        <p><strong>既往史：</strong><span></span></p>
                                                        <p><strong>过敏史：</strong><span></span></p>
                                                        <p><strong>体格检查：</strong><span></span></p>
                                                        <p><strong>终诊：</strong><span></span></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--2.申请界面-->
                    <div id="menu1" class="container-fluid tab-pane fade"><br>
                        <input type="hidden" id="applyForType">
                        <div class="row">
                            <div class="col-md-8">
                                <!--项目信息表格-->
                                <form id="visitItemForm" role="form" onkeypress="return event.keyCode !== 13;">
                                    <div class="row ">
                                        <div class="col-md-4">
                                            <h4 class="font-weight-bold">**申请</h4>
                                        </div>
                                        <div class="col-md-8 text-right">
                                            <div id="applyForBtnGroup" class="btn-group">
                                                <button type="button" class="btn btn-outline-primary ">新增</button>
                                                <button type="button" class="btn btn-outline-dark ">暂存</button>
                                                <button type="button" class="btn btn-outline-success ">开立</button>
                                                <button type="button" class="btn btn-outline-danger ">删除</button>
                                                <button type="button" class="btn btn-outline-secondary ">作废</button>
                                                <button type="button" class="btn btn-outline-info ">存为组套</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!--申请单列表部分-->
                                    <div id="applyForCard" class="card">
                                        <div class="card-header font-weight-bold">**申请单列表</div>
                                        <table class="table table-condensed table-striped table-hover table-bordered" >
                                            <thead>
                                            <tr>
                                                <th>&nbsp;</th>
                                                <th>No</th>
                                                <th>申请时间</th>
                                                <th>状态</th>
                                                <th>申请人</th>
                                                <th>目的和要求</th>
                                                <th>收费状态</th>
                                                <th>执行状态</th>
                                                <th>金额</th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                    <br>
                                    <!--项目列表部分-->
                                    <div id="visitItemCard" class="card">
                                        <div class="card-header font-weight-bold">
                                            <div class="row">
                                                <div class="col-md-10">
                                                    <span>项目列表</span>
                                                </div>
                                                <div class="col-md-2 text-right">
                                                    <button type="button" class="btn btn-outline-warning " data-toggle="modal" data-target="#ItemModal">修改</button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-condensed table-striped table-hover  table-bordered" >
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>项目名称</th>
                                                <th>价格</th>
                                                <th>医生嘱托</th>
                                                <th>执行状态</th>
                                                <th>&nbsp;</th>
                                                <th>&nbsp;</th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                </form>
                            </div>
                            <!--1.右侧功能-->
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <!-- Nav pills -->
                                        <ul id="menu1RightNav" class="nav nav-pills " role="tablist" >
                                            <li class="nav-item">
                                                <a class="nav-link active " data-toggle="pill" href="#menu1_1">常用项目</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="pill" href="#menu1_2">**组套</a>
                                            </li>
                                        </ul>
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <!--常用项目部分-->
                                            <div id="menu1_1" class="container tab-pane active">
                                                <hr>
                                                <div class="card">
                                                    <div class="card-header"><strong>**项目</strong></div>
                                                    <!--项目内容-->
                                                    <div class="list-group"></div>
                                                </div>
                                            </div>
                                            <!--申请组套部分-->
                                            <div id="menu1_2" class="container tab-pane fade">
                                                <hr>
                                                <!--申请组套搜索框-->
                                                <form id="searchItemSetForm" role="form" onkeypress="return event.keyCode !== 13;">
                                                    <label class="control-label font-weight-bold" for="searchItemSetKey"></label>
                                                    <div class="input-group ">
                                                        <input type="search" class="form-control" id="searchItemSetKey" placeholder="输入关键词"/>
                                                        <div class="input-group-append">
                                                            <button class="btn btn-primary" type="button">搜索</button>
                                                        </div>
                                                    </div>
                                                </form>
                                                <!--申请组套分类选择-->
                                                <div id="itemSetChooseDiv" class="bg-light" style="border: 1px solid rgba(0, 0, 0, 0.125); border-radius: 0.25rem;padding: 0.75rem 0rem;">
                                                    &nbsp;&nbsp;
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input active" id="itemWholeRadio" name="itemRadio" value="1">
                                                        <label class="custom-control-label" for="itemWholeRadio">全院</label>
                                                    </div>
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input" id="itemDepartRadio" name="itemRadio" value="2">
                                                        <label class="custom-control-label" for="itemDepartRadio">科室</label>
                                                    </div>
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input" id="itemPersonRadio" name="itemRadio" value="3">
                                                        <label class="custom-control-label" for="itemPersonRadio">个人</label>
                                                    </div>
                                                </div>
                                                <div class="card">
                                                    <!--申请组套列表-->
                                                    <div id="itemSetListDiv" class="list-group list-group"></div>
                                                </div>
                                                <div id="itemSetContextDiv" >
                                                    <div class="text-right" style="margin-top: 8px">
                                                        <div id="itemSetBtnGroup" class="btn-group ">
                                                            <button type="button" class="btn btn-outline-primary ">新增</button>
                                                            <button type="button" class="btn btn-outline-warning ">修改</button>
                                                            <button type="button" class="btn btn-outline-danger ">删除</button>
                                                            <button type="button" class="btn btn-outline-success ">引用</button>
                                                        </div>
                                                    </div>
                                                    <!--组套内容-->
                                                    <div class="card">
                                                        <div class="card-header"><strong>组套内容</strong></div>
                                                        <div class="card-body">
                                                            <form id="itemSetContextForm" role="form" onkeypress="return event.keyCode !== 13;">
                                                                <input id="idSet" type="hidden" name="id">
                                                                <div id="itemSetCategoryDiv" class="form-group">
                                                                    <label class="font-weight-bold" for="categorySet">适用范围：</label>
                                                                    <select id="categorySet" name="useScope" class="custom-select">
                                                                        <option value="0" selected></option>
                                                                        <option value="1">全院</option>
                                                                        <option value="2">科室</option>
                                                                        <option value="3">个人</option>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="setCodeTemplate">组套编码：</label>
                                                                    <input type="text" class="form-control " id="setCodeTemplate" name="setCode" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="setNameTemplate">组套名称：</label>
                                                                    <input type="text" class="form-control " id="setNameTemplate" name="setName" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold">创建时间：</label ><span id="setCreatTime"></span>
                                                                </div>
                                                                <!--组套子项-->
                                                                <div class="form-group">
                                                                    <input type="hidden" name="objectId" value="-1"><input type="hidden" name="setSubEntrust" value=" ">
                                                                    <label class="font-weight-bold">组套项目</label>&nbsp;<button type="button" class="btn btn-outline-warning "  data-toggle="modal" data-target="#ItemModal">修改</button>
                                                                    <table class="table table-condensed table-striped table-hover table-bordered" >
                                                                        <thead>
                                                                        <tr>
                                                                            <th>项目名称</th>
                                                                            <th>医生嘱托</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody></tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="text-right" style="margin-top: 8px">
                                                                    <button type="button" class="btn btn-outline-success ">提交</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--3.门诊确诊-->
                    <div id="menu2" class="container-fluid tab-pane fade"><br>
                        <div class="row">
                            <div class="col-md-8">
                                <div class="row ">
                                    <div class="col-md-7">
                                        <h4 class="font-weight-bold">门诊确诊</h4>
                                    </div>
                                </div>
                                <!--初诊部分-->
                                <div class="card">
                                    <div class="card-header font-weight-bold">初诊</div>
                                    <div id="newDiagnosisContentCard" class="card-body">
                                        <label class="font-weight-bold">西医诊断</label>
                                        <table class="table table-condensed table-striped table-hover  table-bordered" >
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>ICD编码</th>
                                                <th>名称</th>
                                                <th>主诊</th>
                                                <th>疑似</th>
                                                <th>发病日期</th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                        <!--中医诊断输入-->
                                        <label class="font-weight-bold">中医诊断</label>
                                        <table class="table table-condensed table-striped table-hover  table-bordered" >
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>ICD编码</th>
                                                <th>名称</th>
                                                <th>主诊</th>
                                                <th>疑似</th>
                                                <th>发病日期</th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                </div>
                                <br>
                                <form id="finalDiagnosisForm" role="form" onkeypress="return event.keyCode !== 13;">
                                    <!--空白内容防止出错-->
                                    <input type="hidden" name="diagnosis0" value="-1">
                                    <input type="hidden" name="diagnosis1" value="-1">
                                    <!--终诊输入部分-->
                                    <div class="card">
                                        <div class="card-header font-weight-bold">
                                            <div class="row">
                                                <div class="col-md-3"><span>终诊</span></div>
                                                <div class="col-md-9">
                                                    <div class="text-right"><button id="identifyButton" type="button" class="btn btn-danger ">确诊</button></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-body">
                                            <div class="form-group">
                                                <label class="font-weight-bold">西医诊断</label>
                                                <table class="table table-condensed table-striped table-hover  table-bordered" >
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>ICD编码</th>
                                                        <th>名称</th>
                                                        <th>&nbsp;</th>
                                                        <th class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#DiagnosisModal" style="width: 100%;height: 100%">+
                                                        </button></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody></tbody>
                                                </table>
                                            </div>
                                            <div class="form-group">
                                                <!--中医诊断输入-->
                                                <label class="font-weight-bold">中医诊断</label>
                                                <table class="table table-condensed table-striped table-hover table-bordered" >
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>ICD编码</th>
                                                        <th>名称</th>
                                                        <th>&nbsp;</th>
                                                        <th class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#DiagnosisModal" style="width: 100%;height: 100%">+
                                                        </button></th>
                                                    </tr>
                                                    </thead>
                                                    <tbody></tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <!--右侧功能-->
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <ul class="nav nav-pills " >
                                            <li class="nav-item">
                                                <a id="commonDiagnosisLink" class="nav-link active " href="#">常用诊断</a>
                                            </li>
                                        </ul>
                                        <!--常用诊断部分-->
                                        <div id="comonDiagnosis" class="container">
                                            <hr>
                                            <div class="card">
                                                <div class="card-header"><strong>西医诊断</strong></div>
                                                <!--诊断内容-->
                                                <div class="list-group"></div>
                                            </div>
                                            <div class="card" style="margin-top: 8px">
                                                <div class="card-header"><strong>中医诊断</strong></div>
                                                <!--诊断内容-->
                                                <div class="list-group"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--4.处方申请部分-->
                    <div id="menu3" class="container-fluid tab-pane fade"><br>
                        <input type="hidden" id="prescriptionType">
                        <div class="row">
                            <div class="col-md-8">
                                <!--处方信息表格-->
                                <form id="prescriptionForm" role="form" onkeypress="return event.keyCode !== 13;">
                                    <div class="row ">
                                        <div class="col-md-4">
                                            <h4 class="font-weight-bold">**处方</h4>
                                        </div>
                                        <div class="col-md-8 text-right">
                                            <div id="prescriptionBtnGroup" class="btn-group">
                                                <button type="button" class="btn btn-outline-primary ">新增</button>
                                                <button type="button" class="btn btn-outline-dark ">暂存</button>
                                                <button type="button" class="btn btn-outline-success">发送</button>
                                                <button type="button" class="btn btn-outline-danger ">删除</button>
                                                <button type="button" class="btn btn-outline-secondary">作废</button>
                                                <button type="button" class="btn btn-outline-info ">存为组套</button>
                                            </div>
                                        </div>
                                    </div>
                                    <!--处方列表部分-->
                                    <div id="prescriptionCard" class="card">
                                        <div class="card-header font-weight-bold">**处方列表</div>
                                        <table class="table table-condensed table-striped table-hover  table-bordered" >
                                            <thead>
                                            <tr>
                                                <th>&nbsp;</th>
                                                <th>No</th>
                                                <th>处方名称</th>
                                                <th>类型</th>
                                                <th>开具时间</th>
                                                <th>状态</th>
                                                <th>金额</th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                    <br>
                                    <!--药品列表部分-->
                                    <div id="drugsCard" class="card">
                                        <div class="card-header font-weight-bold">
                                            <div class="row">
                                                <div class="col-md-10">
                                                    <span>药品列表</span>
                                                </div>
                                                <div class="col-md-2 text-right">
                                                    <button type="button" class="btn btn-outline-warning " data-toggle="modal" data-target="#DrugsModal">修改</button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table table-condensed table-striped table-hover  table-bordered" >
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>药品名</th>
                                                <th>规格</th>
                                                <th>单价</th>
                                                <th>用法</th>
                                                <th>用量</th>
                                                <th>频次</th>
                                                <th>天数</th>
                                                <th>数量</th>
                                            </tr>
                                            </thead>
                                            <tbody></tbody>
                                        </table>
                                    </div>
                                </form>
                            </div>
                            <!--1.右侧功能-->
                            <div class="col-md-4">
                                <div class="card">
                                    <div class="card-body">
                                        <ul id="menu3RightNav" class="nav nav-pills " role="tablist" >
                                            <li class="nav-item">
                                                <a class="nav-link active " data-toggle="pill" href="#menu3_1">常用药品</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" data-toggle="pill" href="#menu3_2">**组套</a>
                                            </li>
                                        </ul>
                                        <div class="tab-content">
                                            <!--常用药品部分-->
                                            <div id="menu3_1" class="container tab-pane active">
                                                <hr>
                                                <div class="card">
                                                    <div class="card-header"><strong>**药品</strong></div>
                                                    <!--项目内容-->
                                                    <div class="list-group"></div>
                                                </div>
                                            </div>
                                            <!--处方组套部分-->
                                            <div id="menu3_2" class="container tab-pane fade">
                                                <hr>
                                                <!--处方组套搜索框-->
                                                <form id="searchPrescriptionForm" role="form" onkeypress="return event.keyCode !== 13;">
                                                    <label class="control-label font-weight-bold" for="searchPrescriptionKey"></label>
                                                    <div class="input-group">
                                                        <input type="search" class="form-control" id="searchPrescriptionKey" placeholder="输入关键词"/>
                                                        <div class="input-group-append">
                                                            <button class="btn btn-primary" type="button">搜索</button>
                                                        </div>
                                                    </div>
                                                </form>
                                                <!--处方组套分类选择-->
                                                <div id="prescriptionChooseDiv" class="bg-light" style="border: 1px solid rgba(0, 0, 0, 0.125); border-radius: 0.25rem;padding: 0.75rem 0rem;">
                                                    &nbsp;&nbsp;
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input active" id="prescriptionWholeRadio" name="prescriptionRadio" value="1">
                                                        <label class="custom-control-label" for="prescriptionWholeRadio">全院</label>
                                                    </div>
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input" id="prescriptionDepartRadio" name="prescriptionRadio" value="2">
                                                        <label class="custom-control-label" for="prescriptionDepartRadio">科室</label>
                                                    </div>
                                                    <div class="custom-control custom-radio custom-control-inline">
                                                        <input type="radio" class="custom-control-input" id="prescriptionPersonRadio" name="prescriptionRadio" value="3">
                                                        <label class="custom-control-label" for="prescriptionPersonRadio">个人</label>
                                                    </div>
                                                </div>
                                                <div class="card">
                                                    <!--处方组套列表-->
                                                    <div id="prescriptionListDiv" class="list-group list-group"></div>
                                                </div>
                                                <div id="prescriptionContextDiv" >
                                                    <div class="text-right" style="margin-top: 8px">
                                                        <div id="prescriptionSetBtnGroup" class="btn-group ">
                                                            <button type="button" class="btn btn-outline-primary ">新增</button>
                                                            <button type="button" class="btn btn-outline-warning ">修改</button>
                                                            <button type="button" class="btn btn-outline-danger">删除</button>
                                                            <button type="button" class="btn btn-outline-success ">引用</button>
                                                        </div>
                                                    </div>
                                                    <!--组套内容-->
                                                    <div class="card">
                                                        <div class="card-header"><strong>组套内容</strong></div>
                                                        <div class="card-body">
                                                            <form id="prescriptionContextForm" role="form" onkeypress="return event.keyCode !== 13;">
                                                                <input id="prescriptionIdSet" type="hidden" name="id">
                                                                <div id="prescriptionCategoryDiv" class="form-group">
                                                                    <label class="font-weight-bold" for="categoryPrescription">适用范围：</label>
                                                                    <select id="categoryPrescription" name="useScope" class="custom-select">
                                                                        <option value="0" selected></option>
                                                                        <option value="1">全院</option>
                                                                        <option value="2">科室</option>
                                                                        <option value="3">个人</option>
                                                                    </select>
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="setCodeTemplate">组套编码：</label>
                                                                    <input type="text" class="form-control " id="prescriptionCodeTemplate" name="setCode" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="setNameTemplate">组套名称：</label>
                                                                    <input type="text" class="form-control " id="prescriptionNameTemplate" name="setName" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold">创建时间：</label ><span id="prescriptionCreatTime"></span>
                                                                </div>
                                                                <!--组套子项-->
                                                                <div class="form-group">
                                                                    <input type="hidden" name="objectId" value="-1"><input type="hidden" name="setSubEntrust" value=" ">
                                                                    <label class="font-weight-bold">组套项目</label>&nbsp;<button type="button" class="btn btn-outline-warning "  data-toggle="modal" data-target="#DrugsModal">修改</button>
                                                                    <table class="table table-condensed table-striped table-hover  table-bordered" >
                                                                        <thead>
                                                                        <tr>
                                                                            <th>药品名称</th>
                                                                            <th>用药嘱托</th>
                                                                        </tr>
                                                                        </thead>
                                                                        <tbody></tbody>
                                                                    </table>
                                                                </div>
                                                                <div class="text-right" style="margin-top: 8px">
                                                                    <button type="button" class="btn btn-outline-success">提交</button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--费用查看-->
                    <div id="menu4" class="container-fluid tab-pane fade"><br>
                        <div class="row ">
                            <div class="col-md-7">
                                <h4 class="font-weight-bold">费用明细</h4>
                            </div>
                        </div>
                        <!--申请费用-->
                        <div  class="card">
                            <div class="card-header font-weight-bold">
                                <span>申请费用</span>&nbsp;
                                <select class="custom-select-sm">
                                    <option value="0">全部</option>
                                    <option value="1">检查</option>
                                    <option value="2">检验</option>
                                    <option value="3">处置</option>
                                </select>&nbsp;&nbsp;
                                <strong>总金额：</strong><span></span>
                            </div>
                            <table class="table table-condensed table-striped table-hover  table-bordered" >
                                <thead>
                                <tr>
                                    <th>No</th>
                                    <th>分类</th>
                                    <th>目的和要求</th>
                                    <th>申请时间</th>
                                    <th>金额</th>
                                    <th>收费状态</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                        <br>
                        <!--处方费用-->
                        <div class="card">
                            <div class="card-header font-weight-bold">
                                <span>处方费用</span>&nbsp;
                                <select class="custom-select-sm">
                                    <option value="0">全部</option>
                                    <option value="1">成药</option>
                                    <option value="2">草药</option>
                                </select>&nbsp;&nbsp;
                                <strong>总金额：</strong><span></span>
                            </div>
                            <table class="table table-condensed table-striped table-hover  table-bordered" >
                                <thead>
                                <tr>
                                    <th>No</th>
                                    <th>分类</th>
                                    <th>处方名称</th>
                                    <th>处方类型</th>
                                    <th>开具时间</th>
                                    <th>金额</th>
                                    <th>收费状态</th>
                                </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/popper.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
<script type="text/javascript" src="js/myJquery/loginJs.js"></script>
<script type="text/javascript" src="js/myJquery/outpatientDoctorJs.js"></script>
<script type="text/javascript" src="js/myJquery/applyForJs.js"></script>
<script type="text/javascript" src="js/myJquery/prescriptionJs.js"></script>
<script type="text/javascript" src="js/myJquery/finalDiagnosisJs.js"></script>
<script type="text/javascript" src="js/myJquery/patientFeeJs.js"></script>
</body>
</html>