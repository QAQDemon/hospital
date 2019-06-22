var alertFlag=200;
function showAlertDiv3(color,caption,text){
    alertFlag++;
    return $.outpatientMethod.showAlertDiv(alertFlag,color,caption,text);
}
//修改时间格式
function getTime2(t){
    if(t===null)
        return "";
    var _time=new Date(t);
    var   year=_time.getFullYear();//2017
    var   month=(Array(2).join("0") + (_time.getMonth()+1)).slice(-2);
    var   date=(Array(2).join("0") + (_time.getDate())).slice(-2);
    var   hour=(Array(2).join("0") + (_time.getHours())).slice(-2);
    var   minute=(Array(2).join("0") + (_time.getMinutes())).slice(-2);
    return   (year+"-"+month+"-"+date+" "+hour+":"+minute);//2014-01-02 11:42
}
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
            '<td style="padding: 0">\n' +
            '<input type="text" class="form-control" value="'+list[i].prescriptionName+'" '+((status==="暂存")?"":'readonly="readonly"')+'>\n' +
            '</td>\n' +
            '<td>\n' +
            '<select class="custom-select-sm">\n' +
            '<option value="0" '+selectItem[0]+'></option>\n' +
            '<option value="1" '+selectItem[1]+'>普诊</option>\n' +
            '<option value="2" '+selectItem[2]+'>急诊</option>\n' +
            '<option value="3" '+selectItem[3]+'>专诊</option>\n' +
            '</select>\n' +
            '</td>\n' +
            '<td>'+getTime2(list[i].buildTime)+'</td>\n' +
            '<td class="'+allColor+'">'+status+'</td>\n' +
            '<td>'+list[i].prescriptionInAmount+'</td>\n' +
            '</tr>');
    }
}
function prescriptionAjax(){
    var medicalInfoId=$("#patientInfoDiv span:eq(1)").html();
    if(medicalInfoId==="")
        return;
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForPrescription/getPrescription/"+$("#prescriptionType").val()+"/"+medicalInfoId,
        data: {},
        success: function (result) {
            setPrescriptionList(result);
        }
    });
}
//初始化处方界面
$("[href='#menu3']").click(function () {
    var name=$(this).html();
    $("#prescriptionForm h4").html(name+"处方");
    $("#prescriptionCard .card-header").html(name+"处方列表");
    $("#menu3RightNav a:eq(1) small").html(name+"组套");
    $("#menu3_1 .card-header").html(name+"药品");
    var node=$("#prescriptionType");
    if(name==="成药")
        node.val("1");
    else node.val("2");
    $("#menu3RightNav a:eq(0)").click();
    $("#prescriptionForm tbody").html("");//todo
    changePrescriptionBtns(1);
    prescriptionAjax();
});
//添加药品列表
function setPrescriptionDetail(prescriptionDetailList,drugsList) {
    debugger;
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
            '<td style="padding: 0;width: 60px"><input class="form-control " name="consumption" type="text" value="'+prescriptionDetailList[i].consumption+'" '+readonlyFlag+'></td>\n' +
            '<td>\n' +
            '<select name="frequent" class="custom-select-sm"><!--频次,1一天一次 ，2一天两次 3一天三次 4其他-->\n' +
            '<option value="0" '+selectFrequent[0]+'></option><option value="1" '+selectFrequent[1]+'>一天一次</option>\n' +
            '<option value="2" '+selectFrequent[2]+'>一天两次</option><option value="3" '+selectFrequent[3]+'>一天三次</option>\n' +
            '<option value="4" '+selectFrequent[4]+'>其他</option>\n' +
            '</select>\n' +
            '</td>\n' +
            '<td style="padding: 0"><input class="form-control " name="days" type="number" value="'+prescriptionDetailList[i].days+'" '+readonlyFlag+'> </td>\n' +
            '<td style="padding: 0"><input class="form-control " name="amount" type="number" value="'+prescriptionDetailList[i].amount+'" '+readonlyFlag+'> </td>\n' +
            '</tr>\n' +
            '<tr>\n' +
            '<td colspan="4" style="padding: 0">\n' +
            '<div class="input-group">\n' +
            '<div class="input-group-prepend">\n' +
            '<span class="input-group-text font-weight-bold">用药嘱托:</span>\n' +
            '</div>\n' +
            '<input type="text" class="form-control" name="entrustment" value="'+prescriptionDetailList[i].entrustment+'" '+readonlyFlag+'>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td style="padding: 0" align="center"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<input type="hidden" name="drugsId" value="'+drugsList[i].id+'">'+
            '</tr>');
    }
}
//选择查看处方明细
$("#prescriptionCard").on("click","tr",function () {
    if($(this).find(":radio").val()==="-1"||$(this).find(":radio").val()===undefined)
        return;
    else if($("#prescriptionCard :radio:checked").val()==="-1"){
        showAlertDiv3("alert-danger", "危险!", "存在未保存的信息，先删除或暂存。");
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
        success: function (result) {
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
        showAlertDiv3("alert-danger", "危险!", "存在未保存的信息，先删除或暂存。");
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
        '<td style="padding: 0">\n' +
        '<input type="text" class="form-control" placeholder="请输入名称" >\n' +
        '</td>\n' +
        '<td>\n' +
        '<select class="custom-select-sm">\n' +
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
    debugger;
    var node=$("#prescriptionCard :radio:checked");
    var trNode=node.closest("tr");
    //判断提交内容是否完整
    if(trNode.find(":text").val()===""||trNode.find(":selected").val()==="0"||$("#drugsCard tbody").html()===""){
        showAlertDiv3("alert-warning","警告!","请输入名称，选择类型和至少一个药品。");
        return;
    }
    var flag=0;
    $("#drugsCard [name='entrustment']").each(function () {
        if($(this).val()===""){
            showAlertDiv3("alert-warning","警告!","请输入用药嘱托。");
            flag=1;
            return false;
        }
    });
    $("#drugsCard :selected").each(function () {
        if($(this).val()==="0"){
            showAlertDiv3("alert-warning","警告!","请完成选择。");
            flag=1;
            return false;
        }
    });
    $("#drugsCard [type='number']").each(function () {
        var num=$(this).val();
        if(num===""||num<=0||(num%1 !== 0)){
            showAlertDiv3("alert-warning","警告!","天数和数量必须是大于0的整数。");
            flag=1;
            return false;
        }
    });
    $("#drugsCard [name='consumption']").each(function () {
        var num=$(this).val();
        var reg=/^\d+(\.\d+)?$/;
        if(!reg.test(num)||num==="0"){
            showAlertDiv3("alert-warning","警告!","用量是必须大于0的数字。");
            flag=1;
            return false;
        }
    });
    if(flag===1)
        return;
    var alertNum=showAlertDiv3("alert-secondary","","处方信息保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForPrescription/setPrescriptionAndDetail/"+$("#prescriptionType").val()+"/"+$(this).index()+"/"+$("#patientInfoDiv span:eq(1)").html()+"/"+node.val()+"/"+trNode.find(":text").val()+"/"+trNode.find(":selected").val()+"/"+trNode.find("td:last").html(),
        data: $("#prescriptionForm").serializeArray(),
        success: function (result) {
            $.outpatientMethod.closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv3("alert-success","成功!","处方信息保存成功。");
                flushPrescriptionPage();
            }
            else showAlertDiv3("alert-warning","警告!","处方信息保存失败。");
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
        success: function (result) {
            if(result==="1")
                showAlertDiv3("alert-success","成功!","删除或作废成功。");
            else if(result==="0")
                showAlertDiv3("alert-warning","警告!","删除或作废失败。");
            else if(result==="2")
                showAlertDiv3("alert-danger","错误!","该处方已付费或退费，无法作废。");
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
    //todo
    var fmeitemList=getVisitItemFmeitemData();
    var setSubList=[];
    $("#visitItemCard tbody tr").each(function () {
        setSubList.push({entrust:$(this).find(":text").val()});
    });
    setSetSub(fmeitemList,setSubList);
    disableSetSub(false);
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
        success: function (result) {
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
        list.push({id:$(this).find("td:eq(0)").html(),drugsname:$(this).find("td:eq(1)").html(),drugsformat:$(this).find("td:eq(2)").html()});
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
    apendCheckedDrugs(getSetSubFmitemData(),false);//todo
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
            '<td title="'+list[i].drugsname+'" style="max-width:260px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+list[i].drugsname+'</td>\n' +
            '<td>'+list[i].drugsformat+'</td>\n' +
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
        showAlertDiv3("alert-danger","错误!","异常页码。");
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

//项目 搜索增加
$("#drugsNotCheckedTbody").on("click","tr",function () {
    var id=$(this).children().eq(1).html();
    var oneflag=0;
    var node=$("#drugsCheckedTbody");
    node.children().each(function () {
        if($(this).children().eq(1).html()===id){
            showAlertDiv3("alert-warning","警告!","已选择。");
            oneflag=1;
            return false;
        }
    });
    if(oneflag===1)
        return;
    node.append("<tr>"+$(this).html()+"</tr>")
        .find("input").last().attr("checked",'true');
});
//项目 搜索删除
$("#drugsCheckedTbody").on("click","tr",function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        $(this).remove();
    }
});