$(function(){
    $("#isCreateTransaction").click(function(){
        if(this.checked){
            $("#create-transaction2").show(200);
        }else{
            $("#create-transaction2").hide(200);
        }
    });

    //日历函数
    $(".mydate").datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        minView: 'month',
        initialDate: new Date(),
        autoclose: true,
        todayBtn: true,
        clearBtn: true
    });

});