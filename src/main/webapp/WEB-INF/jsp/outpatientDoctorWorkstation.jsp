<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="shortcut icon" href="images/icon.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/pagination.css">
</head>
<body>
<!-- 诊断弹窗 -->
<div class="modal fade" id="DiagnosisModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 1000px;margin-left: -50%;margin-top: 20%">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">诊断</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- 模态框主体 -->
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
                        <table class="table table-striped table-hover table-sm table-bordered" >
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
                                <div class="input-group input-group-sm">
                                    <label for="searchDiagnosisPage"></label>
                                    <input type="number" class="form-control" id="searchDiagnosisPage" placeholder="页码" style="width: 40px"/>
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">Go</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <table class="table table-striped table-hover table-sm table-bordered">
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
            <!-- 模态框底部 -->
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" >导入结果</button>
            </div>
        </div>
    </div>
</div>
<!--整体界面-->
<div class="container">
    <nav class="navbar navbar-expand-md ">
        <div class="col-md-9">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="#">
                <img src="images/logo.png" alt="logo" style="height: 45px;margin-left: -5px;">
            </a>
        </div>
        <div class="col-md-3 text-right" >
            <span ><strong>门诊医生：</strong></span>
            <span>哈哈哈</span>&nbsp;
            <button type="button" class="btn btn-secondary">退出</button>
        </div>
    </nav>
    <div class="row">
        <!--左侧患者搜索卡片-->
        <div id="searchCard" class="collapse" style="position:absolute ;z-index: 100 ;margin-top: 65px ;margin-left: 15px">
            <div class="card">
                <div class="card-body">
                    <!--患者搜索框-->
                    <form id="searchPatientForm" role="form" onkeypress="return event.keyCode !== 13;">
                        <label class="control-label font-weight-bold" for="searchPatientKey">患者查询：</label>
                        <div class="input-group input-group-sm">
                            <input type="search" class="form-control" id="searchPatientKey" name="searchPatientKey" style="width: 175px"
                                placeholder="输入姓名或病历号"/>
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">搜索</button>
                            </div>
                        </div>
                    </form>
                    <!--患者搜索结果-->
                    <ul class="nav nav-pills">
                        <li class="nav-item">
                            <a id="patientSearchCategory1" class="nav-link active font-weight-bold" href="#">本人</a>
                        </li>
                        <li class="nav-item">
                            <a id="patientSearchCategory2" class="nav-link font-weight-bold" href="#">科室</a>
                        </li>
                    </ul>
                    <form id="patientListForm" role="form" onkeypress="return event.keyCode !== 13;">
                        <table class="table table-condensed table-striped table-hover table-sm">
                            <thead>
                            <tr>
                                <th colspan="3">待诊患者<span id="notSeenNumSpan" class="badge badge-pill badge-danger">0</span></th>
                                <th style="text-align:right;">
                                    <a href="#"><img src="images/reflush_img.jpg" alt="刷新" style="height: 15px;width: 15px"></a>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="searchPatientTbody1"></tbody>
                        </table>
                        <table class="table table-condensed table-striped table-hover table-sm">
                            <thead>
                            <tr>
                                <th colspan="3">已诊患者<span id="isSeenNumSpan" class="badge badge-pill badge-success">0</span></th>
                                <th style="text-align:right;">
                                    <a href="#"><img src="images/reflush_img.jpg" alt="刷新" style="height: 15px;width: 15px"></a>
                                </th>
                            </tr>
                            </thead>
                            <tbody id="searchPatientTbody2"></tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!--主体-->
        <div class="container">
            <!--患者信息-->
            <div class="card"><div class="card-body">
                <div class="row" >
                    <div id="patientInfoDiv" class="col-md-11 form-inline" >
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#searchCard" id="searchPatientButton">搜索</button>
                            <button type="button" class="btn btn-info">统计</button>
                        </div>
                        &nbsp;&nbsp;
                        <label class="control-label font-weight-bold">就诊状态：</label><span id="visitStatusSpan" class="text-success font-weight-bold"></span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">病单号：</label><span></span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">姓名：</label><span></span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">性别：</label><span></span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">年龄：</label><span></span>&nbsp;&nbsp;
                    </div>
                    <div class="col-md-1 text-right">
                        <button type="button" class="btn btn-danger">诊毕</button>
                    </div>
                </div>
            </div></div>
            <!--可变部分-->
            <div class="card">
                <!--功能导航栏-->
                <div class="card-header">
                    <ul class="nav nav-tabs  card-header-tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link font-weight-bold active" data-toggle="tab" href="#home1" style="font-size: 28px;">病历首页</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link font-weight-bold dropdown-toggle" data-toggle="dropdown" href="#" style="font-size: 28px;">申请</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" data-toggle="tab" href="#menu1" style="font-size: 20px;">检查</a>
                                <a class="dropdown-item" data-toggle="tab" href="#menu2" style="font-size: 20px;">检验</a>
                                <a class="dropdown-item" data-toggle="tab" href="#menu3" style="font-size: 20px;">处置</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link font-weight-bold" data-toggle="tab" href="#menu4" style="font-size: 28px;">门诊确诊</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link font-weight-bold dropdown-toggle" data-toggle="dropdown" href="#" style="font-size: 28px;">处方</a>
                            <div class="dropdown-menu">
                                <a class="dropdown-item" data-toggle="tab" href="#menu5" style="font-size: 20px;">成药</a>
                                <a class="dropdown-item" data-toggle="tab" href="#menu6" style="font-size: 20px;">草药</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link font-weight-bold" data-toggle="tab" href="#menu7" style="font-size: 28px;">患者账单</a>
                        </li>
                    </ul>
                </div>
                <!--功能主体-->
                <div class="card-body">
                    <div class="tab-content">
                        <!--1.病历首页-->
                        <div id="home1" class="container tab-pane active">
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
                                                    <button type="button" class="btn btn-outline-danger btn-sm">清屏</button>
                                                    <button type="button" class="btn btn-outline-secondary btn-sm" disabled>暂存</button>
                                                    <button type="button" class="btn btn-outline-success btn-sm" disabled>提交</button>
                                                    <button type="button" class="btn btn-outline-info btn-sm">存为模板</button>
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
                                                    <table class="table table-condensed table-striped table-hover table-sm table-bordered" >
                                                        <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>ICD编码</th>
                                                            <th>名称</th>
                                                            <th>主诊</th>
                                                            <th>疑似</th>
                                                            <th>发病日期<small>(不完整会丢失)</small></th>
                                                            <th>&nbsp;</th>
                                                            <th class="text-center" style="padding: 0"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#DiagnosisModal" style="width: 100%;height: 100%">+
                                                            </button></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody id="diagnosisContextTbody1"></tbody>
                                                    </table>
                                                </div>
                                                <!--中医诊断输入-->
                                                <div class="form-group ">
                                                    <label class="font-weight-bold">中医诊断</label>
                                                    <table class="table table-condensed table-striped table-hover table-sm table-bordered" >
                                                        <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>ICD编码</th>
                                                            <th>名称</th>
                                                            <th>主诊</th>
                                                            <th>疑似</th>
                                                            <th>发病日期<small>(不完整会丢失)</small></th>
                                                            <th>&nbsp;</th>
                                                            <th class="text-center" style="padding: 0"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#DiagnosisModal" style="width: 100%;height: 100%">+
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
                                            <ul class="nav nav-pills " role="tablist" >
                                                <li class="nav-item">
                                                    <a class="nav-link active " data-toggle="pill" href="#home1_1"><small>病历模板</small></a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="pill" href="#home1_2"><small>常用诊断</small></a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="pill" href="#home1_3"><small>历史病历</small></a>
                                                </li>
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <!--病历模板部分-->
                                                <div id="home1_1" class="container tab-pane active">
                                                    <hr>
                                                    <div class="text-right" style="margin-top: 8px">
                                                        <button type="button" class="btn btn-outline-secondary btn-sm">模板管理</button>
                                                    </div>
                                                    <!--病历模板分类选择-->
                                                    <div id="medrecTempChooseDiv" class="bg-light" style="border: 1px solid rgba(0, 0, 0, 0.125); border-radius: 0.25rem;padding: 0.75rem 0rem;">
                                                        &nbsp;&nbsp;
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input active" id="wholeRadio" name="medrecTemplateRadioGroup">
                                                            <label class="custom-control-label" for="wholeRadio">全院</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" id="departRadio" name="medrecTemplateRadioGroup">
                                                            <label class="custom-control-label" for="departRadio">科室</label>
                                                        </div>
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input" id="personRadio" name="medrecTemplateRadioGroup">
                                                            <label class="custom-control-label" for="personRadio">个人</label>
                                                        </div>
                                                    </div>
                                                    <div class="card">
                                                        <!--病历模板列表-->
                                                        <div id="MedrecTempListDiv" class="list-group list-group"></div>
                                                    </div>
                                                    <div id="MedrecTempContextDiv" >
                                                        <div class="text-right" style="margin-top: 8px">
                                                            <button type="button" class="btn btn-outline-success btn-sm">引用</button>
                                                        </div>
                                                        <!--模板内容-->
                                                        <div class="card">
                                                            <div class="card-header"><strong>模板内容：</strong><span></span></div>
                                                            <div class="card-body">
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="chiefComplaintTemplate">主诉：</label>
                                                                    <input type="text" class="form-control " id="chiefComplaintTemplate" readonly="readonly">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="currentMedicalHistoryTemplate">现病史：</label>
                                                                    <textarea class="form-control " rows="2" id="currentMedicalHistoryTemplate" readonly="readonly"></textarea>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="font-weight-bold" for="physicalExaminationTemplate">体格检查：</label>
                                                                    <textarea class="form-control " rows="2" id="physicalExaminationTemplate" readonly="readonly"></textarea>
                                                                </div>
                                                                <!--模板诊断*2-->
                                                                <div class="form-group">
                                                                    <label class="font-weight-bold">西医诊断</label>
                                                                    <table class="table table-condensed table-striped table-hover table-sm table-bordered" >
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
                                                                    <label class="font-weight-bold">中医诊断</label>
                                                                    <table class="table table-condensed table-striped table-hover table-sm table-bordered" >
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
                                                        <div class="list-group">
                                                            <a href="#" class="list-group-item list-group-item-action">大肠杆菌感染<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">高血压<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">伤寒<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">呼吸道疾病<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">糖尿病<span class="badge badge-pill badge-danger">X</span></a>
                                                        </div>
                                                    </div>
                                                    <div class="card" style="margin-top: 8px">
                                                        <div class="card-header"><strong>中医诊断</strong></div>
                                                        <!--诊断内容-->
                                                        <div class="list-group">
                                                            <a href="#" class="list-group-item list-group-item-action">大肠杆菌感染<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">高血压<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">伤寒<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">呼吸道疾病<span class="badge badge-pill badge-danger">X</span></a>
                                                            <a href="#" class="list-group-item list-group-item-action">糖尿病<span class="badge badge-pill badge-danger">X</span></a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!--历史病历部分-->
                                                <div id="home1_3" class="container tab-pane fade" >
                                                    <hr>
                                                    <!--历史病历信息部分-->
                                                    <div class="card">
                                                        <div class="card-header"><strong>历史病历信息</strong></div>
                                                        <div class="list-group">
                                                            <a href="#" class="list-group-item list-group-item-action">2018-02-12 12:10:20 外科</a>
                                                            <a href="#" class="list-group-item list-group-item-action">2018-02-12 12:10:20 心理科</a>
                                                            <a href="#" class="list-group-item list-group-item-action">2018-02-12 12:10:20 内科</a>
                                                        </div>
                                                    </div>
                                                    <!--模板内容-->
                                                    <div class="card" style="margin-top: 8px">
                                                        <div class="card-header"><strong>历史病历明细</strong></div>
                                                        <div class="card-body">
                                                            <p><strong>主诉：</strong>amplet</p>
                                                            <p><strong>现病史：</strong>ampletaaaaaaaaaaaaaaaaaa</p>
                                                            <p><strong>现病治疗情况：</strong>ampletaaaaaaaaaaaaaaaaaaaaaaa</p>
                                                            <p><strong>既往史：</strong>ampletaaaaaaaaaaaaaaaaaaaaaaaaa</p>
                                                            <p><strong>过敏史：</strong>ampletaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
                                                            <p><strong>体格检查：</strong>ampletssssssssssssssssssssssss</p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="menu1" class="container tab-pane fade"><br>
                            <h2>Menu 1</h2>
                            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                        </div>
                        <div id="menu2" class="container tab-pane fade"><br>
                            <h3>Menu 2</h3>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                        </div>
                        <div id="menu3" class="container tab-pane fade"><br>
                            <h3>Menu 2</h3>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                        </div>
                        <div id="menu4" class="container tab-pane fade"><br>
                            <h3>Menu 2</h3>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                        </div>
                        <div id="menu5" class="container tab-pane fade"><br>
                            <h3>Menu 2</h3>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                        </div>
                        <div id="menu6" class="container tab-pane fade"><br>
                            <h3>Menu 2</h3>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
                        </div>
                        <div id="menu7" class="container tab-pane fade"><br>
                            <h3>Menu 2</h3>
                            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
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
<script type="text/javascript" src="js/myJquery/outpatientDoctorJs.js"></script>
<script type="text/javascript" src="js/jquery.pagination.js"></script>
</body>
</html>