$(function(){

    //定制字段
    $("#definedColumns > li").click(function(e) {
        //防止下拉菜单消失
        e.stopPropagation();
    });

    //给“创建”按钮添加事件
    $("#createContactsBtn").click(function () {
        //刷新重置
        $("#createContactsForm")[0].reset();
        //弹出创建窗口
        $("#createCustomerModal").modal("show");
    });



});
