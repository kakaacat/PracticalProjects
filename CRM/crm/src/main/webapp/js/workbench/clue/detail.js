
//默认情况下取消和保存按钮是隐藏的
var cancelAndSaveBtnDefault = true;

$(function(){
    $("#remark").focus(function(){
        if(cancelAndSaveBtnDefault){
            //设置remarkDiv的高度为130px
            $("#remarkDiv").css("height","130px");
            //显示
            $("#cancelAndSaveBtn").show("2000");
            cancelAndSaveBtnDefault = false;
        }
    });

    $("#cancelBtn").click(function(){
        //显示
        $("#cancelAndSaveBtn").hide();
        //设置remarkDiv的高度为130px
        $("#remarkDiv").css("height","90px");
        cancelAndSaveBtnDefault = true;
    });

    $(".remarkDiv").mouseover(function(){
        $(this).children("div").children("div").show();
    });

    $(".remarkDiv").mouseout(function(){
        $(this).children("div").children("div").hide();
    });

    $(".myHref").mouseover(function(){
        $(this).children("span").css("color","red");
    });

    $(".myHref").mouseout(function(){
        $(this).children("span").css("color","#E6E6E6");
    });
});
