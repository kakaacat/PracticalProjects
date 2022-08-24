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


    //阶段提示框
    $(".mystage").popover({
        trigger:'manual',
        placement : 'bottom',
        html: 'true',
        animation: false
    }).on("mouseenter", function () {
        var _this = this;
        $(this).popover("show");
        $(this).siblings(".popover").on("mouseleave", function () {
            $(_this).popover('hide');
        });
    }).on("mouseleave", function () {
        var _this = this;
        setTimeout(function () {
            if (!$(".popover:hover").length) {
                $(_this).popover("hide")
            }
        }, 100);
    });
});

