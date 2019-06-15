var patientList;//保存病人信息列表
var notSeenListNum;//待诊人数，用来取病历信息

//修改时间格式
function getTime(t){
    var _time=new Date(t);
    var   year=_time.getFullYear();//2017
    var   month=(Array(2).join("0") + (_time.getMonth()+1)).slice(-2);
    var   date=(Array(2).join("0") + (_time.getDate())).slice(-2);
    var   hour=(Array(2).join("0") + (_time.getHours())).slice(-2);
    var   minute=(Array(2).join("0") + (_time.getMinutes())).slice(-2);
    return   year+"-"+month+"-"+date+"T"+hour+":"+minute;//2014-01-02T11:42
}

//设置搜索获得的病人信息 num 1待诊 2已
function setPatientList(seenList,listName,num){
    var str="";
    for (var i=0;i<seenList.length;i++) {
        str+="<tr>\n" +
            "<td>\n" +
            "<div class='custom-control custom-radio'>\n" +
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
            '<td>'+diseaseId+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="'+listName+diseaseId+'Radio" name="'+listName+'RadioGroup" value="'+diseaseId+'" '+((diagnosisList[i].isNewMajorDiagnosis==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+listName+diseaseId+'Radio"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox ">\n' +
            '<input type="checkbox" class="custom-control-input" id="'+listName+diseaseId+'Check" name="'+listName+'CheckGroup" value="'+diseaseId+'" '+((diagnosisList[i].isNewSuspect==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+listName+diseaseId+'Check"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td style="padding: 0">\n' +
            '<input type="datetime-local"  name="dateOfOnset'+diseaseId+'" class="form-control" value="'+getTime(diagnosisList[i].dateOfOnset)+'"/>\n' +
            '</td>\n' +
            '<td style="padding: 0"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<td class="text-center" style="padding: 0"><button type="button" class="btn btn-danger" style="width: 100%;height: 100%">-\n' +
            '</button></td>\n' +
            '</tr>';
    }
    $("#diagnosisContextTbody"+num).html(str);
}
//禁用和解开病历信息按钮组的暂存和提交
function disableMedicalInfoBtn(bool){
    $("#medicalInfoBtnGroup").find(".btn-outline-secondary,.btn-outline-success").attr("disabled",bool);
}
//点击表格设置患者信息
$("#searchPatientTbody1,#searchPatientTbody2").on("click","tr",function () {
    var radio=$(this).find("input");
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
                visitStatus=((medicalRecordInfo.status==='3')?"诊毕":"待诊");
                medicalInfoNo=medicalRecordInfo.id;
            }else {
                visitStatus="待诊";
                medicalInfoNo="待创建";
            }
            //变色
            var span=$("#visitStatusSpan");
            if(visitStatus==="诊毕"){
                span.addClass("text-danger");
                span.removeClass("text-success");
                disableMedicalInfoBtn(false);
            }else {
                span.removeClass("text-danger");
                span.addClass("text-success");
                disableMedicalInfoBtn(true);
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
var pages;//总页数
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
            pages=result.pages;
            if(diseaseFlag===1){
                var initPagination = function() {
                    // 创建分页
                    $("#diagnosisPagination").pagination(pages, {
                        num_edge_entries: 1, //边缘页数
                        num_display_entries: 4, //主体页数
                        callback: diseasePageselectCallback
                    });
                }();
            }
        }
    });
}
$("button[data-target='#DiagnosisModal']").click(function () {
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
    if(num==null||num<=0||num>pages||(num%1 !== 0)){
        alert("异常页码");
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
        $("#diagnosisPagination").pagination(pages, {
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
            alert("已选择");
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

//导入诊断结果
$("#DiagnosisModal .modal-footer :button").click(function () {
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
                alert($(this).children().eq(2).html()+"重复");
                return false;//break
            }
        });
        if(flag===1)
            return true;//continue
        resultNode.append('<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+$(this).children().eq(2).html()+'</td>\n' +
            '<td title="'+$(this).children().eq(3).html()+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+$(this).children().eq(3).html()+'</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="'+listName+diseaseId+'Radio" name="'+listName+'RadioGroup" value="'+diseaseId+'">\n' +
            '<label class="custom-control-label" for="'+listName+diseaseId+'Radio"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox ">\n' +
            '<input type="checkbox" class="custom-control-input" id="'+listName+diseaseId+'Check" name="'+listName+'CheckGroup" value="'+diseaseId+'">\n' +
            '<label class="custom-control-label" for="'+listName+diseaseId+'Check"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td style="padding: 0">\n' +
            '<input type="datetime-local"  class="form-control" />\n' +
            '</td>\n' +
            '<td style="padding: 0"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<td class="text-center" style="padding: 0"><button type="button" class="btn btn-danger" style="width: 100%;height: 100%">-\n' +
            '</button></td>\n' +
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
    debugger;
    var data=$("#medicalRecordInfoForm").serializeArray();
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "../medicalRecordHome/test",
        data: data,
        success: function (result) {
            alert(result);
        }
    });
});