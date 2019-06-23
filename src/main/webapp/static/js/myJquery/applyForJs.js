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
    return   (year+"-"+month+"-"+date+" "+hour+":"+minute);//2014-01-02 11:42
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
        var allColor=["text-info","text-primary","text-warning","text-danger","text-success"];
        allColor=allColor[eval(status) - 1];
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
            '<td class="'+allColor+'">'+status+'</td>\n' +
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
    $("#menu1_1 .card-header").html(name+"项目");
    var node=$("#applyForType");
    if(name==="检查")
        node.val("1");
    else if (name === "检验")
        node.val("2");
    else node.val("3");
    $("#menu1RightNav a:eq(0)").click();
    $("#visitItemForm tbody").html("");
    changeApplyForBtns(1);
    applyForItemAjax();
});
//添加项目明细表
function setVisitItemList(visitDetailList,fmeditemList) {
    var readonlyFlag=$("#applyForCard :checked").closest("tr").find("td:eq(3)").html();
    if (readonlyFlag === "暂存"||readonlyFlag === ""||readonlyFlag===undefined)
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
            '<td style="padding: 0" align="center"><a href="#"><img src="images/save_icon.jpg" style="height:40px;width:40px" alt="保存"></a></td>\n' +
            '<td class="text-center" style="padding-left: 0;padding-right: 0"><button type="button" class="btn btn-primary btn-sm" style="width: 100%;height: 100%" data-toggle="modal" data-target="#ResultModal">查看结果</button></td>\n' +
            '<input type="hidden" name="fmeditemId" value="'+fmeditemList[i].id+'">'+
            '</tr>');
    }
}
//选择查看申请明细
$("#applyForCard").on("click","tr",function () {
    if($(this).find(":radio").val()===$("#applyForCard :checked").val()||$(this).find(":radio").val()===undefined)
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
        data: {},
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
//存为组套
$("#applyForBtnGroup button:eq(5)").click(function () {
    $("#menu1RightNav a:last").click();
    $("#itemSetBtnGroup :eq(0)").click();
    var fmeitemList=getVisitItemFmeitemData();
    var setSubList=[];
    $("#visitItemCard tbody tr").each(function () {
        setSubList.push({entrust:$(this).find(":text").val()});
    });
    setSetSub(fmeitemList,setSubList);
    disableSetSub(false);
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

function initeItemModal(){
    var node=$("#ItemModal");
    node.find("tbody").html("");//清空表格
    node.find("#itemKey").val("");
    $("#itemAllAmountDiv").show();
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
//获取项目列表非药品信息的list
function getVisitItemFmeitemData(){
    var list=[];
    $("#visitItemCard tbody tr").each(function () {
        list.push({id:$(this).find("td:eq(0)").html(),itemname:$(this).find("td:eq(1)").html(),price:$(this).find("td:eq(2)").html()});
    });
    return list;
}
//项目列表修改按钮 弹窗
$("#visitItemCard button:eq(0)").click(function () {
    initeItemModal();
    var list=getVisitItemFmeitemData();
    apendCheckedItem(list,false);
});
//组套子项的修改
$("#itemSetContextForm button[data-target='#ItemModal']:contains('修改')").click(function () {
    initeItemModal();
    $("#ItemModal button:contains('导入结果')").hide();
    $("#ItemModal button:contains('保存')").show();
    $("#itemAllAmountDiv").hide();
    apendCheckedItem(getSetSubFmitemData(),false);
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

//获得项目弹窗右侧的选择数据结果
function getItemCheckedFmeditemData(){
    var fmeditemList=[];
    $("#itemCheckedTbody tr").each(function () {
        fmeditemList.push({id:$(this).find("td:eq(1)").html(),itemname:$(this).find("td:eq(2)").html(),price:$(this).find("td:eq(3)").html()});
    });
    return fmeditemList;
}
//导入项目结果，求同存异
$("#ItemModal .modal-footer :button:contains('导入结果')").click(function () {
    var fmeditemList=getItemCheckedFmeditemData();
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
    calcuteAmount();
    $("#ItemModal button[data-dismiss='modal']").click();
});
//计算项目列表所有金额写到选定行
function calcuteAmount() {
    var amountNode=$("#applyForCard :checked").closest("tr").children().last();
    if(amountNode.html()!==undefined){
        var amount=0;
        $("#visitItemCard tbody tr").each(function () {
            amount+=eval($(this).find("td:eq(2)").html());
        });
        amountNode.html(amount);
    }
}
//保存到组套子项 求同存异
$("#ItemModal .modal-footer :button:contains('保存')").click(function () {
    var fmeditemList=getItemCheckedFmeditemData();
    //倒序循环，找到则删除list，找不到则删除原有
    for(var i=$("#itemSetContextForm tbody tr").length-1;i>=0;i--){
        var anId=$("#itemSetContextForm tbody tr:eq("+i+")").find("[name='objectId']").val();
        var flag=0;
        for (var j = 0; j < fmeditemList.length; j++) {
            if (fmeditemList[j].id === anId) {
                fmeditemList.splice(j, 1);
                flag = 1;
                break;
            }
        }
        if(flag===0){
            $("#itemSetContextForm tbody").find("tr:eq("+i+")").remove();
        }
    }
    //生成空列
    var setSubList=[];
    for (var k = 0; k < fmeditemList.length; k++) {
        setSubList.push({entrust:""});
    }
    setSetSub(fmeditemList,setSubList);
    disableSetSub(false);
    $("#ItemModal button[data-dismiss='modal']").click();
});

//放入常用项目
function insertCommonItem(list){
    var str="";
    for (var i = 0; i < list.length; i++) {
        str+='<a href="#" class="list-group-item list-group-item-action"><span>'+list[i].itemname+'</span><span class="badge badge-pill badge-danger">X</span></a>\n' +
            '<input type="hidden" value="'+list[i].id+'">'+
            '<input type="hidden" value="'+list[i].price+'">';
    }
    $("#menu1_1 .list-group").html(str);
}
//常用项目生成
$("#menu1RightNav a:eq(0)").click(function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForFmeditem/getCommonOption/"+$("#applyForType").val(),
        data: {},
        success: function (result) {
            insertCommonItem(result);
        }
    });
});
//双击将常用项目加到项目列表下
$("#menu1_1").on("dblclick","a",function () {
    var flag=0;
    var itemId=$(this).next().val();
    $("#visitItemCard tbody").children().each(function () {
        if($(this).children().eq(0).html()===itemId){
            flag=1;
            return false;//break
        }
    });
    if(flag===1){
        showAlertDiv2("alert-warning","警告!","该项目已存在。");
        return;//重复跳出
    }
    var fmeditemList=[{id:itemId,itemname:$(this).find("span:eq(0)").html(),price:$(this).next().next().val()}];
    var visitDetailList=[{doctorEntrustment:"",executionStatus:"0"}];
    setVisitItemList(visitDetailList,fmeditemList);
    calcuteAmount();
    showAlertDiv2("alert-success","成功!","项目插入成功。");
})//删除常用项目
    .on("click",".badge-danger",function () {
        var res = confirm('确认要删除吗？');
        if(res === true) {
            $.ajax({
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "applyForFmeditem/deleteCommonItem/" + $("#applyForType").val() + "/" + $(this).parent().next().val(),
                data: {},
                success: function (result) {
                    $("#menu1RightNav a:eq(0)").click();
                }
            });
        }
    })//取消跳转
    .on("click","a",function () {
        return false;
});

//增加常用项目
$("#visitItemCard").on("click","a",function () {
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "applyForFmeditem/addCommonFmeditem/"+$("#applyForType").val()+"/"+$(this).closest("tr").children().first().html(),
        data: {},
        success: function (result) {
            if(result==="1")
                showAlertDiv2("alert-success","成功!","增加常用项目成功。");
            else showAlertDiv2("alert-danger","失败!","增加常用项目失败。");
            $("#menu1RightNav a:eq(0)").click();
        }
    });
    return false;
});

//点击导航直接搜索组套
$("#menu1RightNav a:last").click(function () {
    $("#searchItemSetForm button").click();
    disableSetBtn(true);
    disableSetContext(true);
});
//放入组套的名字到标签
function addSetContext(map){
    var str="";
    for(var key in map){
        str+=' <a href="#" class="list-group-item list-group-item-action">'+map[key]+'</a><input type="hidden" value="'+key+'">';
    }
    $("#itemSetListDiv").html(str);
}
//组套类型选择
$("#itemSetChooseDiv :radio").click(function () {
    searchSetGroup();
});
function searchSetGroup(){
    debugger;
    var category=$("#itemSetChooseDiv :checked").val();
    if(category===undefined){//未选的情况
        category=1;
        $("#itemSetChooseDiv [type='radio']:eq(0)").attr("checked",true);
    }
    var key=$("#searchItemSetKey").val();
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "setManage/getSetGroup/"+category+"/"+$("#applyForType").val()+((key==="")?"":("/"+key)),
        data: {},
        success: function (result) {
            addSetContext(result);
        }
    });
}
//组套搜索
$("#searchItemSetForm").on("click","button",function () {
    searchSetGroup();
}).on("keyup","input",function (e) {
    if(e.keyCode===13){
        searchSetGroup();
    }
});

//清空组套内容
function clearSetContent(){
    $("#itemSetListDiv a").removeClass("active");
    $("#idSet").val("");
    $("#categorySet").val(0);
    $("#itemSetContextForm [type='text']").val("");
    $("#itemSetContextForm").find("span,tbody").html("");
}
//放入组套子项
function setSetSub(fmeitemList,setSubList){
    debugger;
    for (var i=0;i<fmeitemList.length;i++) {
        $("#itemSetContextForm tbody").append('<tr>\n' +
            '<td title="'+fmeitemList[i].itemname+'" style="max-width:160px;overflow:hidden;text-overflow:ellipsis;white-space:nowrap">'+fmeitemList[i].itemname+'</td>\n' +
            '<td style="padding: 0">\n' +
            '<input type="text" class="form-control" name="setSubEntrust" value="'+setSubList[i].entrust+'" readonly="readonly">\n' +
            '<input type="hidden" name="objectId" value="'+fmeitemList[i].id+'">\n' +
            '<input type="hidden" value="'+fmeitemList[i].price+'">\n' +
            '</td>\n' +
            '</tr>');
    }
}
//解锁或锁定组套按钮 true锁定
function disableSetBtn(bool){
    var btns=$("#itemSetBtnGroup").find(".btn-outline-warning,.btn-outline-danger");
    if(bool===true)
        btns.hide();
    else  btns.show();
}
//点击标签获得组套内容
$("#itemSetListDiv").on("click","a",function () {
    //变色
    $("#itemSetListDiv a").removeClass("active");
    $(this).addClass("active");
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "setManage/getSetContent/"+$("#applyForType").val()+"/"+$(this).next().val(),
        data: {},
        success: function (result) {
            $("#setCodeTemplate").attr("readonly",true);
            $("#itemSetCategoryDiv").hide();
            disableSetContext(true);
            if(result.setGroup===null){
                showAlertDiv2("alert-warning","警告!","组套不存在。");
                return;
            }
            var setGroup=result.setGroup;
            if(eval($("#doctorId").val())===setGroup.createrId)//判断是否能修改删除
                disableSetBtn(false);
            else disableSetBtn(true);
            $("#idSet").val(setGroup.id);
            $("#categorySet").val(eval(setGroup.useScope));
            $("#setCodeTemplate").val(setGroup.setCode);
            $("#setNameTemplate").val(setGroup.setName);
            $("#setCreatTime").html(getTime1(setGroup.buildDate));
            $("#itemSetContextForm tbody").html("");
            setSetSub(result.objectList,result.setSubList);
        }
    });
    return false;
});
//获得组套子项的非药品信息
function getSetSubFmitemData() {
    var fmeditemList=[];
    $("#itemSetContextForm tbody tr").each(function () {
        fmeditemList.push({id:$(this).find("[name='objectId']").val(),itemname:$(this).find("td:eq(0)").html(),price:$(this).find("[type='hidden']:eq(1)").val()});
    });
    return fmeditemList;
}
//组套子项插入主体
function addSetSub(){
    var fmeditemList=getSetSubFmitemData();
    var setSubList=[];
    $("#itemSetContextForm tbody tr").each(function () {
        setSubList.push({doctorEntrustment:$(this).find("[name='setSubEntrust']").val(), executionStatus: "0"});
    });
    //删除已存在
    $("#visitItemCard tbody tr").each(function () {
        var anId=$(this).find("td:first").html();
        for (var i = fmeditemList.length-1; i >=0; i--) {
            if (fmeditemList[i].id === anId) {
                fmeditemList.splice(i, 1);
                setSubList.splice(i, 1);
                break;
            }
        }
    });
    setVisitItemList(setSubList,fmeditemList);
    calcuteAmount();
}
//引用组套
$("#itemSetBtnGroup button:contains('引用')").click(function () {
    addSetSub();
});
//删除组套
$("#itemSetBtnGroup button:eq(2)").click(function () {
    var res = confirm('确认要删除吗？');
    if(res === true){
        if($("#idSet").val()!==""){
            $.ajax({
                type: "POST",//方法类型
                dataType: "text",//预期服务器返回的数据类型
                url: "setManage/cancelSetGroup/"+$("#idSet").val(),
                data: {},
                success: function (result) {
                    if(result==="0")//好像无效
                        showAlertDiv2("alert-danger","错误!","删除组套失败。");
                    $("#itemSetChooseDiv :checked").click();
                }
            });
        }else showAlertDiv2("alert-danger","错误!","删除组套失败，请重新选择。");
        disableSetBtn(true);
        clearSetContent();
        disableSetContext(true);
    }
});
//锁定组套内容或解开 bool true锁
function disableSetContext(bool){
    $("#setNameTemplate").attr("readonly",bool);
    var btns=$("#itemSetContextForm button");
    if(bool)
        btns.hide();
    else btns.show();
}
//锁定组套子项的输入框 bool true锁定
function disableSetSub(bool){
    $("#itemSetContextForm tbody :text").attr("readonly",bool);
}
// 修改增加组套后提交
$("#itemSetContextForm button:contains('提交')").click(function () {
    var codeNode=$("#setCodeTemplate");
    if(codeNode.val()===""||$("#categorySet").val()==="0"){
        showAlertDiv2("alert-warning", "警告!", "组套编码和适用范围不能为空。");
        codeNode.focus();
        return;
    }
    var alertNum=showAlertDiv2("alert-secondary","","组套保存中...");
    $.ajax({
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: "setManage/saveSetGroup/"+((codeNode.attr("readonly")==="readonly")?"2":"1")+"/"+$("#applyForType").val(),
        data: $("#itemSetContextForm").serializeArray(),
        success: function (result) {//1成功 0更新失败（已删除） 2新增失败（code已存在）
            $.outpatientMethod.closeAlertDiv(alertNum);
            if(result==="1"){
                showAlertDiv2("alert-success","成功!","组套提交成功。");
                $("#itemSetCategoryDiv").hide();
                codeNode.attr("readonly",true);
                disableSetSub(true);
                disableSetContext(true);
                $("#itemSetListDiv .active").click();
            }
            else if(result==="2") {
                showAlertDiv2("alert-warning", "警告!", "当前分类下组套编码已存在。");
                codeNode.focus();
            }
            else {
                showAlertDiv2("alert-warning", "警告!", "当前分类下组套不存在。");
            }
        }
    });
});
//修改组套
$("#itemSetBtnGroup").on("click","button:eq(1)",function () {
    $("#setCodeTemplate").attr("readonly",true);
    disableSetSub(false);
    disableSetContext(false);
}).on("click","button:eq(0)",function () {//新增组套
    $("#itemSetCategoryDiv").show();
    $("#setCodeTemplate").attr("readonly",false);
    disableSetContext(false);
    clearSetContent();
    disableSetBtn(true);
});


//查看项目结果
$("#visitItemCard tbody").on("click","button",function () {
    var node=$("#applyForCard :checked");
    var status=node.closest("tr").find("td:eq(3)").html();
    var visitItemNo=node.val();
    $("#ResultModal span").html("");
    $("#ResultModal img").attr("src", "");
    $("#ResultModal span:first").html($(this).closest("tr").find("td:eq(1)").html());
    if (visitItemNo === undefined||status===""||status==="暂存")
        showAlertDiv2("alert-warning","警告!","未选择申请单或未开立过。");
    else
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "applyForFmeditem/getItemResult/"+visitItemNo+"/"+$(this).closest("tr").find("td:first").html(),
            data: {},
            success: function (result) {
                if (result.describetion === null) {
                    showAlertDiv2("alert-warning","警告!","未找到项目结果。");
                    return;
                }
                $("#ResultModal span:last").html(result.describetion);
                $("#ResultModal img").attr("src", result.picture);
            }
        });
});