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
</head>
<body>
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
                    <div class="row">
                        <form class="" id="searchPatientForm" role="form">
                            <label class="control-label font-weight-bold" for="searchPatientKey">患者查询：</label>
                            <input type="search" class="form-control" id="searchPatientKey" name="searchPatientKey" style="width: 175px"
                                   placeholder="输入姓名或病历号"/>
                        </form>
                    </div>
                    <br>
                    <!--患者搜索结果-->
                    <div class="row" >
                        <ul class="nav nav-pills">
                            <li class="nav-item">
                                <a class="nav-link active font-weight-bold" href="#">本人</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link font-weight-bold" href="#">科室</a>
                            </li>
                        </ul>
                    </div>
                    <div class="row" ><form class="" role="form">
                        <table class="table table-condensed table-striped table-hover table-sm">
                            <thead>
                            <tr>
                                <th colspan="3">待诊患者<span class="badge badge-pill badge-danger">4</span></th>
                                <th style="text-align:right;">
                                    <img src="images/reflush_img.jpg" alt="刷新" style="height: 15px;width: 15px">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="customRadio" name="example1" value="customEx">
                                        <label class="custom-control-label" for="customRadio"></label>
                                    </div>
                                </td>
                                <td>00001</td>
                                <td>哒哒哒</td>
                                <td>35岁</td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="customRadio1" name="example1" value="customEx">
                                        <label class="custom-control-label" for="customRadio1"></label>
                                    </div>
                                </td>
                                <td>00001</td>
                                <td>哒哒哒</td>
                                <td>35岁</td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="customRadio2" name="example1" value="customEx">
                                        <label class="custom-control-label" for="customRadio2"></label>
                                    </div>
                                </td>
                                <td>00001</td>
                                <td>哒哒哒</td>
                                <td>35岁</td>
                            </tr>
                            </tbody>
                        </table>
                        <table class="table table-condensed table-striped table-hover table-sm">
                            <thead>
                            <tr>
                                <th colspan="3">已诊患者<span class="badge badge-pill badge-success">3</span></th>
                                <th style="text-align:right;">
                                    <img src="images/reflush_img.jpg" style="height: 15px;width: 15px" alt="刷新">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="customRadio3" name="example1" value="customEx">
                                        <label class="custom-control-label" for="customRadio3"></label>
                                    </div>
                                </td>
                                <td>00001</td>
                                <td>哒哒哒</td>
                                <td>35岁</td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="customRadio4" name="example1" value="customEx">
                                        <label class="custom-control-label" for="customRadio4"></label>
                                    </div>
                                </td>
                                <td>00001</td>
                                <td>哒哒哒</td>
                                <td>35岁</td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="custom-control custom-radio">
                                        <input type="radio" class="custom-control-input" id="customRadio5" name="example1" value="customEx">
                                        <label class="custom-control-label" for="customRadio5"></label>
                                    </div>
                                </td>
                                <td>00001</td>
                                <td>哒哒哒</td>
                                <td>35岁</td>
                            </tr>
                            </tbody>
                        </table>
                    </form></div>
                </div>
            </div>
        </div>
        <!--主体-->
        <div class="container">
            <!--患者信息-->
            <div class="card"><div class="card-body">
                <div class="row" >
                    <div class="col-md-11 form-inline" >
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" data-toggle="collapse" data-target="#searchCard" id="searchPatientButton">搜索</button>
                            <button type="button" class="btn btn-info">统计</button>
                        </div>
                        &nbsp;&nbsp;
                        <label class="control-label font-weight-bold">就诊状态：</label><span class="text-success font-weight-bold">待诊</span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">病历号：</label><span class="">00000000001</span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">姓名：</label><span class="">哒哒哒</span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">性别：</label><span class="">男</span>&nbsp;&nbsp;
                        <label class="control-label font-weight-bold">年龄：</label><span class="">35岁</span>&nbsp;&nbsp;
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
                                    <form id="medicalRecordInfoForm" role="form">
                                        <div class="row ">
                                            <div class="col-md-7">
                                                <h4 class="font-weight-bold">病历信息</h4>
                                            </div>
                                            <div class="col-md-5 text-right">
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-outline-danger btn-sm">清屏</button>
                                                    <button type="button" class="btn btn-outline-secondary btn-sm">暂存</button>
                                                    <button type="button" class="btn btn-outline-success btn-sm">提交</button>
                                                    <button type="button" class="btn btn-outline-info btn-sm">存为模板</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!--病史输入部分-->
                                        <div class="card">
                                            <div class="card-header font-weight-bold">病史内容</div>
                                            <div class="card-body">
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="chiefComplaint">主诉</label>
                                                    <input type="text" class="form-control col-md-10" id="chiefComplaint">
                                                </div>
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="currentMedicalHistory">现病史</label>
                                                    <textarea class="form-control col-md-10" rows="2" id="currentMedicalHistory"></textarea>
                                                </div>
                                                <div class="form-group form-inline text-center">
                                                    <label class="col-md-2 font-weight-bold" for="currentTreatmentSituation">&nbsp;现病治疗情况</label>
                                                    <textarea class="form-control col-md-10" rows="2" id="currentTreatmentSituation"></textarea>
                                                </div>
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="pastHistory">既往史</label>
                                                    <textarea class="form-control col-md-10" rows="2" id="pastHistory"></textarea>
                                                </div>
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="allergiesHistory">过敏史</label>
                                                    <textarea class="form-control col-md-10" rows="2" id="allergiesHistory"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <!--检查输入部分-->
                                        <div class="card">
                                            <div class="card-header font-weight-bold">检查及结果</div>
                                            <div class="card-body">
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="physicalExamination">体格检查</label>
                                                    <textarea class="form-control col-md-10" rows="2" id="physicalExamination"></textarea>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <!--诊断输入部分-->
                                        <div class="card">
                                            <div class="card-header font-weight-bold">评估诊断</div>
                                            <div class="card-body">
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="physicalExamination">西医诊断</label>
                                                    <table class="table table-condensed table-striped table-hover table-sm table-bordered col-md-10" >
                                                        <thead>
                                                        <tr>
                                                            <th>&nbsp;</th>
                                                            <th>ICD编码</th>
                                                            <th>名称<span class="text-danger">*</span></th>
                                                            <th>主诊</th>
                                                            <th>疑似</th>
                                                            <th>发病日期</th>
                                                            <th>&nbsp;</th>
                                                            <th class="text-center table-primary text-white"><a class="font-weight-bold">+</a></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>00001</td>
                                                            <td>哒哒哒</td>
                                                            <td>35岁</td>
                                                            <td>s</td>
                                                            <td>a</td>
                                                            <td><img src="images/save_icon.jpg" style="height: 20px;width: 20px" alt="保存"></td>
                                                            <td class="text-center table-danger text-white"><a class="font-weight-bold">-</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>00001</td>
                                                            <td>哒哒哒</td>
                                                            <td>35岁</td>
                                                            <td>s</td>
                                                            <td>a</td>
                                                            <td><img src="images/save_icon.jpg" style="height: 15px;width: 15px" alt="保存"></td>
                                                            <td class="text-center table-danger text-white"><a class="font-weight-bold">-</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>00001</td>
                                                            <td>哒哒哒</td>
                                                            <td>35岁</td>
                                                            <td>s</td>
                                                            <td>a</td>
                                                            <td><img src="images/save_icon.jpg" style="height: 15px;width: 15px" alt="保存"></td>
                                                            <td class="text-center table-danger text-white"><a class="font-weight-bold">-</a></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <!--中医诊断输入-->
                                                <div class="form-group form-inline">
                                                    <label class="col-md-2 font-weight-bold" for="physicalExamination">中医诊断</label>
                                                    <table class="table table-condensed table-striped table-hover table-sm table-bordered col-md-10" >
                                                        <thead>
                                                        <tr>
                                                            <th>&nbsp;</th>
                                                            <th>ICD编码</th>
                                                            <th>名称</th>
                                                            <th>主诊</th>
                                                            <th>疑似</th>
                                                            <th>发病日期</th>
                                                            <th>&nbsp;</th>
                                                            <th class="text-center table-primary text-white"><a class="font-weight-bold">+</a></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>00001</td>
                                                            <td>哒哒哒</td>
                                                            <td>35岁</td>
                                                            <td>s</td>
                                                            <td>a</td>
                                                            <td><img src="images/save_icon.jpg" style="height: 15px;width: 15px" alt="保存"></td>
                                                            <td class="text-center table-danger text-white"><a class="font-weight-bold">-</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>00001</td>
                                                            <td>哒哒哒</td>
                                                            <td>35岁</td>
                                                            <td>s</td>
                                                            <td>a</td>
                                                            <td><img src="images/save_icon.jpg" style="height: 15px;width: 15px" alt="保存"></td>
                                                            <td class="text-center table-danger text-white"><a class="font-weight-bold">-</a></td>
                                                        </tr>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>00001</td>
                                                            <td>哒哒哒</td>
                                                            <td>35岁</td>
                                                            <td>s</td>
                                                            <td>a</td>
                                                            <td><img src="images/save_icon.jpg" style="height: 15px;width: 15px" alt="保存"></td>
                                                            <td class="text-center table-danger text-white"><a class="font-weight-bold">-</a></td>
                                                        </tr>
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
                                                    <div class="bg-light" style="border: 1px solid rgba(0, 0, 0, 0.125); border-radius: 0.25rem;padding: 0.75rem 0rem;">
                                                        &nbsp;&nbsp;
                                                        <div class="custom-control custom-radio custom-control-inline">
                                                            <input type="radio" class="custom-control-input active" id="wholeRadio" name="medrecTemplateRadioGroup" checked>
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
                                                        <div class="list-group list-group">
                                                            <a href="#" class="list-group-item list-group-item-action">慢性咽炎模板</a>
                                                            <a href="#" class="list-group-item list-group-item-action">快速诊断</a>
                                                            <a href="#" class="list-group-item list-group-item-action">模板二</a>
                                                            <a href="#" class="list-group-item list-group-item-action">模板三</a>
                                                        </div>
                                                    </div>
                                                    <form id="medrecTemplateForm" role="form">
                                                        <div class="text-right" style="margin-top: 8px">
                                                            <button type="button" class="btn btn-outline-success btn-sm">引用</button>
                                                        </div>
                                                        <!--模板内容-->
                                                        <div class="card">
                                                            <div class="card-header"><strong>模板内容：</strong>快速诊断</div>
                                                            <div class="card-body">
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="chiefComplaintTemplate">主诉：</label>
                                                                    <input type="text" class="form-control " id="chiefComplaintTemplate" readonly="readonly" value="as">
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="currentMedicalHistoryTemplate">现病史：</label>
                                                                    <textarea class="form-control " rows="2" id="currentMedicalHistoryTemplate" readonly="readonly"></textarea>
                                                                </div>
                                                                <div class="form-group ">
                                                                    <label class="font-weight-bold" for="physicalExaminationTemplate">体格检查：</label>
                                                                    <textarea class="form-control " rows="2" id="physicalExaminationTemplate" readonly="readonly"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </form>
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
<script type="text/javascript">
    $(document).ready(function(){
        $("#searchPatientButton").click(function(){
            if($("#searchCard").hasClass("show"))
                alert("a");
            else
                alert("n");
            // $.ajax({
            //     type: "POST",//方法类型
            //     dataType: "json",//预期服务器返回的数据类型
            //     url: "outpatientDoctorWorkstation/searchPatient/1",//url
            //     data:{},
            //     success: function (result) {
            //         alert(result.isSeenList[0].name);
            //         alert(result.notSeenList);
            //     }
            // });
        });
        $("#searchPatientKey").click(function(){
            alert("outpatientDoctorWorkstation/searchPatient/"+$("#searchPatientKey").val());
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "outpatientDoctorWorkstation/searchPatient/"+$("#searchPatientKey").val(),//url
                data:{},
                success: function (result) {
                    console.log(result);//打印服务端返回的数据(调试用)
                    if (result.resultCode == 200) {
                        alert("SUCCESS");
                    };
                }
            });
        });
    });
</script>
</body>
</html>