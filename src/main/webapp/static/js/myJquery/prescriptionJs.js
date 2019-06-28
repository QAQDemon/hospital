//处方主体按钮显示的切换(新增和组套一直在) 1初始(修) 2暂存(暂、发、删、修) 3发送(作) 4作废(无)
function changePrescriptionBtns(status){
    if(status===1){
        $("#prescriptionForm button:eq(6)").show();
        $("#prescriptionForm button:gt(0):lt(4)").hide();
    }else if(status===2){
        $("#prescriptionForm button:eq(4)").hide();
        $("#prescriptionForm button:gt(0):lt(3)").show();
        $("#prescriptionForm button:eq(6)").show();
    }else if (status === 3) {
        $("#prescriptionForm button:gt(0):lt(3)").hide();
        $("#prescriptionForm button:eq(4)").show();
        $("#prescriptionForm button:eq(6)").hide();
    }else {
        $("#prescriptionForm button:gt(0):lt(4)").hide();
        $("#prescriptionForm button:eq(6)").hide();
    }
}
//添加处方列表
function setPrescriptionList(list) {
    for (var i = 0; i < list.length; i++) {
        var status=list[i].status;//1暂存 2发送 3未开立删除 4开立后作废
        var allColor=["text-info","text-primary","text-warning","text-danger"];
        allColor=allColor[eval(status) - 1];
        var allStatus=["暂存","发送","删除","作废"];
        status = allStatus[eval(status) - 1];
        var selectItem = ["", "", "", ""];
        selectItem[eval(list[i].prescriptionType)]=" selected";
        $("#prescriptionForm tbody:eq(0)").append('<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="prescriptionRadio'+i+'" value="'+list[i].id+'">\n' +
            '<label class="custom-control-label" for="prescriptionRadio'+i+'"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+list[i].id+'</td>\n' +
            '<td style="padding-left: 0;padding-right: 0">\n' +
            '<input type="text" class="form-control" value="'+list[i].prescriptionName+'" '+((status==="暂存")?"":'readonly="readonly"')+'>\n' +
            '</td>\n' +
            '<td>\n' +
            '<select class="custom-select">\n' +
            '<option value="0" '+selectItem[0]+'></option>\n' +
            '<option value="1" '+selectItem[1]+'>普诊</option>\n' +
            '<option value="2" '+selectItem[2]+'>急诊</option>\n' +
            '<option value="3" '+selectItem[3]+'>专诊</option>\n' +
            '</select>\n' +
            '</td>\n' +
            '<td>'+getTime1(list[i].buildTime)+'</td>\n' +
            '<td class="'+allColor+'">'+status+'</td>\n' +
            '<td>'+list[i].prescriptionInAmount+'</td>\n' +
            '</tr>');
    }
}
function prescriptionAjax(){
    var medicalInfoId=$("#patientInfoDiv span:eq(1)").html();
    if(medicalInfoId===""||medicalInfoId==="待创建")
        return;
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForPrescription/getPrescription/"+$("#prescriptionType").val()+"/"+medicalInfoId,
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            setPrescriptionList(result);
        }
    });
}
//初始化处方界面
$("[href='#menu3']").click(function () {
    var name=$(this).html();
    $("#prescriptionForm h4").html(name+"处方");
    $("#prescriptionCard .card-header").html(name+"处方列表");
    $("#menu3RightNav a:eq(1)").html(name+"组套");
    $("#menu3_1 .card-header").html(name+"药品");
    var node=$("#prescriptionType");
    if(name==="成药")
        node.val("1");
    else node.val("2");
    $("#menu3RightNav a:eq(0)").click();
    $("#prescriptionForm tbody").html("");
    changePrescriptionBtns(1);
    var visitStatus=$("#patientInfoDiv span:eq(0)").html();
    var button=$("#prescriptionForm button:eq(0)");
    if(visitStatus==="待诊"||visitStatus==="诊毕"||visitStatus==="")
        button.hide();
    else button.show();
    prescriptionAjax();
});
//添加药品列表
function setPrescriptionDetail(prescriptionDetailList,drugsList) {
    var readonlyFlag=$("#prescriptionCard :radio:checked").closest("tr").find("td:eq(5)").html();
    if (readonlyFlag === "暂存"||readonlyFlag === ""||readonlyFlag===undefined)
        readonlyFlag=" ";
    else readonlyFlag=' readonly="readonly" ';
    for (var i = 0; i < prescriptionDetailList.length; i++) {
        var selectUsage = ["", "", "", "","","","","","",""];
        selectUsage[eval(prescriptionDetailList[i].usageMethod)]=" selected";
        var selectFrequent = ["", "", "", "",""];
        selectFrequent[eval(prescriptionDetailList[i].frequent)]=" selected";
        $("#drugsCard tbody").append('<tr>\n' +
            '<td rowspan="2">'+drugsList[i].id+'</td>\n' +
            '<td rowspan="2">'+drugsList[i].drugsname+'</td>\n' +
            '<td rowspan="2">'+drugsList[i].drugsformat+'</td>\n' +
            '<td rowspan="2">'+drugsList[i].drugsprice+'</td>\n' +
            '<td><!--用法 1口服 2静脉注射 3肌肉注射 4皮试 5皮下注射 6静脉滴注 7外用 8喷雾吸入 9其他-->\n' +
            '<select name="usageMethod" class="custom-select-sm">\n' +
            '<option value="0" '+selectUsage[0]+'></option><option value="1" '+selectUsage[1]+'>口服</option>\n' +
            '<option value="2" '+selectUsage[2]+'>静脉注射</option><option value="3" '+selectUsage[3]+'>肌肉注射</option>\n' +
            '<option value="4" '+selectUsage[4]+'>皮试</option><option value="5" '+selectUsage[5]+'>皮下注射</option>\n' +
            '<option value="6" '+selectUsage[6]+'>静脉滴注</option><option value="7" '+selectUsage[7]+'>外用</option>' +
            '<option value="8" '+selectUsage[8]+'>喷雾吸入</option><option value="9" '+selectUsage[9]+'>其他</option>\n' +
            '</select>\n' +
            '</td>\n' +
            '<td style="padding-left: 0;padding-right: 0"><input class="form-control " name="consumption" type="text" value="'+prescriptionDetailList[i].consumption+'" '+readonlyFlag+'></td>\n' +
            '<td>\n' +
            '<select name="frequent" class="custom-select-sm"><!--频次,1一天一次 ，2一天两次 3一天三次 4其他-->\n' +
            '<option value="0" '+selectFrequent[0]+'></option><option value="1" '+selectFrequent[1]+'>一天一次</option>\n' +
            '<option value="2" '+selectFrequent[2]+'>一天两次</option><option value="3" '+selectFrequent[3]+'>一天三次</option>\n' +
            '<option value="4" '+selectFrequent[4]+'>其他</option>\n' +
            '</select>\n' +
            '</td>\n' +
            '<td style="padding-left: 0;padding-right: 0"><input class="form-control " name="days" type="number" value="'+prescriptionDetailList[i].days+'" '+readonlyFlag+'> </td>\n' +
            '<td style="padding-left: 0;padding-right: 0"><input class="form-control " name="amount" type="number" value="'+prescriptionDetailList[i].amount+'" '+readonlyFlag+'> </td>\n' +
            '</tr>\n' +
            '<tr>\n' +
            '<td colspan="4" style="padding-left: 0;padding-right: 0">\n' +
            '<div class="input-group">\n' +
            '<div class="input-group-prepend">\n' +
            '<span class="input-group-text font-weight-bold">用药嘱托:</span>\n' +
            '</div>\n' +
            '<input type="text" class="form-control" name="entrustment" value="'+prescriptionDetailList[i].entrustment+'" '+readonlyFlag+'>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td style="padding-left: 0;padding-right: 0" align="center"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<input type="hidden" name="drugsId" value="'+drugsList[i].id+'">'+
            '</tr>');
    }
}
//选择查看处方明细
$("#prescriptionCard").on("click","tr",function () {
    if($(this).find(":radio").val()===$("#prescriptionCard :radio:checked").val()||$(this).find(":radio").val()===undefined)
        return;
    else if($("#prescriptionCard :radio:checked").val()==="-1"){
        showAlertDiv("alert-danger", "危险!", "存在未保存的信息，先删除或暂存。");
        return;
    }
    $("#prescriptionCard :radio").prop("checked", false);
    $(this).find(":radio").prop("checked", 'true');
    var status=$(this).find("td:eq(5)").html();
    //1初始(修) 2暂存(暂、发、删、修) 3发送(作) 4作废(无)
    if(status===""||status==="暂存")
        changePrescriptionBtns(2);
    else if(status==="发送")
        changePrescriptionBtns(3);
    else if(status==="作废")
        changePrescriptionBtns(4);
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForPrescription/getPrescriptionDetail/"+$(this).find(":radio").val(),
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            $("#prescriptionForm tbody:eq(1)").html("");
            setPrescriptionDetail(result.prescriptionDetailList,result.drugsList);
        }
    });
}).on("click",":radio",function () {
    return false;//防止二次点击
});

//新增，加一行
$("#prescriptionBtnGroup button:eq(0)").click(function () {
    if($("#prescriptionCard :radio:checked").val()==="-1"){
        showAlertDiv("alert-danger", "危险!", "存在未保存的信息，先删除或暂存。");
        return;
    }
    //清除checked
    $("#prescriptionCard :radio").prop("checked", false);
    $("#prescriptionForm tbody:eq(1)").html("");
    $("#prescriptionForm tbody:eq(0)").append('<tr>\n' +
        '<td>\n' +
        '<div class="custom-control custom-radio">\n' +
        '<input type="radio" class="custom-control-input" id="prescriptionRadionew" value="-1" checked>\n' +
        '<label class="custom-control-label" for="prescriptionRadionew"></label>\n' +
        '</div>\n' +
        '</td>\n' +
        '<td></td>\n' +
        '<td style="padding-left: 0;padding-right: 0">\n' +
        '<input type="text" class="form-control" placeholder="请输入名称" >\n' +
        '</td>\n' +
        '<td>\n' +
        '<select class="custom-select">\n' +
        '<option value="0" selected></option>\n' +
        '<option value="1" >普诊</option>\n' +
        '<option value="2" >急诊</option>\n' +
        '<option value="3" >专诊</option>\n' +
        '</select>\n' +
        '</td>\n' +
        '<td></td>\n' +
        '<td></td>\n' +
        '<td></td>\n' +
        '</tr>');
    $("#prescriptionCard :text:last").focus();
    changePrescriptionBtns(2);
});
//暂存开立
$("#prescriptionBtnGroup button:gt(0):lt(2)").click(function () {
    var node=$("#prescriptionCard :radio:checked");
    var trNode=node.closest("tr");
    //判断提交内容是否完整
    if(trNode.find(":text").val()===""||trNode.find(":selected").val()==="0"||$("#drugsCard tbody").html()===""){
        showAlertDiv("alert-warning","警告!","请输入名称，选择类型和至少一个药品。");
        return;
    }
    var flag=0;
    $("#drugsCard [name='entrustment']").each(function () {
        if($(this).val()===""){
            showAlertDiv("alert-warning","警告!","请输入用药嘱托。");
            flag=1;
            return false;
        }
    });
    $("#drugsCard :selected").each(function () {
        if($(this).val()==="0"){
            showAlertDiv("alert-warning","警告!","请完成选择。");
            flag=1;
            return false;
        }
    });
    $("#drugsCard [type='number']").each(function () {
        var num=$(this).val();
        if(num===""||num<=0||(num%1 !== 0)){
            showAlertDiv("alert-warning","警告!","天数和数量必须是大于0的整数。");
            flag=1;
            return false;
        }
    });
    $("#drugsCard [name='consumption']").each(function () {
        var num=$(this).val();
        var reg=/^\d+(\.\d+)?$/;
        if(!reg.test(num)||num==="0"){
            showAlertDiv("alert-warning","警告!","用量是必须大于0的数字。");
            flag=1;
            return false;
        }
    });
    if(flag===1)
        return;
    var alertNum=showAlertDiv("alert-secondary","","处方信息保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForPrescription/setPrescriptionAndDetail/"+$("#prescriptionType").val()+"/"+$(this).index()+"/"+$("#patientInfoDiv span:eq(1)").html()+"/"+node.val()+"/"+trNode.find(":text").val()+"/"+trNode.find(":selected").val()+"/"+trNode.find("td:last").html(),
        data: $("#prescriptionForm").serializeArray(),
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv("alert-success","成功!","处方信息保存成功。");
                flushPrescriptionPage();
            }
            else showAlertDiv("alert-warning","警告!","处方信息保存失败。");
        }
    });
});
//刷新处方界面，药品列表会被清空
function flushPrescriptionPage() {
    $("#allNavTab [href='#menu3']:contains("+$("#prescriptionForm h4:first").html().substring(0,2)+")").click();
}

//删除或作废 3删除 4作废
function deletePrescriptionAjax(num){
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForPrescription/canclePrescription/"+num+"/"+$("#prescriptionCard :radio:checked").val(),
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            if(result==="1")
                showAlertDiv("alert-success","成功!","删除或作废成功。");
            else if(result==="0")
                showAlertDiv("alert-warning","警告!","删除或作废失败。");
            else if(result==="2")
                showAlertDiv("alert-danger","错误!","该处方已付费或退费，无法作废。");
            flushPrescriptionPage();
        }
    });
}
//删除
$("#prescriptionBtnGroup button:eq(3)").click(function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        if($("#prescriptionCard :radio:checked").val()==="-1")
            flushPrescriptionPage();
        else deletePrescriptionAjax('3');
    }
});
//作废
$("#prescriptionBtnGroup button:eq(4)").click(function () {
    var res = confirm('确认要作废吗？');
    if(res === true){
        deletePrescriptionAjax('4');
    }
});
//存为组套
$("#prescriptionBtnGroup button:eq(5)").click(function () {
    $("#menu3RightNav a:last").click();
    $("#prescriptionSetBtnGroup :eq(0)").click();
    var drugsList=getDrugsCardData();
    var setSubList=[];
    $("#drugsCard tbody tr:odd").each(function () {
        setSubList.push({entrust:$(this).find(":text").val()});
    });
    setSetSub1(drugsList,setSubList);
    disableSetSub1(false);
});

//药品 搜索
var drugsList;//药品信息列
var drugsFlag;//flag 1创建 2不创建页码并跳过
var drugsPages;//总页数
function addSearchDrugs() {
    $("#drugsNotCheckedTbody").html("");
    apendCheckedDrugs(drugsList, true);
}
function drugsPageselectCallback(page_index,jq){
    if(!(drugsFlag===1&&page_index===0)){
        drugsFlag=2;
        searchDrugsAjax(page_index+1);
    }
    addSearchDrugs();
    return false;
}
function searchDrugsAjax(pageNum){
    var urlS="applyForPrescription/searchDrugs/"+$("#prescriptionType").val()+"/"+pageNum;
    var drugsKey=$("#drugsKey").val();
    if(drugsKey!=="")
        urlS += ("/" + drugsKey);
    $.ajax({
        type: "POST",//方法类型
        async:false,//防止分页错误
        dataType: "json",//预期服务器返回的数据类型
        url: urlS,
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            drugsList=result.drugsList;
            drugsPages=result.pages;
            if(drugsFlag===1){
                var initPagination = function() {
                    // 创建分页
                    $("#drugsPagination").pagination(drugsPages, {
                        num_edge_entries: 1, //边缘页数
                        num_display_entries: 4, //主体页数
                        callback: drugsPageselectCallback
                    });
                }();
            }
        }
    });
}

function initeDrugsModal(){
    var node=$("#DrugsModal");
    node.find("tbody").html("");//清空表格
    node.find("#drugsKey").val("");
    $("#drugsPagination").html("");
    $("#drugsPageJump").hide();
    $("#searchDrugsPage").val("");
    $("#DrugsModal button:contains('导入结果')").show();
    $("#DrugsModal button:contains('保存')").hide();
}
//获取药品列表的药品信息的list
function getDrugsCardData(){
    var list=[];
    $("#drugsCard tbody tr:even").each(function () {
        list.push({id:$(this).find("td:eq(0)").html(),drugsname:$(this).find("td:eq(1)").html(),drugsformat:$(this).find("td:eq(2)").html(),drugsprice:$(this).find("td:eq(3)").html()});
    });
    return list;
}
//药品列表修改按钮 弹窗
$("#drugsCard button:eq(0)").click(function () {
    initeDrugsModal();
    var list=getDrugsCardData();
    apendCheckedDrugs(list,false);
});
//组套子项的修改
$("#prescriptionContextForm button[data-target='#DrugsModal']:contains('修改')").click(function () {
    initeDrugsModal();
    $("#DrugsModal button:contains('导入结果')").hide();
    $("#DrugsModal button:contains('保存')").show();
    apendCheckedDrugs(getSetSubDrugsData(),false);
});
//将内容导入药品弹窗的左右侧 bool true左 false右
function apendCheckedDrugs(list,bool) {
    var node;
    if (bool)
        node=$("#drugsNotCheckedTbody");
    else
        node=$("#drugsCheckedTbody");
    for(var i=0;i<list.length;i++){
        node.append('<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox" >\n' +
            '<input type="checkbox" class="custom-control-input" id="drugsCheck'+((bool)?'0':'1')+i+'" name="drugsCheckGroup'+((bool)?'0"':'1" checked')+'/>\n' +
            '<label class="custom-control-label" for="drugsCheck'+((bool)?'0':'1')+i+'"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+list[i].id+'</td>\n' +
            '<td title="'+list[i].drugsname+'" style="max-width:200px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+list[i].drugsname+'</td>\n' +
            '<td>'+list[i].drugsformat+'</td>\n' +
            '<td>'+list[i].drugsprice+'</td>\n' +
            '</tr>');
    }
}
$("#DrugsModal").on("click",":checkbox",function () {
    return false;//防止二次点击
});
function searchDrugsMethod(){
    drugsFlag=1;
    searchDrugsAjax(1);
    $("#drugsPageJump").show();
}
$("#drugsSearchInput").on("click","button",function () {
    searchDrugsMethod();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchDrugsMethod();
    }
});

//跳转
function drugsPageJumpMethod(){
    var num=$("#searchDrugsPage").val();
    if(num==null||num<=0||num>drugsPages||(num%1 !== 0)){
        showAlertDiv("alert-danger","错误!","异常页码。");
        return;
    }
    var current=$("#drugsPagination .current").html();
    if(current===num)//可排除第一次跳第一页
        return;
    drugsFlag=2;
    searchDrugsAjax(num);
    $("#drugsPagination").html("");
    var initPagination = function() {
        // 更改分页
        $("#drugsPagination").pagination(drugsPages, {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            callback: drugsPageselectCallback,
            current_page:num-1
        });
    }();
    addSearchDrugs();
}
$("#drugsPageJump").on("click","button",function () {
    drugsPageJumpMethod();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        drugsPageJumpMethod();
    }
});

//处方 搜索增加
$("#drugsNotCheckedTbody").on("click","tr",function () {
    var id=$(this).children().eq(1).html();
    var oneflag=0;
    var node=$("#drugsCheckedTbody");
    node.children().each(function () {
        if($(this).children().eq(1).html()===id){
            showAlertDiv("alert-warning","警告!","已选择。");
            oneflag=1;
            return false;
        }
    });
    if(oneflag===1)
        return;
    node.append("<tr>"+$(this).html()+"</tr>")
        .find("input").last().attr("checked",'true');
});
//处方 搜索删除
$("#drugsCheckedTbody").on("click","tr",function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        $(this).remove();
    }
});

//获得药品弹窗右侧的选择数据结果
function getCheckedDrugsData(){
    var drugsList=[];
    $("#drugsCheckedTbody tr").each(function () {
        drugsList.push({id:$(this).find("td:eq(1)").html(),drugsname:$(this).find("td:eq(2)").html(),drugsformat:$(this).find("td:eq(3)").html(),drugsprice:$(this).find("td:eq(4)").html()});
    });
    return drugsList;
}
//导入药品结果，求同存异
$("#DrugsModal .modal-footer :button:contains('导入结果')").click(function () {
    var drugsList=getCheckedDrugsData();
    //原有的倒序循环，找到则删除list，找不到则删除原有
    for(var i=($("#drugsCard tbody tr").length/2)-1;i>=0;i--){
        var anId=$("#drugsCard tbody tr:eq("+(i*2)+")").find("td:eq(0)").html();
        var flag=0;
        for (var j = 0; j < drugsList.length; j++) {
            if (drugsList[j].id === anId) {
                drugsList.splice(j, 1);
                flag = 1;
                break;
            }
        }
        if(flag===0){
            $("#drugsCard tbody").find("tr:eq("+(i*2)+"),tr:eq("+(i*2+1)+")").remove();
        }
    }
    //生成空列
    var prescriptionDetailList=[];
    for (var k = 0; k < drugsList.length; k++) {
        prescriptionDetailList.push({usageMethod:"0", frequent: "0",consumption:"",days:"",amount:"",entrustment:""});
    }
    setPrescriptionDetail(prescriptionDetailList,drugsList);
    $("#DrugsModal button[data-dismiss='modal']").click();
});

//计算处方金额，单价*数量，排除非正整数
function caculatePrescriptionAmount(){
    var amount=0;
    $("#drugsCard tbody tr:even").each(function () {
        var num=$(this).find("[type='number']:last").val();
        if(num===""||num<=0||(num%1 !== 0))
            return true;
        amount+=eval(num)*eval($(this).find("td:eq(3)").html());
    });
    return Number(amount).toFixed(2);
}
//改变数量就计算金额
$("#drugsCard tbody").on("change","[type='number']:odd",function () {
    var amountNode=$("#prescriptionCard :radio:checked").closest("tr").children().last();
    if(amountNode.html()!==undefined)
        amountNode.html(caculatePrescriptionAmount());
});

//保存到组套子项 求同存异
$("#DrugsModal .modal-footer :button:contains('保存')").click(function () {
    var drugsList=getCheckedDrugsData();
    //倒序循环，找到则删除list，找不到则删除原有
    for(var i=$("#prescriptionContextForm tbody tr").length-1;i>=0;i--){
        var anId=$("#prescriptionContextForm tbody tr:eq("+i+")").find("[name='objectId']").val();
        var flag=0;
        for (var j = 0; j < drugsList.length; j++) {
            if (drugsList[j].id === anId) {
                drugsList.splice(j, 1);
                flag = 1;
                break;
            }
        }
        if(flag===0){
            $("#prescriptionContextForm tbody").find("tr:eq("+i+")").remove();
        }
    }
    //生成空列
    var setSubList=[];
    for (var k = 0; k < drugsList.length; k++) {
        setSubList.push({entrust:""});
    }
    setSetSub1(drugsList,setSubList);
    disableSetSub1(false);
    $("#DrugsModal button[data-dismiss='modal']").click();
});

//放入常用药品
function insertCommonDrugs(list){
    var str="";
    for (var i = 0; i < list.length; i++) {
        str+='<a href="#" class="list-group-item list-group-item-action"><span>'+list[i].drugsname+'</span><span class="badge badge-pill badge-danger">X</span></a>\n' +
            '<input type="hidden" value="'+list[i].id+'">'+
            '<input type="hidden" value="'+list[i].drugsformat+'">'+
            '<input type="hidden" value="'+list[i].drugsprice+'">';
    }
    $("#menu3_1 .list-group").html(str);
}
//常用药品生成
$("#menu3RightNav a:eq(0)").click(function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForPrescription/getCommonOption/"+$("#prescriptionType").val(),
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            insertCommonDrugs(result);
        }
    });
});
//双击将常用药品加到药品列表下
$("#menu3_1").on("dblclick","a",function () {
    var flag=0;
    var drugsId=$(this).next().val();
    $("#drugsCard tbody tr:even").each(function () {
        if($(this).children().eq(0).html()===drugsId){
            flag=1;
            return false;//break
        }
    });
    if(flag===1){
        showAlertDiv("alert-warning","警告!","该药品已存在。");
        return;//重复跳出
    }
    var drugsList=[{id:drugsId,drugsname:$(this).find("span:eq(0)").html(),drugsformat:$(this).next().next().val(),drugsprice:$(this).next().next().next().val()}];
    var prescriptionDetailList=[{usageMethod:"0", frequent: "0",consumption:"",days:"",amount:"",entrustment:""}];
    setPrescriptionDetail(prescriptionDetailList,drugsList);
    showAlertDiv("alert-success","成功!","药品插入成功。");
})//删除常用处方
    .on("click",".badge-danger",function () {
        var res = confirm('确认要删除吗？');
        if(res === true) {
            $.ajax({
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "applyForPrescription/deleteCommonDrugs/" + $("#prescriptionType").val() + "/" + $(this).parent().next().val(),
                data: {},
                headers: {
                    Authorization:"Bearer "+getCookie("token")
                },
                error:function(result){
                    console.log(result);
                    showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
                },
                success: function (result, textStatus, request) {
                    setTokenToCookie("token",request.getResponseHeader('Authorization'));
                    $("#menu3RightNav a:eq(0)").click();
                }
            });
        }
    })//取消跳转
    .on("click","a",function () {
        return false;
});

//增加常用药品
$("#drugsCard").on("click","a",function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForPrescription/addCommonDrugs/"+$("#prescriptionType").val()+"/"+$(this).closest("tr").prev().children().first().html(),
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            if(result==="1")
                showAlertDiv("alert-success","成功!","增加常用药品成功。");
            else showAlertDiv("alert-danger","失败!","增加常用药品失败。");
            $("#menu3RightNav a:eq(0)").click();
        }
    });
    return false;
});

//点击导航直接搜索组套
$("#menu3RightNav a:last").click(function () {
    $("#searchPrescriptionForm button").click();
    disableSetBtn1(true);
    disableSetContext1(true);
    clearSetContent1();
});
//放入组套的名字到标签
function addSetContext1(map){
    var str="";
    for(var key in map){
        str+=' <a href="#" class="list-group-item list-group-item-action">'+map[key]+'</a><input type="hidden" value="'+key+'">';
    }
    $("#prescriptionListDiv").html(str);
}
//组套类型选择
$("#prescriptionChooseDiv :radio").click(function () {
    searchSetGroup1();
});
function searchSetGroup1(){
    var category=$("#prescriptionChooseDiv :checked").val();
    if(category===undefined){//未选的情况
        category=1;
        $("#prescriptionChooseDiv [type='radio']:eq(0)").attr("checked",true);
    }
    var key=$("#searchPrescriptionKey").val();
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "setManage/getSetGroup/"+category+"/"+(eval($("#prescriptionType").val())+3)+((key==="")?"":("/"+key)),
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            addSetContext1(result);
        }
    });
}
//组套搜索
$("#searchPrescriptionForm").on("click","button",function () {
    searchSetGroup1();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchSetGroup1();
    }
});

//清空组套内容
function clearSetContent1(){
    $("#prescriptionListDiv a").removeClass("active");
    $("#prescriptionIdSet").val("");
    $("#categoryPrescription").val(0);
    $("#prescriptionContextForm [type='text']").val("");
    $("#prescriptionContextForm").find("span,tbody").html("");
}
//放入组套子项
function setSetSub1(drugsList,setSubList){
    for (var i=0;i<drugsList.length;i++) {
        $("#prescriptionContextForm tbody").append('<tr>\n' +
            '<td title="'+drugsList[i].drugsname+'" style="max-width:160px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+drugsList[i].drugsname+'</td>\n' +
            '<td style="padding-left: 0;padding-right: 0">\n' +
            '<input type="text" class="form-control" name="setSubEntrust" value="'+setSubList[i].entrust+'" readonly="readonly">\n' +
            '<input type="hidden" name="objectId" value="'+drugsList[i].id+'">\n' +
            '<input type="hidden" value="'+drugsList[i].drugsformat+'">\n' +
            '<input type="hidden" value="'+drugsList[i].drugsprice+'">\n' +
            '</td>\n' +
            '</tr>');
    }
}
//解锁或锁定组套按钮 true锁定
function disableSetBtn1(bool){
    var btns=$("#prescriptionSetBtnGroup").find(".btn-outline-warning,.btn-outline-danger");
    if(bool===true)
        btns.hide();
    else  btns.show();
}
//点击标签获得组套内容
$("#prescriptionListDiv").on("click","a",function () {
    //变色
    $("#prescriptionListDiv a").removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "setManage/getSetContent/"+(eval($("#prescriptionType").val())+3)+"/"+$(this).next().val(),
        data: {},
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            $("#prescriptionCodeTemplate").attr("readonly",true);
            $("#prescriptionCategoryDiv").hide();
            disableSetContext1(true);
            if(result.setGroup===null){
                showAlertDiv("alert-warning","警告!","组套不存在。");
                return;
            }
            var setGroup=result.setGroup;
            if(eval($("#doctorId").val())===setGroup.createrId)//判断是否能修改删除
                disableSetBtn1(false);
            else disableSetBtn1(true);
            $("#prescriptionIdSet").val(setGroup.id);
            $("#categoryPrescription").val(eval(setGroup.useScope));
            $("#prescriptionCodeTemplate").val(setGroup.setCode);
            $("#prescriptionNameTemplate").val(setGroup.setName);
            $("#prescriptionCreatTime").html(getTime1(setGroup.buildDate));
            $("#prescriptionContextForm tbody").html("");
            setSetSub1(result.objectList,result.setSubList);
        }
    });
    return false;
});
//获得组套子项的药品信息
function getSetSubDrugsData() {
    var drugsList=[];
    $("#prescriptionContextForm tbody tr").each(function () {
        drugsList.push({id:$(this).find("[name='objectId']").val(),drugsname:$(this).find("td:eq(0)").html(),drugsformat:$(this).find("[type='hidden']:eq(1)").val(),drugsprice:$(this).find("[type='hidden']:eq(2)").val()});
    });
    return drugsList;
}
//组套子项插入主体
function addSetSub1(){
    var drugsList=getSetSubDrugsData();
    var setSubList=[];
    $("#prescriptionContextForm tbody tr").each(function () {
        setSubList.push({usageMethod:"0", frequent: "0",consumption:"",days:"",amount:"",entrustment:$(this).find("[name='setSubEntrust']").val()});
    });
    //删除已存在
    $("#drugsCard tbody tr:even").each(function () {
        var anId=$(this).find("td:first").html();
        for (var i = drugsList.length-1; i >=0; i--) {
            if (drugsList[i].id === anId) {
                drugsList.splice(i, 1);
                setSubList.splice(i, 1);
                break;
            }
        }
    });
    setPrescriptionDetail(setSubList,drugsList);
}
//引用组套
$("#prescriptionSetBtnGroup button:contains('引用')").click(function () {
    addSetSub1();
});
//删除组套
$("#prescriptionSetBtnGroup button:eq(2)").click(function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        if($("#prescriptionIdSet").val()!==""){
            $.ajax({
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "setManage/cancelSetGroup/"+$("#prescriptionIdSet").val(),
                data: {},
                headers: {
                    Authorization:"Bearer "+getCookie("token")
                },
                error:function(result){
                    console.log(result);
                    showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
                },
                success: function (result, textStatus, request) {
                    setTokenToCookie("token",request.getResponseHeader('Authorization'));
                    if(result==="0")//好像无效
                        showAlertDiv("alert-danger","错误!","删除组套失败。");
                    $("#prescriptionChooseDiv :checked").click();
                }
            });
        }else showAlertDiv("alert-danger","错误!","删除组套失败，请重新选择。");
        disableSetBtn1(true);
        clearSetContent1();
        disableSetContext1(true);
    }
});
//锁定组套内容或解开 bool true锁
function disableSetContext1(bool){
    $("#prescriptionNameTemplate").attr("readonly",bool);
    var btns=$("#prescriptionContextForm button");
    if(bool)
        btns.hide();
    else btns.show();
}
//锁定组套子项的输入框 bool true锁定
function disableSetSub1(bool){
    $("#prescriptionContextForm tbody :text").attr("readonly",bool);
}
// 修改增加组套后提交
$("#prescriptionContextForm button:contains('提交')").click(function () {
    var codeNode=$("#prescriptionCodeTemplate");
    if(codeNode.val()===""||$("#categoryPrescription").val()==="0"){
        showAlertDiv("alert-warning", "警告!", "组套编码和适用范围不能为空。");
        codeNode.focus();
        return;
    }
    var alertNum=showAlertDiv("alert-secondary","","组套保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "setManage/saveSetGroup/"+((codeNode.attr("readonly")==="readonly")?"2":"1")+"/"+(eval($("#prescriptionType").val())+3),
        data: $("#prescriptionContextForm").serializeArray(),
        headers: {
            Authorization:"Bearer "+getCookie("token")
        },
        error:function(result){
            console.log(result);
            showAlertDiv("alert-warning","警告!","登录失效，请重新登录。");
        },
        success: function (result, textStatus, request) {//1成功 0更新失败（已删除） 2新增失败（code已存在）
            setTokenToCookie("token",request.getResponseHeader('Authorization'));
            closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv("alert-success","成功!","组套提交成功。");
                $("#prescriptionCategoryDiv").hide();
                codeNode.attr("readonly",true);
                disableSetSub1(true);
                disableSetContext1(true);
                $("#prescriptionListDiv .active").click();
            }
            else if(result==="2") {
                showAlertDiv("alert-warning", "警告!", "当前分类下组套编码已存在。");
                codeNode.focus();
            }
            else {
                showAlertDiv("alert-warning", "警告!", "当前分类下组套不存在。");
            }
        }
    });
});
//修改组套
$("#prescriptionSetBtnGroup").on("click","button:eq(1)",function () {
    $("#prescriptionCodeTemplate").attr("readonly",true);
    disableSetSub1(false);
    disableSetContext1(false);
}).on("click","button:eq(0)",function () {//新增组套
    $("#prescriptionCategoryDiv").show();
    $("#prescriptionCodeTemplate").attr("readonly",false);
    disableSetContext1(false);
    clearSetContent1();
    disableSetBtn1(true);
});