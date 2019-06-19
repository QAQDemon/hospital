var alertFlag=100;
function showAlertDiv2(color,caption,text){
    alertFlag++;
    return $.outpatientMethod.showAlertDiv(alertFlag,color,caption,text);
}

//修改时间格式
function getTime1(t){
    if(t===null)
        return "";
    var _time=new Date(t);
    var   year=_time.getFullYear();//2017
    var   month=(Array(2).join("0") + (_time.getMonth()+1)).slice(-2);
    var   date=(Array(2).join("0") + (_time.getDate())).slice(-2);
    var   hour=(Array(2).join("0") + (_time.getHours())).slice(-2);
    var   minute=(Array(2).join("0") + (_time.getMinutes())).slice(-2);
    return   (year+"-"+month+"-"+date+" "+hour+":"+minute).substring(2,16);//14-01-02 11:42
}
//申请主体按钮显示的切换(新增和组套一直在) 1初始(修) 2暂存(暂、开、删、修) 3开立(作) 4作废、完成(无)
function changeApplyForBtns(status){
    if(status===1){
        $("#visitItemForm button:eq(6)").show();
        $("#visitItemForm button:gt(0):lt(4)").hide();
    }else if(status===2){
        $("#visitItemForm button:eq(4)").hide();
        $("#visitItemForm button:gt(0):lt(3)").show();
        $("#visitItemForm button:eq(6)").show();
    }else if (status === 3) {
        $("#visitItemForm button:gt(0):lt(3)").hide();
        $("#visitItemForm button:eq(4)").show();
        $("#visitItemForm button:eq(6)").hide();
    }else {
        $("#visitItemForm button:gt(0):lt(4)").hide();
        $("#visitItemForm button:eq(6)").hide();
    }
}
//添加申请单列表
function setApplyForList(list,applyForPeople) {
    for (var i = 0; i < list.length; i++) {
        var status=list[i].status;//1暂存 2开立 3未开立删除 4开立后作废 5完成
        var allStatus=["暂存","开立","删除","作废","完成"];
        status = allStatus[eval(status) - 1];
        $("#visitItemForm tbody:eq(0)").append('<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="applyForRadio'+i+'" value="'+list[i].id+'">\n' +
            '<label class="custom-control-label" for="applyForRadio'+i+'"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+list[i].id+'</td>\n' +
            '<td>'+getTime1(list[i].applicationTime)+'</td>\n' +
            '<td>'+status+'</td>\n' +
            '<td>'+applyForPeople[i]+'</td>\n' +
            '<td  style="padding: 0">\n' +
            '<input type="text" class="form-control" value="'+list[i].purposeRequirement+'" '+((status==="暂存")?"":'readonly="readonly"')+'>\n' +
            '</td>\n' +
            '<td class="text-center">'+((list[i].feeStatus==="1")?'X':'√')+'</td>\n' +
            '<td class="text-center">'+((list[i].executionStatus==="1")?'X':'√')+'</td>\n' +
            '<td>'+list[i].fee+'</td>\n' +
            '</tr>');
    }
}
function clearApplyForContext() {
     $("#visitItemForm tbody").html("");//todo
}
function applyForItemAjax(){
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForFmeditem/getVisitItemList/"+$("#applyForType").val()+"/"+$("#patientInfoDiv span:eq(1)").html(),
        data: {},
        success: function (result) {
            setApplyForList(result.visitItemList,result.applyForPeople);
        }
    });
}
//初始化申请界面
$("[href='#menu1']").click(function () {
    var name=$(this).html();
    $("#visitItemForm h4").html(name+"申请");
    $("#applyForCard .card-header").html(name+"申请单列表");
    $("#menu1RightNav a:eq(1) small").html(name+"组套");
    $("#home2_1 .card-header").html(name+"项目");
    var node=$("#applyForType");
    if(name==="检查")
        node.val("1");
    else if (name === "检验")
        node.val("2");
    else node.val("3");
    clearApplyForContext();
    changeApplyForBtns(1);
    applyForItemAjax();
});
//添加项目明细表
function setVisitItemList(visitDetailList,fmeditemList) {
    var readonlyFlag=$("#applyForCard :checked").closest("tr").find("td:eq(3)").html();
    if (readonlyFlag === "暂存"||readonlyFlag === "")
        readonlyFlag=" ";
    else readonlyFlag=' readonly="readonly" ';
    for (var i = 0; i < visitDetailList.length; i++) {
        $("#visitItemCard tbody").append('<tr>\n' +
            '<td>'+fmeditemList[i].id+'</td>\n' +
            '<td>'+fmeditemList[i].itemname+'</td>\n' +
            '<td>'+fmeditemList[i].price+'</td>\n' +
            '<td  style="padding: 0">\n' +
            '<input type="text" class="form-control" name="doctorEntrustment" value="'+visitDetailList[i].doctorEntrustment+' " '+readonlyFlag+'>\n' +
            '</td>\n' +
            '<td class="text-center">'+((visitDetailList[i].executionStatus==="0")?'X':'√')+'</td>\n' +
            '<td class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary btn-sm" style="width: 100%;height: 100%">查看结果</button></td>\n' +
            '<input type="hidden" name="fmeditemId" value="'+fmeditemList[i].id+'">'+
            '</tr>');
    }

}
//选择查看申请明细
$("#applyForCard").on("click","tr",function () {
    if($(this).find(":radio").val()==="-1")
        return;
    else if($("#applyForCard :checked").val()==="-1"){
        showAlertDiv2("alert-danger", "危险!", "存在未保存的信息，先删除或暂存。");
        return;
    }
    $("#applyForCard :radio").prop("checked", false);
    $(this).find(":radio").prop("checked", 'true');
    var status=$(this).find("td:eq(3)").html();
    //1初始(修) 2暂存(暂、开、删、修) 3开立(作) 4作废、完成(无)
    if(status===""||status==="暂存")
        changeApplyForBtns(2);
    else if(status==="开立")
        changeApplyForBtns(3);
    else if(status==="作废"||status==="完成")
        changeApplyForBtns(4);
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForFmeditem/getVisitItemDetail/"+$(this).find(":radio").val(),
        data: {},
        success: function (result) {
            $("#visitItemForm tbody:eq(1)").html("");
            setVisitItemList(result.visitItemDetailList,result.fmeditemList);
        }
    });
}).on("click",":radio",function () {
    return false;//防止二次点击
});

//新增，加一行
$("#applyForBtnGroup button:eq(0)").click(function () {
    if($("#applyForCard :checked").val()==="-1"){
        showAlertDiv2("alert-danger", "危险!", "存在未保存的信息，先删除或暂存。");
        return;
    }
    //清除checked
    $("#applyForCard :radio").prop("checked", false);
    $("#visitItemForm tbody:eq(1)").html("");
    $("#visitItemForm tbody:eq(0)").append('<tr><td>\n' +
        '<div class="custom-control custom-radio">\n' +
        '<input type="radio" class="custom-control-input" id="applyForRadionew" value="-1"  checked>\n' +
        '<label class="custom-control-label" for="applyForRadionew"></label></div>\n' +
        '</td><td></td><td></td><td></td><td></td>\n' +
        '<td  style="padding: 0"><input type="text" class="form-control" placeholder="请输入目的和要求">\n' +
        '</td><td class="text-center"></td><td class="text-center"></td>\n' +
        '<td></td></tr>');
    $("#applyForCard :text:last").focus();
    changeApplyForBtns(2);
});

//暂存开立
$("#applyForBtnGroup button:gt(0):lt(2)").click(function () {
    var node=$("#applyForCard :checked");
    //判断提交内容是否完整
    if(node.closest("tr").find(":text").val()===""||$("#visitItemCard tbody").html()===""){
        showAlertDiv2("alert-warning","警告!","请输入目的和要求且选择至少一个项目。");
        return;
    }
    var flag=0;
    $("#visitItemCard :text").each(function () {
        if($(this).val()===""){
            showAlertDiv2("alert-warning","警告!","请输入医生嘱托。");
            flag=1;
            return false;
        }
    });
    if(flag===1)
        return;
    var trNode=node.closest("tr");
    var alertNum=showAlertDiv2("alert-secondary","","项目信息保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForFmeditem/setVisitItemAndDetail/"+$("#applyForType").val()+"/"+$(this).index()+"/"+$("#patientInfoDiv span:eq(1)").html()+"/"+node.val()+"/"+trNode.find(":text").val()+"/"+trNode.find("td:last").html(),
        data: $("#visitItemForm").serializeArray(),
        success: function (result) {
            $.outpatientMethod.closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv2("alert-success","成功!","项目信息保存成功。");
                flushApplyForPage();
            }
            else showAlertDiv2("alert-warning","警告!","项目信息保存失败。");
        }
    });
});
//刷新申请界面，项目列表会被清空
function flushApplyForPage() {
    $("#allNavTab [href='#menu1']:contains("+$("#visitItemForm h4:first").html().substring(0,2)+")").click();
}

//删除或作废 3删除 4作废
function deleteApplyForAjax(num){
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForFmeditem/cancleVisitItem/"+num+"/"+$("#applyForCard :checked").val(),
        data: $("#visitItemForm").serializeArray(),
        success: function (result) {
            if(result==="1")
                showAlertDiv2("alert-success","成功!","删除或作废成功。");
            else if(result==="0")
                showAlertDiv2("alert-warning","警告!","删除或作废失败。");
            else if(result==="2")
                showAlertDiv2("alert-danger","错误!","该项目已登记，无法作废。");
            flushApplyForPage();
        }
    });
}
//删除
$("#applyForBtnGroup button:eq(3)").click(function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        if($("#applyForCard :checked").val()==="-1")
            flushApplyForPage();
        else deleteApplyForAjax('3');
    }
});
//作废
$("#applyForBtnGroup button:eq(4)").click(function () {
    var res = confirm('确认要作废吗？');
    if(res === true){
        deleteApplyForAjax('4');
    }
});