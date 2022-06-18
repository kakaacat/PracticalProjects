$(function(){

    //定制字段
    $("#definedColumns > li").click(function(e) {
        //防止下拉菜单消失
        e.stopPropagation();
    });

    //给“创建”按钮添加事件
    $("#createContactsBtn").click(function () {
        //清空表单
        $("#createContactsForm")[0].reset();
        //打开模态窗口
        $("#createContactsModal").modal("show");
    });

    //客户名称自动补全
    $("#create-customerName").typeahead({
        source: function (jquery, process) {
            $.ajax({
                url: 'workbench/contacts/queryCustomerByName.do',
                data: {
                    name: jquery
                },
                type: 'post',
                dataType: 'json',
                success:function (data) {
                    process(data);
                }
            });
        }
    });

    //给“保存”按钮添加事件
    $("#saveCreateContactsBtn").click(function () {
        //收集参数
        var owner = $("#create-contactsOwner").val();
        var source = $("#create-clueSource").val();
        var fullname = $("#create-surname").val();
        var appellation = $("#create-call").val();
        var job = $("#create-job").val();
        var mphone = $("#create-mphone").val();
        var email = $("#create-email").val();
        //var customerId = $("#create-customerName").val();
        var customerId = "805d14c7ba724ad795155d3292207432";
        var description = $("#create-describe").val();
        var contactSummary = $("#create-contactSummary1").val();
        var nextContactTime = $("#create-nextContactTime1").val();
        var address = $("#edit-address1").val();

        //表单验证
        if (owner == "") {
            alert("所有者不能为空");
            return;
        }
        if (fullname == "") {
            alert("名称不能为空");
            return;
        }
        if (source == "" || source == "---请选择---") {
            alert("请选择来源！");
            return;
        }
        if (appellation == "" || appellation == "---请选择---") {
            alert("请选择称谓！");
            return;
        }
        var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!regExp.test(email)) {
            alert("邮箱不合法！");
            return;
        }
        var regExp = /^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\d{8}$/;
        if (!regExp.test(mphone)) {
            alert("手机号不合法！");
            return;
        }

        //发送请求
        $.ajax({
            url: 'workbench/contacts/saveContacts.do',
            data: {
                owner : owner,
                source : source,
                fullname : fullname,
                appellation : appellation,
                job : job,
                mphone : mphone,
                email : email,
                customerId : customerId,
                description : description,
                contactSummary : contactSummary,
                nextContactTime : nextContactTime,
                address : address
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#createContactsModal").modal("hide");
                    //刷新页面

                } else{
                    //模态窗口不关闭
                    $("#createContactsModal").modal("show");
                    alert(data.message);
                }
            }
        });
    });

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




    
});
