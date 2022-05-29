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
    });


});
