$(function(){
    //日历函数
    $(".mydate").datetimepicker({
        language : 'zh-CN',
        format : 'yyyy-mm-dd',
        minView : 'month',
        initialDate : new Date(),
        autoclose : true,
        todayBtn : true,
        clearBtn : true
    });

    //给“创建”按钮添加点击事件
    $("#createClueBtn").click(function () {
        //清空输入框
        $("#createClueForm")[0].reset();
        //显示模态窗口
        $("#createClueModal").modal("show");
    });

    //给“保存”按钮添加单击事件
    $("#saveCreateClueBtn").click(function () {
        //收集参数
        var fullname          =  $.trim($("#create-fullname").val());
        var appellation       =  $("#create-appellation").val();
        var owner             =  $("#create-owner").val();
        var company           =  $.trim($("#create-company").val());
        var job               =  $.trim($("#create-job").val());
        var email             =  $.trim($("#create-email").val());
        var phone             =  $.trim($("#create-phone").val());
        var website           =  $.trim($("#create-website").val());
        var mphone            =  $.trim($("#create-mphone").val());
        var state             =  $("#create-state").val();
        var source            =  $("#create-source").val();
        var description       =  $.trim($("#create-description").val());
        var contactSummary    =  $.trim($("#create-contactSummary").val());
        var nextContactTime   =  $.trim($("#create-nextContactTime").val());
        var address           =  $.trim($("#create-address").val());


        //表单验证
        if (appellation == "--请选择--") {
            alert("请选择称谓！");
            return;
        }
        if (state == "--请选择--") {
            alert("请选择线索状态！");
            return;
        }
        if (source == "--请选择--") {
            alert("请选择线索来源！");
            return;
        }

        if (owner == "") {
            alert("所有者不能为空！");
            return;
        }
        if (company == "") {
            alert("公司不能为空！");
            return;
        }
        if (fullname == "") {
            alert("姓名不能为空！");
            return;
        }
        var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!regExp.test(email)) {
            alert("邮箱不合法！");
            return;
        }
        var regExp = /\d{3}-\d{8}|\d{4}-\d{7}/;
        if (!regExp.test(phone)) {
            alert("公司座机不合法！");
            return;
        }
        var regExp = /^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\d{8}$/;
        if (!regExp.test(mphone)) {
            alert("手机号不合法！");
            return;
        }



        //发送请求
        $.ajax({
            url: 'workbench/clue/saveClue.do',
            data: {
                fullname: fullname,
                appellation: appellation,
                owner: owner,
                company: company,
                job: job,
                email: email,
                phone: phone,
                website: website,
                mphone: mphone,
                state: state,
                source: source,
                description: description,
                contactSummary: contactSummary,
                nextContactTime: nextContactTime,
                address: address
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#createClueModal").modal("hide");
                    //刷新列表

                } else {
                    //模态窗口不关闭
                    $("#createClueModal").modal("show");
                }
            }
        });

    });


});

