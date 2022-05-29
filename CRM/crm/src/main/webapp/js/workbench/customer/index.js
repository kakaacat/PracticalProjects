$(function(){

    //定制字段
    $("#definedColumns > li").click(function(e) {
        //防止下拉菜单消失
        e.stopPropagation();
    });

    //给“创建”按钮添加事件
    $("#createCustomerBtn").click(function () {
        //刷新重置
        $("#createCustomerForm")[0].reset();
        //弹出创建窗口
        $("#createCustomerModal").modal("show");
    });

    //给“保存”按钮添加点击事件
    $("#saveCreateCustomerBtn").click(function () {
        //收集参数
        var owner = $("#create-customerOwner").val();
        var name = $("#create-customerName").val();
        var website = $("#create-website").val();
        var phone = $("#create-phone").val();
        var description = $("#create-describe").val();
        var contactSummary = $("#create-contactSummary").val();
        var nextContactTime = $("create-nextContactTime").val();
        var address = $("#create-address1").val();

        //表单验证
        if (owner == "") {
            alert("所有者不能为空！");
            return;
        }
        if (name == "") {
            alert("名称不能为空！")
            return;
        }
        var regExp = /\d{3}-\d{8}|\d{4}-\d{7}/;
        if (!regExp.test(phone)) {
            alert("公司座机不合法！");
            return;
        }

        //发送请求
        $.ajax({
            url: 'workbench/customer/saveCustomers.do',
            data: {
                owner: owner,
                name: name,
                website: website,
                phone: phone,
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
                    $("#createCustomerModal").modal("hide");
                    //刷新列表

                } else{
                    //模态窗口不关闭
                    $("#createCustomerModal").modal("show");
                    alert(data.message);
                }
            }
        });
    });


});
