var patientList;//保存病人信息列表
var patient;//病人信息
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
        url: urlS,
        data: {},
        success: function (result) {
            var isSeenList = result.isSeenList;
            var notSeenList = result.notSeenList;
            patientList=isSeenList.concat(notSeenList);
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
    $("#searchPatientTbody1").html("");
    $("#searchPatientTbody2").html("");
    $("#notSeenNumSpan").html(0);
    $("#isSeenNumSpan").html(0);
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

//点击表格设置患者信息
$("#searchPatientTbody1,#searchPatientTbody2").on("click","tr",function () {
    var radio=$(this).find("input");
    radio.prop('checked','true');
    var no=parseInt(radio.val());
    for (var i=0; i < patientList.length; i++) {
        if(patientList[i].medicalRecordNo===no){
            patient=patientList[i];//保存到全局病人信息   //todo可能不需要
            break;
        }
    }

});