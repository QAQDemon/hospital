//放入初诊列表  0x 1z
function setNewDiagnosis(newDiagnosisList,diseaseList,num) {
    var str="";
    for (var i = 0; i < newDiagnosisList.length; i++) {
        var diseaseId=diseaseList[i].id;
        str+='<tr>\n' +
            '<td>'+(diseaseId)+'</td>\n' +
            '<td>'+diseaseList[i].diseaseicd+'</td>\n' +
            '<td title="'+diseaseList[i].diseasename+'" style="max-width:120px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+diseaseList[i].diseasename+'</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" '+((newDiagnosisList[i].isNewMajorDiagnosis==='1')?'':'checked')+'>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox ">\n' +
            '<input type="checkbox" class="custom-control-input" '+((newDiagnosisList[i].isNewSuspect==='1')?'':'checked')+'>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+getTime1(newDiagnosisList[i].dateOfOnset)+'</td>\n' +
            '</tr>';
    }
    $("#newDiagnosisContentCard tbody:eq("+num+")").html(str);
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
            debugger;
            var xNewDiagnosisList=result.xNewDiagnosisList;
            var zNewDiagnosisList=result.zNewDiagnosisList;
            var xDiagnosisDiseaseList=result.xDiagnosisDiseaseList;
            var zDiagnosisDiseaseList=result.zDiagnosisDiseaseList;
            var xFinalDiagnosisDiseaseList=result.xFinalDiagnosisDiseaseList;
            var zFinalDiagnosisDiseaseList=result.zFinalDiagnosisDiseaseList;
            setNewDiagnosis(xNewDiagnosisList,xDiagnosisDiseaseList,0);
            setNewDiagnosis(zNewDiagnosisList,zDiagnosisDiseaseList,1);
        }
    });
}

//初始化确诊界面
$("[href='#menu2']").click(function () {
    $("#commonDiagnosisLink").click();
    $("#identifyButton").hide();//确诊按钮隐藏，如果ajax未确诊会出现
    $("#menu2 tbody").html("");
    newFinalDiagnosisAjax();
});

$("#commonDiagnosisLink").click(function () {
    $("#comonDiagnosis .list-group").html("");
    commonDiagnosisAjax();
});
function commonDiagnosisAjax(){

}