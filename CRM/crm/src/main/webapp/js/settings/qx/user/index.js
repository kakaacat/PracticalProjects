$(function () {
    //给整个窗口加个键盘按下事件
    $(window).keydown(function (event) {
        //如果按的是回车键，就提交登录
        if (event.keyCode == 13) {
            $("#loginBtn").click();
        }
    });



    //给“登录”按钮添加单击事件
    $("#loginBtn").click(function () {
        //收集参数
        var loginAct = $.trim($("#loginAct").val());
        var loginPwd = $.trim($("#loginPwd").val());
        var isRemPwd = $("#isRemPwd").prop("checked");
        //表单验证
        if (loginAct == "") {
            alert("用户名不能为空！");
            return;
        }
        if (loginPwd == "") {
            alert("密码不能为空！");
            return;
        }

        //显示正在验证
        $("msg").text("正在验证...");

        //发送请求
        $.ajax({
            url:'settings/qx/user/login.do',
            data:{
                loginAct:loginAct,
                loginPwd:loginPwd,
                isRemPwd:isRemPwd
            },
            type:'post',
            dataType:'json',
            success:function (data) {
                if (data.code == "1") {
                    //跳转到业务主界面
                    window.location.href="workbench/index.do";
                } else {
                    //提示信息
                    $("#msg").html(data.message);
                }
            },
            beforeSend:function(){
                $("msg").text("正在验证...");
                return true;
            }
        });
    });
});