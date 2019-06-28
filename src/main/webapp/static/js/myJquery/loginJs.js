//将jwt的token放入cookie
function setTokenToCookie(key,value) {
    var Days = 1; //此 cookie 将被保存 30 天
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = key+"=" + escape(value) + ";expires=" + exp.toGMTString();
}
//获得cookie的token
function getCookie(name) {
    var cookieValue = "啥也没有！！";
    if (document.cookie && document.cookie !== '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = $.trim(cookies[i]);
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}
//生成一个隐藏的表单使用post跳转到controller
function submitUserInfo(userId,userName,userCategory){
    var $form=$(document.createElement('form')).css({display:'none'}).attr("method","POST").attr("action","loginController/loginSuccess");
    var $input=$(document.createElement('input')).attr('name',"userId").val(userId);
    var $input1=$(document.createElement('input')).attr('name','userName').val(userName);
    var $input2=$(document.createElement('input')).attr('name','userCategory').val(userCategory);
    $form.append($input).append($input1).append($input2);
    $("body").append($form);
    $form.submit();
}
$("#submitButton").click(function () {
    $('form').fadeOut(500);
    $('.wrapper').addClass('form-success');
    var name=$("#loginName").val();
    var password=$("#password").val();
    if(name===""||password===""){
        setTimeout(function () {
            $('form').fadeIn(500);
            $('.wrapper').removeClass('form-success');
        },1000);
        showAlertDiv("alert-warning","警告!","用户名或密码不能为空。");
    }
    else{
        setTimeout(function () {
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "loginController/login",
                data: $("#loginForm").serializeArray(),
                async: false,
                success: function(data, textStatus, request) {
                    var id=data.userId;
                    if(id!=="-1"){
                        setTokenToCookie("token",request.getResponseHeader('Authorization'));
                        setTokenToCookie("userId",id);
                        submitUserInfo(id,data.userName,data.userCategory);
                    }else {
                        showAlertDiv("alert-warning","警告!","用户名或密码错误。");
                        $('form').fadeIn(500);
                        $('.wrapper').removeClass('form-success');
                    }
                }
            });
        },1000);
    }
});

