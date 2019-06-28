//放入明细到表格 num 0申请 1处方
function setFeeList(num,list){
    var str="";
    var amount=0;
    for (var i = 0; i < list.length; i++) {
        if(num===0){
            var type=["检查","检验","处置"];
            amount+=eval(list[i].fee);
            str+='<tr>\n' +
                '<td>'+list[i].id+'</td>\n' +
                '<td>'+type[eval(list[i].type)-1]+'</td>\n' +
                '<td>'+list[i].purposeRequirement+'</td>\n' +
                '<td>'+getTime1(list[i].applicationTime)+'</td>\n' +
                '<td>'+list[i].fee+'</td>\n' +
                '<td class="text-center">'+((list[i].feeStatus==='2')?'√':'X')+'</td>\n' +
                '</tr>';
        }else {
            var type1=["成药","草药"];
            var type2=["普诊","急诊","专诊"];
            amount+=eval(list[i].prescriptionInAmount);
            str+='<tr>\n' +
                '<td>'+list[i].id+'</td>\n' +
                '<td>'+type1[eval(list[i].type)-1]+'</td>\n' +
                '<td>'+list[i].prescriptionName+'</td>\n' +
                '<td>'+type2[eval(list[i].prescriptionType)-1]+'</td>\n' +
                '<td>'+getTime1(list[i].buildTime)+'</td>\n' +
                '<td>'+list[i].prescriptionInAmount+'</td>\n' +
                '<td class="text-center">'+((list[i].feeStatus==='2')?'√':'X')+'</td>\n' +
                '</tr>';
        }
    }
    $("#menu4 tbody:eq("+num+")").html(str);
    $("#menu4 span:eq("+((num===0)?"1":"3")+")").html(Number(amount).toFixed(2));
}
//获得费用明细 num 0申请 1处方
function patientFeeAjax(num){
    var medicalInfoId=$("#patientInfoDiv span:eq(1)").html();
    if(medicalInfoId===""||medicalInfoId==="待创建")
        return;
    var url=(num===0)?"getActiveVisitItem":"getActivePrescription";
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "outpatientDoctorWorkstation/"+url+"/"+$("#menu4 select:eq("+num+")").val()+"/"+medicalInfoId,
        data: $("#visitItemForm").serializeArray(),
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            if(result.length!==0&&result[0].purposeRequirement===undefined)//判断是处方
                setFeeList(1,result);
            else setFeeList(0,result);

        }
    });
}

//初始化费用界面
$("[href='#menu4']").click(function () {
    $("#menu4 span:eq(1),#menu4 span:eq(3)").html("");
    $("#menu4 tbody").html("");
    $("#menu4 select").val("0");
    patientFeeAjax(0);
    patientFeeAjax(1);
});

//切换费用类别
$("#menu4 select").change(function () {
    if($(this).prev().html()==="申请费用")
        patientFeeAjax(0);
    else  patientFeeAjax(1);
});