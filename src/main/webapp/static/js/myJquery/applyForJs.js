var alertFlag=100;

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
//添加申请单列表
function setApplyForList(list,applyForPeople) {
    for (var i = 0; i < list.length; i++) {
        var status=list[i].status;//1暂存 2开立 3未开立删除 4开立后作废 5完成
        var allStatus=["暂存","开立","删除","作废","完成"];
        status = allStatus[eval(status) - 1];
        $("#visitItemForm tbody:eq(0)").append('<tr>\n' +
            '<td>\n' +
            '<div class="custom-control custom-radio">\n' +
            '<input type="radio" class="custom-control-input" id="applyForRadio'+i+'" name="id" value="'+list[i].id+'">\n' +
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
    // $("#visitItemForm tbody").html("");//todo
}
function applyForItemAjax(){
    $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "applyForFmeditem/getVisitItemList/"+$("#applyForType").val()+"/"+medicalRecordInfoId,
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
    applyForItemAjax();
});

//选择查看申请明细
$("#applyForCard tr").click(function () {
   alert();
});