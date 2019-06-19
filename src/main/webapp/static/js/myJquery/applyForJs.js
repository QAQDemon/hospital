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
    var medicalInfoId=$("#patientInfoDiv span:eq(1)").html();
    if(medicalInfoId==="")
        return;
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForFmeditem/getVisitItemList/"+$("#applyForType").val()+"/"+medicalInfoId,
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
            '<input type="text" class="form-control" name="doctorEntrustment" value="'+visitDetailList[i].doctorEntrustment+'" '+readonlyFlag+'>\n' +
            '</td>\n' +
            '<td class="text-center">'+((visitDetailList[i].executionStatus==="0")?'X':'√')+'</td>\n' +
            '<td class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary btn-sm" style="width: 100%;height: 100%">查看结果</button></td>\n' +
            '<input type="hidden" name="fmeditemId" value="'+fmeditemList[i].id+'">'+
            '</tr>');
    }

}
//选择查看申请明细
$("#applyForCard").on("click","tr",function () {
    if($(this).find(":radio").val()==="-1"||$(this).find(":radio").val()===undefined)
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

//项目 搜索
var itemList;//疾病信息列
var itemFlag;//flag 1创建 2不创建页码并跳过
var itemPages;//总页数
function addSearchItem() {
    $("#itemNotCheckedTbody").html("");
    apendCheckedItem(itemList, true);
}
function itemPageselectCallback(page_index,jq){
    if(!(itemFlag===1&&page_index===0)){
        itemFlag=2;
        searchItemAjax(page_index+1);
    }
    addSearchItem();
    return false;
}
function searchItemAjax(pageNum){
    var urlS="applyForFmeditem/searchItem/"+$("#applyForType").val()+"/"+pageNum;
    var inputKey=$("#itemKey").val();
    if(inputKey!=="")
        urlS += ("/" + inputKey);
    $.ajax({
        type: "POST",//方法类型
        async:false,//防止分页错误
        dataType: "json",//预期服务器返回的数据类型
        url: urlS,
        data: {},
        success: function (result) {
            itemList=result.itemList;
            itemPages=result.pages;
            if(itemFlag===1){
                var initPagination = function() {
                    // 创建分页
                    $("#itemPagination").pagination(itemPages, {
                        num_edge_entries: 1, //边缘页数
                        num_display_entries: 4, //主体页数
                        callback: itemPageselectCallback
                    });
                }();
            }
        }
    });
}

function initeItemModel(){
    var node=$("#ItemModal");
    node.find("tbody").html("");//清空表格
    node.find("#itemKey").val("");
    $("#itemPagination").html("");
    $("#itemPageJump").hide();
    $("#searchItemPage").val("");
    $("#ItemModal button:contains('导入结果')").show();
    $("#ItemModal button:contains('保存')").hide();
    var amount=$("#applyForCard :checked").closest("tr").children().last().html();
    if(amount!==undefined)
        $("#itemModalAmount").html(amount);
    else $("#itemModalAmount").html("0");
}
//项目列表修改按钮 弹窗
$("#visitItemCard button:eq(0)").click(function () {
    initeItemModel();
    var list=[];
    $("#visitItemCard tbody tr").each(function () {
        list.push({id:$(this).find("td:eq(0)").html(),itemname:$(this).find("td:eq(1)").html(),price:$(this).find("td:eq(2)").html()});
    });
    apendCheckedItem(list,false);
});

//将内容导入项目弹窗的左右侧 bool true左 false右
function apendCheckedItem(list,bool) {
    var node;
    if (bool)
        node=$("#itemNotCheckedTbody");
    else
        node=$("#itemCheckedTbody");
    for(var i=0;i<list.length;i++){
        node.append('<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-checkbox" >\n' +
            '<input type="checkbox" class="custom-control-input" id="itemCheck'+((bool)?'0':'1')+i+'" name="itemCheckGroup'+((bool)?'0"':'1" checked')+'/>\n' +
            '<label class="custom-control-label" for="itemCheck'+((bool)?'0':'1')+i+'"></label>\n' +
            '</div>\n' +
            '</td>\n' +
            '<td>'+list[i].id+'</td>\n' +
            '<td title="'+list[i].itemname+'" style="max-width:260px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+list[i].itemname+'</td>\n' +
            '<td>'+list[i].price+'</td>\n' +
            '</tr>');
    }
}
$("#ItemModal").on("click",":checkbox",function () {
    return false;//防止二次点击
});
function searchItemMethod(){
    itemFlag=1;
    searchItemAjax(1);
    $("#itemPageJump").show();
}
$("#itemSearchInput").on("click","button",function () {
    searchItemMethod();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchItemMethod();
    }
});

//跳转
function itemPageJumpMethod(){
    var num=$("#searchItemPage").val();
    if(num==null||num<=0||num>itemPages||(num%1 !== 0)){
        showAlertDiv2("alert-danger","错误!","异常页码。");
        return;
    }
    var current=$("#itemPagination .current").html();
    if(current===num)//可排除第一次跳第一页
        return;
    itemFlag=2;
    searchItemAjax(num);
    $("#itemPagination").html("");
    var initPagination = function() {
        // 更改分页
        $("#itemPagination").pagination(itemPages, {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            callback: itemPageselectCallback,
            current_page:num-1
        });
    }();
    addSearchItem();
}
$("#itemPageJump").on("click","button",function () {
    itemPageJumpMethod();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        itemPageJumpMethod();
    }
});

//项目 搜索增加
$("#itemNotCheckedTbody").on("click","tr",function () {
    var id=$(this).children().eq(1).html();
    var oneflag=0;
    var node=$("#itemCheckedTbody");
    node.children().each(function () {
        if($(this).children().eq(1).html()===id){
            showAlertDiv2("alert-warning","警告!","已选择。");
            oneflag=1;
            return false;
        }
    });
    if(oneflag===1)
        return;
    node.append("<tr>"+$(this).html()+"</tr>")
        .find("input").last().attr("checked",'true');
    //增加总额
    var amountNode=$("#itemModalAmount");
    var amount=amountNode.html();
    amountNode.html(eval(amount)+eval($(this).find("td:eq(3)").html()));
});
//项目 搜索删除
$("#itemCheckedTbody").on("click","tr",function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        //减少总额
        var amountNode=$("#itemModalAmount");
        var amount=amountNode.html();
        amountNode.html(eval(amount)-eval($(this).find("td:eq(3)").html()));
        $(this).remove();
    }
});

//导入项目结果，求同存异
$("#ItemModal .modal-footer :button:contains('导入结果')").click(function () {
    //将选择项目放入列表
    var fmeditemList=[];
    $("#itemCheckedTbody tr").each(function (index) {
        fmeditemList.push({id:$(this).find("td:eq(1)").html(),itemname:$(this).find("td:eq(2)").html(),price:$(this).find("td:eq(3)").html()});
    });
    //原有项目倒序循环，找到则删除list，找不到则删除原有
    for(var i=$("#visitItemCard tbody tr").length-1;i>=0;i--){
        var anId=$("#visitItemCard tbody tr:eq("+i+")").find("td:eq(0)").html();
        var flag=0;
        for (var j = 0; j < fmeditemList.length; j++) {
            if (fmeditemList[j].id === anId) {
                fmeditemList.splice(j, 1);
                flag = 1;
                break;
            }
        }
        if(flag===0){
            $("#visitItemCard tbody").find("tr:eq("+i+")").remove();
        }
    }
    //生成空列
    var visitDetailList=[];
    for (var k = 0; k < fmeditemList.length; k++) {
        visitDetailList.push({doctorEntrustment:"", executionStatus: "0"});
    }
    setVisitItemList(visitDetailList,fmeditemList);
    //计算amount
    var amountNode=$("#applyForCard :checked").closest("tr").children().last();
    if(amountNode.html()!==undefined){
        var amount=0;
        $("#visitItemCard tbody tr").each(function () {
            amount+=eval($(this).find("td:eq(2)").html());
        });
        amountNode.html(amount);
    }
    $("#ItemModal button[data-dismiss='modal']").click();
});