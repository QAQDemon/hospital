$.outpatientMethod={
    showAlertDiv:function (alertFlag,color,caption,text) {//弹出信息提示框,返回值可以用来删除
        var num=alertFlag+1100;//z-index
        $("body").prepend(
            '<div id="alertDiv'+num+'" class="alert alert-dismissible fade show '+color+'" style="position:fixed;z-index: '+num+';width: 100%;">\n' +
            '    <button type="button" class="close" data-dismiss="alert">&times;</button>\n' +
            '    <strong>'+alertFlag+'.'+caption+'</strong><span>'+text+'</span>\n' +
            '</div>');
        return num;
    },
    closeAlertDiv:function (alertNum) {//关闭提示
        $("#alertDiv"+alertNum+" :button").click();
    }
};

var alertFlag=0;
function showAlertDiv1(color,caption,text){
    alertFlag++;
    return $.outpatientMethod.showAlertDiv(alertFlag,color,caption,text);
}
//没有选择病单和提交病历不能跳转到其他界面
$("#allNavTab a[data-toggle='tab']:gt(0)").click(function () {
    var medicalRecordInfoId=$("#patientInfoDiv span:eq(1)").html();
    if (medicalRecordInfoId === "") {
        showAlertDiv1("alert-danger","错误!","未选择病单。");
        $("#applyForBtnGroup button:lt(4)").hide();
        $("#prescriptionBtnGroup button:lt(4)").hide();
        return;
    }
    var status=$("#patientInfoDiv span:eq(0)").html();
    if(status==="待诊"){
        showAlertDiv1("alert-danger","错误!","请先提交病历信息。");
        $("#applyForBtnGroup button:lt(4)").hide();
        $("#prescriptionBtnGroup button:lt(4)").hide();
    }
    $("#applyForBtnGroup button:lt(4)").show();
    $("#prescriptionBtnGroup button:lt(4)").show();
});

var patientList;//保存病人信息列表
var notSeenListNum;//待诊人数，用来取病历信息

disableMedrTempBtn(true);//锁定模板按钮
$("#medrecTempCategoryDiv").hide();
disableMedreTempContext(true);

//评估诊断重复部分
function duplicatDiagnosis(diseaseId,listName,lastNum) {
    return  '<td>\n' +
        '<div class="custom-control custom-radio">\n' +
        '<input type="radio" class="custom-control-input" id="'+listName+diseaseId+'Radio" name="isNewMajorDiagnosisCheckded" value="'+diseaseId+'">\n' +
        '<label class="custom-control-label" for="'+listName+diseaseId+'Radio"></label>\n' +
        '</div>\n' +
        '</td>\n' +
        '<td>\n' +
        '<div class="custom-control custom-checkbox ">\n' +
        '<input type="checkbox" class="custom-control-input" id="'+listName+diseaseId+'Check" name="isNewSuspectChecked" value="'+diseaseId+'">\n' +
        '<label class="custom-control-label" for="'+listName+diseaseId+'Check"></label>\n' +
        '</div>\n' +
        '</td>\n' +
        '<td style="padding: 0">\n' +
        '<input type="datetime-local"  class="form-control" />\n' +
        '<input type="hidden" name="'+listName+'['+lastNum+'].dateOfOnset"/>\n'+
        '</td>\n' +
        '<td style="padding: 0" align="center"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
        '<td class="text-center" style="padding: 0"><button type="button" class="btn btn-danger" style="width: 100%;height: 100%">-\n' +
        '</button></td>\n' +
        '</tr>';
}

//修改时间格式
function getTime(t){
    if(t===null)
        return "";
    var _time=new Date(t);
    var   year=_time.getFullYear();//2017
    var   month=(Array(2).join("0") + (_time.getMonth()+1)).slice(-2);
    var   date=(Array(2).join("0") + (_time.getDate())).slice(-2);
    var   hour=(Array(2).join("0") + (_time.getHours())).slice(-2);
    var   minute=(Array(2).join("0") + (_time.getMinutes())).slice(-2);
    return   year+"-"+month+"-"+date+"T"+hour+":"+minute;//2014-01-02T11:42
}

//获取[]中的值
function getInnerNum(str) {
    var reg = /\[(.+?)]/g;
    var lastNum = str.match(reg)[0];
    return  eval(lastNum.substring(1,lastNum.length-1));
}

//设置搜索获得的病人信息 num 1待诊 2已
function setPatientList(seenList,listName,num){
    var str="";
    for (var i=0;i<seenList.length;i++) {
        str+="<tr>\n" +
            "<td>\n" +
            "<div class='custom-control custom-radio'>\n" +
            "<input type='hidden' value='"+seenList[i].id+"' >\n"+
            "<input type='radio' class='custom-control-input' id='customRadio"+listName+i+"' name='seenList' value='"+seenList[i].medicalRecordNo+"'>\n" +
            "<label class='custom-control-label' for='customRadio"+listName+i+"'></label>\n" +
            "</div>\n" +
            "</td>\n" +
            "<td>"+seenList[i].medicalRecordNo+"</td>\n" +
            "<td>"+seenList[i].name+"</td>\n" +
            "<td>"+seenList[i].age+"岁</td>\n" +
            "</tr>";
    }
    $("#searchPatientTbody"+num).html(str);
}
//根据类别和关键词修改url
function searchPatientAjax(){
    var urlS="outpatientDoctorWorkstation/searchPatient/";
    var keyVal=$("#searchPatientKey").val();
    urlS+=($("#patientSearchCategory1").hasClass("active"))?1:2;
    urlS+=(keyVal==="")?"":("/"+keyVal);
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: urlS,
        data: {},
        success: function (result) {
            var isSeenList = result.isSeenList;
            var notSeenList = result.notSeenList;
            patientList=notSeenList.concat(isSeenList);
            notSeenListNum=notSeenList.length;
            setPatientList(isSeenList, "isSeenList", 2);
            setPatientList(notSeenList, "notSeenList", 1);
            $("#notSeenNumSpan").html(notSeenList.length);
            $("#isSeenNumSpan").html(isSeenList.length);
        }
    });
}
//清空患者搜索
function clearPatientList(){
    $("#searchPatientKey").val("");
    $("#searchPatientTbody1,#searchPatientTbody2").html("");
    $("#notSeenNumSpan,#isSeenNumSpan").html(0);
}
// $("#searchPatientButton").click(function(){
//     //搜索按钮，生效条件
//     if(!$("#searchCard").hasClass("show"))
//         searchPatientAjax();
// });
$("#patientSearchCategory1,#patientSearchCategory2,[alt='刷新']").click(function () {
    if($(this).is("a")){//切换active
        $("#patientSearchCategory1,#patientSearchCategory2").toggleClass("active");
    }
    searchPatientAjax();
    return false;
});
$("#searchPatientForm").on("click","button",function () {
    searchPatientAjax();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchPatientAjax();
    }
});
$("*").click(function (event) {
    var e = event || window.event; //浏览器兼容性
    var elem = e.target || e.srcElement;
    while (elem) { //循环判断至跟节点，防止点击的是div子元素
        if (elem.id && elem.id==='searchCard') {
            return;
        }
        elem = elem.parentNode;
    }
    var div=$("#searchCard");
    if(div.hasClass("show")){
        div.removeClass("show");
    }
});

//清空病历信息
function clearMedicalInfoContext(){
    $("#chiefComplaint").val("");
    $("#medicalInfoCard1,#medicalInfoCard2").find("textarea").each(function (index) {
        $(this).html("");
    });
    $("#diagnosisContextTbody1,#diagnosisContextTbody2").html("");
}
//放入评估诊断
function setDiagnosisList(diagnosisList,diseaseList,listName,num){
    var str="";
    for (var i=0;i<diagnosisList.length;i++) {
        var diseaseId=diseaseList[i].id;
        str+='<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'<input type="hidden" name="'+listName+'['+(i+1)+'].diseaseId" value="'+diseaseId+'"></td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="'+listName+diseaseId+'Radio" name="isNewMajorDiagnosisCheckded" value="'+diseaseId+'" '+((diagnosisList[i].isNewMajorDiagnosis==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+listName+diseaseId+'Radio"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox ">\n' +
            '<input type="checkbox" class="custom-control-input" id="'+listName+diseaseId+'Check" name="isNewSuspectChecked" value="'+diseaseId+'" '+((diagnosisList[i].isNewSuspect==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+listName+diseaseId+'Check"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td style="padding: 0">\n' +
            '<input type="datetime-local"  class="form-control" value="'+getTime(diagnosisList[i].dateOfOnset)+'"/>\n' +
            '<input type="hidden" name="'+listName+'['+(i+1)+'].dateOfOnset"/>\n'+
            '</td>\n' +
            '<td style="padding: 0" align="center"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<td class="text-center" style="padding: 0"><button type="button" class="btn btn-danger" style="width: 100%;height: 100%">-\n' +
            '</button></td>\n' +
            '</tr>';
    }
    $("#diagnosisContextTbody"+num).html(str);
}
//禁用和解开病历信息按钮组的暂存和提交true 不可用
function disableMedicalInfoBtn(bool){
    var btns= $("#medicalInfoBtnGroup").find(".btn-outline-secondary,.btn-outline-success");
    if(bool===true)
        btns.hide();
    else  btns.show();
}
//切换患者将右侧功能重置
function resetHomeRight(){
    $("#homeRightNav a:eq(0)").click();
    $("#medrecTempChooseDiv [type='radio']:eq(0)").click();
    clearMedrecTemplateContent();
}
//点击表格设置患者信息
$("#searchPatientTbody1,#searchPatientTbody2").on("click","tr",function () {
    $("[href='#home1']").click();
    resetHomeRight();
    var radio=$(this).find("input[type='radio']");
    radio.prop('checked','true');
    var no=parseInt(radio.val());
    var patient;
    var patientListNum;
    for (var i=0; i < patientList.length; i++) {
        if(patientList[i].medicalRecordNo===no){
            patient=patientList[i];//保存到病人信息
            patientListNum=i;
            break;
        }
    }
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getMedicalRecordInfo/"+((patientListNum<notSeenListNum)?"1":"2")+"/"+no,
        data: {},
        success: function (result) {
            var visitStatus;//就诊状态
            var medicalInfoNo;//病单号
            var medicalRecordInfo;
            var zDiagnosisList;
            var xDiagnosisList;
            var zDiagnosisDiseaseList;
            var xDiagnosisDiseaseList;
            if(result.mes!=='2'){
                medicalRecordInfo=result.medicalRecordInfo;
                zDiagnosisList=result.zDiagnosisList;
                xDiagnosisList=result.xDiagnosisList;
                zDiagnosisDiseaseList=result.zDiagnosisDiseaseList;
                xDiagnosisDiseaseList=result.xDiagnosisDiseaseList;
                medicalInfoNo=medicalRecordInfo.id;
                if(medicalRecordInfo.status==='3')
                    visitStatus="诊毕";
                else if(medicalRecordInfo.status==='2')
                    visitStatus="初诊完成";
                else visitStatus="待诊";
                if(visitStatus==='待诊')
                    disableMedicalInfoBtn(false);
                else disableMedicalInfoBtn(true);
            }else {
                visitStatus="待诊";
                medicalInfoNo="待创建";
                disableMedicalInfoBtn(false);
            }
            //变色
            var span=$("#visitStatusSpan");
            if(visitStatus==="诊毕"){
                span.addClass("text-danger");
                span.removeClass("text-success");
            }else {
                span.removeClass("text-danger");
                span.addClass("text-success");
            }
            var content=[visitStatus,medicalInfoNo,patient.name,((patient.gender==='1')?"男":"女"),patient.age+"岁"];
            //设置数据
            $("#patientInfoDiv span").each(function (index) {
                $(this).html(content[index]);
            });
            clearMedicalInfoContext();
            if(medicalInfoNo==="待创建")
                return;
            $("#chiefComplaint").val(medicalRecordInfo.chiefComplaint);
            content=[medicalRecordInfo.currentMedicalHistory,medicalRecordInfo.currentTreatmentSituation,
                medicalRecordInfo.pastHistory,medicalRecordInfo.allergiesHistory,medicalRecordInfo.physicalExamination];
            $("#medicalInfoCard1,#medicalInfoCard2").find("textarea").each(function (index) {
                $(this).html(content[index]);
            });
            setDiagnosisList(xDiagnosisList,xDiagnosisDiseaseList,"xDiagnosisList",1);
            setDiagnosisList(zDiagnosisList,zDiagnosisDiseaseList,"zDiagnosisList",2);
        }
    });
});
//诊断 删
$("#diagnosisContextTbody1,#diagnosisContextTbody2").on("click","button:contains('-')",function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        // var tbodyNode=$(this).closest("tbody");
        $(this).closest("tr").replaceWith("");
        //更新序号
        // tbodyNode.find("tr").each(function (index) {
        //     $(this).find("td").first().html(index+1);
        // });
    }
    return false;
});
//诊断 搜索
var diseaseList;//疾病信息列
var diseaseFlag;//flag 1创建 2不创建页码并跳过
var diseasePages;//总页数
function addDisease() {
    var str="";
    for(var i=0;i<diseaseList.length;i++){
        str+='<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox" >\n' +
            '<input type="checkbox" class="custom-control-input" id="diagnosisNotCheck'+i+'" name="diagnosisNotCheckGroup">\n' +
            '<label class="custom-control-label" for="\'+listName+i+\'Check1"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+diseaseList[i].id+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:260px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '</tr>';
    }
    $("#diagnosisNotCheckedTbody").html(str);
}
function diseasePageselectCallback(page_index,jq){
    if(!(diseaseFlag===1&&page_index===0)){
        diseaseFlag=2;
        searchDiagnosisAjax(page_index+1);
    }
    addDisease();
    return false;
}
function searchDiagnosisAjax(pageNum){
    var urlS="medicalRecordHome/searchDiagnosis/"+(($("#DiagnosisModal").find("h4").html()==="西医诊断")?"1":"2")+"/"+pageNum;
    var inputKey=$("#diagnosisKey").val();
    if(inputKey!=="")
        urlS += ("/" + inputKey);
    $.ajax({
        type: "POST",//方法类型
        async:false,
        dataType: "json",//预期服务器返回的数据类型
        url: urlS,
        data: {},
        success: function (result) {
            diseaseList=result.diseaseList;
            diseasePages=result.pages;
            if(diseaseFlag===1){
                var initPagination = function() {
                    // 创建分页
                    $("#diagnosisPagination").pagination(diseasePages, {
                        num_edge_entries: 1, //边缘页数
                        num_display_entries: 4, //主体页数
                        callback: diseasePageselectCallback
                    });
                }();
            }
        }
    });
}
$("button[data-target='#DiagnosisModal']:contains('+')").click(function () {
    var node=$("#DiagnosisModal");
    if($(this).closest("table").prev().html()==="西医诊断")
        node.find("h4").html("西医诊断");
    else
        node.find("h4").html("中医诊断");
    node.find("tbody").html("");//清空表格
    node.find("#diagnosisKey").val("");
    $("#diagnosisPagination").html("");
    $("#diseasePageJump").hide();
    $("#searchDiagnosisPage").val("");
    $("#DiagnosisModal button:contains('导入结果')").show();
    $("#DiagnosisModal button:contains('保存')").hide();
});
$("button[data-target='#DiagnosisModal']:contains('修改')").click(function () {
    var node=$("#DiagnosisModal");
    if($(this).prev().html()==="西医诊断")
        node.find("h4").html("西医诊断");
    else
        node.find("h4").html("中医诊断");
    node.find("tbody").html("");//清空表格
    node.find("#diagnosisKey").val("");
    $("#diagnosisPagination").html("");
    $("#diseasePageJump").hide();
    $("#searchDiagnosisPage").val("");
    $("#DiagnosisModal button:contains('导入结果')").hide();
    $("#DiagnosisModal button:contains('保存')").show();
    //放入要修改的内容
    var resultNode=$("#diagnosisCheckedTbody");
    $(this).next().find('tbody').children().each(function (index) {
        resultNode.append('<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox" >\n' +
            '<input type="checkbox" class="custom-control-input" id="diagnosisNotCheck'+index+'" name="diagnosisNotCheckGroup" checked>\n' +
            '<label class="custom-control-label" for="diagnosisNotCheck'+index+'"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+$(this).find("td:eq('0')").html()+'</td>\n' +
            '<td>'+$(this).find("td:eq('1')").html()+'</td>\n' +
            '<td title="'+$(this).find("td:eq('2')").html()+'" style="max-width:260px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+$(this).find("td:eq('2')").html()+'</td>\n' +
            '</tr>');
    });
});
$("#DiagnosisModal").on("click",":checkbox",function () {
    return false;//防止二次点击
});
function searchDiagMethod(){
    diseaseFlag=1;
    searchDiagnosisAjax(1);
    $("#diseasePageJump").show();
}
$("#diagnosisSearchInput").on("click","button",function () {
    searchDiagMethod();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchDiagMethod();
    }
});

//跳转
function diseasePageJumpMethod(){
    var num=$("#searchDiagnosisPage").val();
    if(num==null||num<=0||num>diseasePages||(num%1 !== 0)){
        showAlertDiv1("alert-danger","错误!","异常页码。");
        return;
    }
    var current=$("#diagnosisPagination .current").html();
    if(current===num)//可排除第一次跳第一页
        return;
    diseaseFlag=2;
    searchDiagnosisAjax(num);
    $("#diagnosisPagination").html("");
    var initPagination = function() {
        // 更改分页
        $("#diagnosisPagination").pagination(diseasePages, {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            callback: diseasePageselectCallback,
            current_page:num-1
        });
    }();
    addDisease();
}
$("#diseasePageJump").on("click","button",function () {
    diseasePageJumpMethod();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        diseasePageJumpMethod();
    }
});

//诊断 搜索增加
$("#diagnosisNotCheckedTbody").on("click","tr",function () {
    var id=$(this).children().eq(1).html();
    var oneflag=0;
    var node=$("#diagnosisCheckedTbody");
    node.children().each(function () {
        if($(this).children().eq(1).html()===id){
            showAlertDiv1("alert-warning","警告!","已选择。");
            oneflag=1;
            return false;
        }
    });
    if(oneflag===1)
        return;
    node.append("<tr>"+$(this).html()+"</tr>")
        .find("input").last().attr("checked",'true');
});
//诊断 搜索删除
$("#diagnosisCheckedTbody").on("click","tr",function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        $(this).remove();
    }
});

//获得诊断最后一位序号
function getDiagnosisLast(tbody){
    var hiddenName=tbody.find("[type='hidden']:last").attr("name");
    var lastNum;
    if (hiddenName === undefined)
        lastNum=1;
    else
        lastNum=getInnerNum(hiddenName)+1;
    return lastNum;
}
//导入诊断结果
$("#DiagnosisModal .modal-footer :button:contains('导入结果')").click(function () {
    var resultNode;
    var listName;
    if($("#DiagnosisModal h4").html()==="西医诊断"){
        listName='xDiagnosisList';
        resultNode=$("#diagnosisContextTbody1");
    } else{
        resultNode=$("#diagnosisContextTbody2");
        listName='zDiagnosisList';
    }
    $("#diagnosisCheckedTbody tr").each(function () {
        var flag=0;
        var diseaseId=$(this).children().eq(1).html();
        resultNode.children().each(function () {
            if($(this).children().eq(0).html()===diseaseId){
                flag=1;
                showAlertDiv1("alert-warning","警告!",$(this).children().eq(2).html()+"重复");
                return false;//break
            }
        });
        if(flag===1)
            return true;//continue
        var lastNum = getDiagnosisLast(resultNode);
        resultNode.append('<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+$(this).children().eq(2).html()+'<input type="hidden" name="'+listName+'['+lastNum+'].diseaseId" value="'+diseaseId+'"></td>\n' +
            '<td title="'+$(this).children().eq(3).html()+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+$(this).children().eq(3).html()+'</td>\n' +
            duplicatDiagnosis(diseaseId,listName,lastNum));
    });
    $("#DiagnosisModal button[data-dismiss='modal']").click();
});
//保存到模板
$("#DiagnosisModal .modal-footer :button:contains('保存')").click(function () {//todo
    var resultNode;
    var num;
    if($("#DiagnosisModal h4").html()==="西医诊断"){
        num='0';
        resultNode=$("#medrecTempContextForm tbody:eq(0)");
    } else{
        resultNode=$("#medrecTempContextForm tbody:eq(1)");
        num='1';
    }
    resultNode.html("");
    $("#diagnosisCheckedTbody tr").each(function () {
        var diseaseId=$(this).children().eq(1).html();
        resultNode.append('<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+$(this).children().eq(2).html()+'</td>\n' +
            '<td title="'+$(this).children().eq(3).html()+'" style="max-width:80px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+$(this).children().eq(3).html()+'</td>\n' +
            '<input type="hidden" name="diseaseId'+num+'" value="'+diseaseId+'">\n'+
            '</tr>');
    });
    $("#DiagnosisModal button[data-dismiss='modal']").click();
});

//表单功能按键
//清屏
$("#medicalInfoBtnGroup :first").click(function () {
    clearMedicalInfoContext();
});
//暂存或提交
$("#medicalInfoBtnGroup").find(".btn-outline-secondary,.btn-outline-success").click(function () {
    //判断表单完整性
    var formFlag=0;
    if(($("#medicalRecordInfoForm [type='text']").val()==="")||($("#medicalRecordInfoForm textarea").html()==="")||(($("#diagnosisContextTbody1").html()==="")&&($("#diagnosisContextTbody2").html()===""))){
        showAlertDiv1("alert-warning","警告!","表单不完整");
        return;
    }
    //转换hidden时间
    $("#diagnosisContentCard").find("[type='datetime-local']").each(function () {
        var dateStr=$(this).val();
        if(dateStr!=="")
            $(this).next().val(dateStr.replace("T"," "));
    });
    var infoId=$("#patientInfoDiv span:eq(1)").html();
    var alertNum=showAlertDiv1("alert-secondary","","病历信息保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "medicalRecordHome/saveMedicalRecordInfo/"+($(this).hasClass("btn-outline-secondary")?"1":"2")+"/"+$("#patientListForm :checked").val()+"/"+((infoId==="待创建")?"-1":(infoId))+"/"+$("#patientListForm [type='hidden']").val(),
        data: $("#medicalRecordInfoForm").serializeArray(),
        success: function (result) {
            $.outpatientMethod.closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv1("alert-success","成功!","病历信息保存成功。");
                //提交成功则暂存提交不可用
                $("#patientListForm :checked").click();
            }
            else showAlertDiv1("alert-warning","警告!","病历信息保存失败。");
        }
    });
});
//存为模板
$("#medicalInfoBtnGroup :eq(3)").click(function () {
    $("#homeRightNav a:first").click();
    $("#medreTempBtnGroup :eq(0)").click();
    $("#chiefComplaintTemplate").val($("#chiefComplaint").val());
    $("#currentMedicalHistoryTemplate").html($("#currentMedicalHistory").html());
    $("#physicalExaminationTemplate").html($("#physicalExamination").html());
    var xList=[];
    $("#diagnosisContextTbody1 tr").each(function (index) {
        var str=$(this).find("td:eq(1)").html();
        str = str.split("<")[0];
       xList.push({id:$(this).find("td:eq(0)").html(),diseaseicd:str, diseasename: $(this).find("td:eq(2)").html()});
    });
    var zList=[];
    $("#diagnosisContextTbody2 tr").each(function (index) {
        var str=$(this).find("td:eq(1)").html();
        str = str.split("<")[0];
        zList.push({id:$(this).find("td:eq(0)").html(),diseaseicd:str, diseasename: $(this).find("td:eq(2)").html()});
    });
    setDiseaseTempleteList(xList,0);
    setDiseaseTempleteList(zList,1);
});

//放入病历模板的名字到标签
function addmedrecTempContext(map){
    var str="";
    for(var key in map){
        str+=' <a href="#" class="list-group-item list-group-item-action">'+map[key]+'</a><input type="hidden" value="'+key+'">';
    }
    $("#MedrecTempListDiv").html(str);
}
//病历模板类型选择
$("#medrecTempChooseDiv :radio").click(function () {
    var key=$("#searchMedrecTempKey").val();
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getMedrecTemplate/"+($(this).parent().index()+1)+((key==="")?"":("/"+key)),
        data: {},
        success: function (result) {
            addmedrecTempContext(result);
        }
    });
});
function searchMedrecTemp(){
    var category=$("#medrecTempChooseDiv :checked").val();
    if(category===undefined){//未选的情况
        category=1;
        $("#medrecTempChooseDiv [type='radio']:eq(0)").attr("checked",true);
    }
    var key=$("#searchMedrecTempKey").val();
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getMedrecTemplate/"+category+((key==="")?"":("/"+key)),
        data: {},
        success: function (result) {
            addmedrecTempContext(result);
        }
    });
}
//病历模板搜索
$("#searchMedrecTempForm").on("click","button",function () {
    searchMedrecTemp();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchMedrecTemp();
    }
});
//清空模板内容
function clearMedrecTemplateContent(){
    $("#MedrecTempListDiv a").removeClass("active");
    $("#idTemplate").val("");
    $("#categoryTemplate").val(0);
    $("#MedrecTempContextDiv [type='text']").val("");
    $("#MedrecTempContextDiv").find("textarea,tbody").html("");
}
//放入模板疾病 0,1
function setDiseaseTempleteList(diseaseList,num){
    var str="";
    for (var i=0;i<diseaseList.length;i++) {
        var diseaseId=diseaseList[i].id;
        str+='<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:80px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '<input type="hidden" name="diseaseId'+num+'" value="'+diseaseId+'">\n'+
            '</tr>';
    }
    $("#MedrecTempContextDiv tbody:eq("+num+")").html(str);
}
//解锁或锁定模板按钮 true锁定
function disableMedrTempBtn(bool){
    var btns=$("#medreTempBtnGroup").find(".btn-outline-warning,.btn-outline-danger");
    if(bool===true)
        btns.hide();
    else  btns.show();
}
//点击标签获得模板内容
$("#MedrecTempListDiv").on("click","a",function () {
    //变色
    $("#MedrecTempListDiv a").removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getMedrecTemplateContent/"+$(this).next().val(),
        data: {},
        success: function (result) {
            $("#templateCodeTemplate").attr("readonly",true);
            $("#medrecTempCategoryDiv").hide();
            disableMedreTempContext(true);
            if(result.medrecTemplate===null){
                showAlertDiv1("alert-warning","警告!","模板不存在。");
                return;
            }
            var medrecTemplate=result.medrecTemplate;
            if(eval($("#doctorId").val())===medrecTemplate.createrId)//判断是否能修改删除
                disableMedrTempBtn(false);
            else disableMedrTempBtn(true);
            $("#idTemplate").val(medrecTemplate.id);
            $("#categoryTemplate").val(eval(medrecTemplate.category));
            $("#templateCodeTemplate").val(medrecTemplate.templateCode);
            $("#templateNameTemplate").val(medrecTemplate.templateName);
            $("#chiefComplaintTemplate").val(medrecTemplate.chiefComplaint);
            $("#currentMedicalHistoryTemplate").html(medrecTemplate.currentMedicalHistory);
            $("#physicalExaminationTemplate").html(medrecTemplate.physicalExamination);
            setDiseaseTempleteList(result.xDiagnosisDiseaseList,0);
            setDiseaseTempleteList(result.zDiagnosisDiseaseList,1);
        }
    });
    return false;
});
//模板诊断插入评估 0x 1z
function setDiagnosisTemplete(listName,num){
    var resultNode=$("#diagnosisContentCard tbody:eq("+num+")");
    $("#MedrecTempContextDiv tbody:eq("+num+") tr").each(function () {
        var flag=0;
        var diseaseId=$(this).children().eq(0).html();
        resultNode.children().each(function () {
            if($(this).children().eq(0).html()===diseaseId){
                flag=1;
                return false;//break
            }
        });
        if(flag===1)
            return true;//continue
        var lastNum = getDiagnosisLast(resultNode);
        resultNode.append('<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+$(this).children().eq(1).html()+'<input type="hidden" name="'+listName+'['+lastNum+'].diseaseId" value="'+diseaseId+'"></td>\n' +
            '<td title="'+$(this).children().eq(2).html()+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+$(this).children().eq(2).html()+'</td>\n' +
            duplicatDiagnosis(diseaseId,listName,lastNum));
    });
}
//引用模板
$("#MedrecTempContextDiv button:contains('引用')").click(function () {
    var chiefComplaintText=$("#chiefComplaintTemplate").val();
    var  currentMedicalHistoryText=$("#currentMedicalHistoryTemplate").html();
    var physicalExaminationTemplateText=$("#physicalExaminationTemplate").html();
    if(chiefComplaintText!=="")
        $("#chiefComplaint").val(chiefComplaintText);
    if(currentMedicalHistoryText!=="")
        $("#currentMedicalHistory").html(currentMedicalHistoryText);
    if(physicalExaminationTemplateText!=="")
        $("#physicalExamination").html(physicalExaminationTemplateText);
    setDiagnosisTemplete("xDiagnosisList",0);
    setDiagnosisTemplete("zDiagnosisList",1);
});
//删除病历模板
$("#medreTempBtnGroup button:eq(2)").click(function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        if($("#idTemplate").val()!==""){
            $.ajax({
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "medicalRecordHome/cancelMedrecTemplate/"+$("#idTemplate").val(),
                data: {},
                success: function (result) {
                    if(result==="0")//好像无效
                        showAlertDiv1("alert-danger","错误!","删除病历模板失败。");
                    $("#searchMedrecTempForm button").click();
                }
            });
        }else showAlertDiv1("alert-danger","错误!","删除病历模板失败，请重新选择。");
        clearMedrecTemplateContent();
        disableMedrTempBtn(true);
        disableMedreTempContext(true);
    }
});
//锁定病历模板内容或解开 bool true锁
function disableMedreTempContext(bool){
    $("#chiefComplaintTemplate,#templateNameTemplate,#currentMedicalHistoryTemplate,#physicalExaminationTemplate").attr("readonly",bool);
    var btns=$("#medrecTempContextForm button");
    if(bool)
        btns.hide();
    else btns.show();
}
// 修改增加病历模板后提交
$("#medrecTempContextForm button:contains('提交')").click(function () {
    if($("#templateCodeTemplate").val()===""||$("#categoryTemplate").val()==="0"){
        showAlertDiv1("alert-warning", "警告!", "模板编码和适用范围不能为空。");
        $("#templateCodeTemplate").focus();
       return;
    }
    var alertNum=showAlertDiv1("alert-secondary","","病历模板保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "medicalRecordHome/saveMedrecTemplate/"+(($("#templateCodeTemplate").attr("readonly")==="readonly")?"2":"1"),
        data: $("#medrecTempContextForm").serializeArray(),
        success: function (result) {//1成功 0更新失败（已删除） 2新增失败（code已存在）
            $.outpatientMethod.closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv1("alert-success","成功!","病历模板提交成功。");
                $("#medrecTempCategoryDiv").hide();
                $("#templateCodeTemplate").attr("readonly",true);
                disableMedrTempBtn(false);
                disableMedreTempContext(true);
            }
            else if(result==="2") {
                showAlertDiv1("alert-warning", "警告!", "当前分类下病历模板编码已存在。");
                $("#templateCodeTemplate").focus();
            }
            else {
                showAlertDiv1("alert-warning", "警告!", "当前分类下病历模板不存在。");
            }
        }
    });
});
//修改病历模板
$("#medreTempBtnGroup").on("click","button:eq(1)",function () {
    $("#templateCodeTemplate").attr("readonly",true);
    disableMedreTempContext(false);
}).on("click","button:eq(0)",function () {//增加病历模板
    $("#medrecTempCategoryDiv").show();
    $("#templateCodeTemplate").attr("readonly",false);
    disableMedreTempContext(false);
    clearMedrecTemplateContent();
    disableMedrTempBtn(true);
});

//放入常用诊断
function insertCommonDisease(list,num){
    var str="";
    for (var i = 0; i < list.length; i++) {
        str+='<a href="#" class="list-group-item list-group-item-action"><span>'+list[i].diseasename+'</span><span class="badge badge-pill badge-danger">X</span></a>\n' +
            '<input type="hidden" value="'+list[i].id+'">'+
            '<input type="hidden" value="'+list[i].diseaseicd+'">';
    }
    $("#home1_2 .list-group:eq("+num+")").html(str);
}
//常用诊断生成
$("#homeRightNav a:eq(1)").click(function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getCommonOption",
        data: {},
        success: function (result) {
            insertCommonDisease(result.xDiseaseCommonOptionList,0);
            insertCommonDisease(result.zDiseaseCommonOptionList,1);
        }
    });
});

//双击将常用诊断加到评估下
$("#home1_2").on("dblclick","a",function () {
    var num=$(this).closest(".card").index()-1;//0x 1z
    var listName = (num === 0) ? "xDiagnosisList" : "zDiagnosisList";
    var resultNode=$("#diagnosisContentCard tbody:eq("+num+")");
    var flag=0;
    var diseaseId=$(this).next().val();
    resultNode.children().each(function () {
        if($(this).children().eq(0).html()===diseaseId){
            flag=1;
            return false;//break
        }
    });
    if(flag===1){
        showAlertDiv1("alert-warning","警告!","该诊断已存在。");
        return;//重复跳出
    }
    var lastNum = getDiagnosisLast(resultNode);
    resultNode.append('<tr>\n' +
        '<td>'+(diseaseId)+'</td>\n' +
        '<td>'+$(this).next().next().val()+'<input type="hidden" name="'+listName+'['+lastNum+'].diseaseId" value="'+diseaseId+'"></td>\n' +
        '<td title="'+$(this).children().first().html()+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+$(this).children().first().html()+'</td>\n' +
        duplicatDiagnosis(diseaseId,listName,lastNum));
    showAlertDiv1("alert-success","成功!","诊断插入成功。");
})//删除常用诊断
.on("click",".badge-danger",function () {
    var res = confirm('确认要删除吗？');
    if(res === true) {
        $.ajax({
            type: "POST",//方法类型
            dataType: "text",//预期服务器返回的数据类型
            url: "medicalRecordHome/deleteCommonDiagnosis/" + $(this).closest(".card").index() + "/" + $(this).parent().next().val(),
            data: {},
            success: function (result) {
                $("#homeRightNav a:eq(1)").click();
            }
        });
    }
})//取消跳转
.on("click","a",function () {
    return false;
});

//增加常用诊断
$("#diagnosisContentCard").on("click","a",function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "medicalRecordHome/addCommonDiagnosis/"+($(this).closest(".form-group").index()+1)+"/"+$(this).closest("tr").children().first().html(),
        data: {},
        success: function (result) {
            if(result==="1")
                showAlertDiv1("alert-success","成功!","增加常用诊断成功。");
            else showAlertDiv1("alert-danger","失败!","增加常用诊断失败。");
            $("#homeRightNav a:eq(1)").click();
        }
    });
    return false;
});

//放入历史病历标签
function addHistoryMedicalLabel(map){
    var str="";
    for(var key in map){
        var jsonstr=JSON.parse(map[key]);
        str+=' <a href="#" class="list-group-item list-group-item-action">'+jsonstr.time+' '+jsonstr.name+'</a><input type="hidden" value="'+key+'">';
    }
    $("#historyMedicalInfoLabelDiv").html(str);
}
//历史病历查看
$("#homeRightNav a:eq(2)").click(function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getHistoryMedicalRecordInfo/"+$("#patientListForm :checked").val(),
        data: {},
        success: function (result) {
            addHistoryMedicalLabel(result);
        }
    });
});
//清空历史病历
function clearHistoryContext(){
    $("#historyContextDiv span").html("");
}
//获得历史病历的内容
$("#historyMedicalInfoLabelDiv").on("click","a",function () {
    //变色
    $("#historyMedicalInfoLabelDiv a").removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getHistoryMedicalRecordContext/"+$(this).next().val(),
        data: {},
        success: function (result) {
            clearHistoryContext();
            var historyMedicalRecordInfo=result.historyMedicalRecordInfo;
            var finalDiagnosis=result.finalDiagnosis;
            if(historyMedicalRecordInfo===null){
                showAlertDiv1("alert-warning","警告!","历史病单不存在。");
                return;
            }
            var context=[historyMedicalRecordInfo.chiefComplaint,historyMedicalRecordInfo.currentMedicalHistory,
                historyMedicalRecordInfo.currentTreatmentSituation,historyMedicalRecordInfo.pastHistory,historyMedicalRecordInfo.allergiesHistory,
                historyMedicalRecordInfo.physicalExamination,finalDiagnosis];
            $("#historyContextDiv span").each(function (index) {
                $(this).html(context[index]);
            });
        }
    });
    return false;
});

