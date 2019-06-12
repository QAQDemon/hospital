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
        async:false,//同步
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
$("#searchPatientButton").click(function(){
    //搜索按钮，生效条件
    if(!$("#searchCard").hasClass("show"))
        searchPatientAjax();
    else
        clearPatientList();
});
$("#patientSearchCategory1,#patientSearchCategory2,[alt='刷新']").click(function () {
    if($(this).is("a")){//切换active
        $("#patientSearchCategory1,#patientSearchCategory2").toggleClass("active");
    }
    searchPatientAjax();
    return false;
});
$("#searchPatientKey").keyup(function(e){
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
        clearPatientList();
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
        str+='<tr>\n' +
            '<td>'+(i+1)+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td>'+diseaseList[i].diseasename+'</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="'+listName+i+'Radio" name="'+listName+'RadioGroup" '+((diagnosisList[i].isNewMajorDiagnosis==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+listName+i+'Radio"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox ">\n' +
            '<input type="checkbox" class="custom-control-input" id="'+listName+i+'Check" name="'+listName+'CheckGroup" '+((diagnosisList[i].isNewSuspect==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+listName+i+'Check"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td><label>\n' +
            '<input type="datetime-local"  class="form-control" value="'+getTime(diagnosisList[i].dateOfOnset)+'"/>\n' +
            '</label></td>\n' +
            '<td style="height: 25px;width:35px"><a href="#"><img src="images/save_icon.jpg" style="height: 100%;width: 100%" alt="保存"></a> </td>\n' +
            '<td class="text-center table-danger text-white"><a href="#" class="text-white font-weight-bold">-</a></td>\n' +
            '</tr>';
    }
    $("#diagnosisContextTbody"+num).html(str);
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
//诊断删
$("#diagnosisContextTbody1,#diagnosisContextTbody2").on("click","a:contains('-')",function () {
    var tbodyNode=$(this).parent().parent().parent();
    $(this).parent().parent().replaceWith("");
    //更新序号
    tbodyNode.children().each(function (index) {
        $(this).find("td").first().html(index+1);
    });
    return false;
});