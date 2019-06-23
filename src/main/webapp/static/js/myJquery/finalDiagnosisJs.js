//放入初诊列表  0x 1z
function setNewDiagnosis(newDiagnosisList,diseaseList,num) {
    var str="";
    for (var i = 0; i < newDiagnosisList.length; i++) {
        str+='<tr>\n' +
            '<td>'+diseaseList[i].id+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:200px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="'+num+i+'Radio" '+((newDiagnosisList[i].isNewMajorDiagnosis==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+num+i+'Radio"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox ">\n' +
            '<input type="checkbox" class="custom-control-input" id="'+num+i+'Check"  '+((newDiagnosisList[i].isNewSuspect==='1')?'':'checked')+'>\n' +
            '<label class="custom-control-label" for="'+num+i+'Check"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+getTime1(newDiagnosisList[i].dateOfOnset)+'</td>\n' +
            '</tr>';
    }
    $("#newDiagnosisContentCard tbody:eq("+num+")").html(str);
}
//取消单选和复选的点击
$("#newDiagnosisContentCard").on("click",":radio,:checkbox",function () {
    return false;
});
//放入终诊列表 0x 1z
function setFinalDiagnosis(diseaseList,num) {
    for (var i = 0; i < diseaseList.length; i++) {
        $("#finalDiagnosisForm tbody:eq("+num+")").append('<tr>\n' +
            '<td>'+diseaseList[i].id+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:200px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '<td style="padding: 0" align="center"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<td class="text-center" style="padding: 0"><button type="button" class="btn btn-danger" style="width: 100%;height: 100%">-\n' +
            '</button></td>\n' +
            '<input type="hidden" name="diagnosis'+num+'" value="'+diseaseList[i].id+'">'+
            '</tr>'
        );
    }
}
function newFinalDiagnosisAjax(){
    var medicalInfoId=$("#patientInfoDiv span:eq(1)").html();
    if(medicalInfoId==="")
        return;
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "outpatientDoctorWorkstation/getNewFinalDiagnosis/"+medicalInfoId,
        data: {},
        success: function (result) {
            var xNewDiagnosisList=result.xNewDiagnosisList;
            var zNewDiagnosisList=result.zNewDiagnosisList;
            var xDiagnosisDiseaseList=result.xDiagnosisDiseaseList;
            var zDiagnosisDiseaseList=result.zDiagnosisDiseaseList;
            var xFinalDiagnosisDiseaseList=result.xFinalDiagnosisDiseaseList;
            var zFinalDiagnosisDiseaseList=result.zFinalDiagnosisDiseaseList;
            setNewDiagnosis(xNewDiagnosisList,xDiagnosisDiseaseList,0);
            setNewDiagnosis(zNewDiagnosisList,zDiagnosisDiseaseList,1);
            if(xFinalDiagnosisDiseaseList.length===0&&zFinalDiagnosisDiseaseList.length===0)
                $("#identifyButton").show();
            else {
                setFinalDiagnosis(xFinalDiagnosisDiseaseList, 0);
                setFinalDiagnosis(zFinalDiagnosisDiseaseList, 1);
            }
        }
    });
}

//初始化确诊界面
$("[href='#menu2']").click(function () {
    $("#comonDiagnosis .list-group").html("");
    $("#commonDiagnosisLink").click();
    $("#identifyButton").hide();//确诊按钮隐藏，如果ajax未确诊会出现
    $("#menu2 tbody").html("");
    newFinalDiagnosisAjax();
});

$("#commonDiagnosisLink").click(function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "medicalRecordHome/getCommonOption",
        data: {},
        success: function (result) {
            $("#comonDiagnosis .list-group:eq(0)").html(insertCommonDisease(result.xDiseaseCommonOptionList,0));
            $("#comonDiagnosis .list-group:eq(1)").html(insertCommonDisease(result.zDiseaseCommonOptionList,1));
        }
    });
    return false;
});
//双击将常用诊断加到评估下
$("#comonDiagnosis").on("dblclick","a",function () {
    var num=$(this).closest(".card").index()-1;//0x 1z
    var resultNode=$("#finalDiagnosisForm tbody:eq("+num+")");
    var flag=0;
    var diseaseId=$(this).next().val();
    resultNode.children().each(function () {
        if($(this).children().eq(0).html()===diseaseId){
            flag=1;
            return false;//break
        }
    });
    if(flag===1){
        showAlertDiv("alert-warning","警告!","该诊断已存在。");
        return;//重复跳出
    }
    setFinalDiagnosis([{id:diseaseId,diseaseicd:$(this).next().next().val(),diseasename:$(this).find("span").html()}],num);
    showAlertDiv("alert-success","成功!","诊断插入成功。");
});

//导入终诊结果
$("#DiagnosisModal .modal-footer :button:contains('导入确诊')").click(function () {
    var num;
    var diseaseList=[];
    if($("#DiagnosisModal h4").html()==="西医诊断")
        num=0;
    else
        num=1;
    $("#diagnosisCheckedTbody tr").each(function () {
        var flag=0;
        var diseaseId=$(this).children().eq(1).html();
        $("#finalDiagnosisForm tbody:eq("+num+") tr").each(function () {
            if($(this).children().eq(0).html()===diseaseId){
                flag=1;
                showAlertDiv("alert-warning","警告!",$(this).children().eq(2).html()+"重复");
                return false;//break
            }
        });
        if(flag===1)
            return true;//continue
        diseaseList.push({id:diseaseId,diseaseicd:$(this).children().eq(2).html(),diseasename:$(this).children().eq(3).html()});
    });
    setFinalDiagnosis(diseaseList,num);
    $("#DiagnosisModal button[data-dismiss='modal']").click();
});

//点击确诊
$("#identifyButton").click(function () {
    if($("#finalDiagnosisForm tbody:eq(0)").html()===""&&$("#finalDiagnosisForm tbody:eq(1)").html()===""){
        showAlertDiv("alert-danger","错误!","至少选择一个终诊。");
        return;
    }
    var res = confirm('确认要提交终诊吗？');
    if(res === true)
        $("#identifyButton").hide();
        $.ajax({
            type: "POST",//方法类型
            dataType: "text",//预期服务器返回的数据类型
            url: "outpatientDoctorWorkstation/setFinalDiagnosis/"+$("#patientInfoDiv span:eq(1)").html(),
            data: $("#finalDiagnosisForm").serializeArray(),
            success: function (result) {
                if(result==="1"){
                    showAlertDiv("alert-success","成功!","确定终诊成功。");
                    $("[href='#menu2']").click();
                }
            }
        });
});