
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

    // $(".remarkDiv").mouseover(function(){
    //     $(this).children("div").children("div").show();
    // });
    $("#remarkListDiv").on("mouseover", ".remarkDiv", function () {
        $(this).children("div").children("div").show();
    });

    // $(".remarkDiv").mouseout(function(){
    //     $(this).children("div").children("div").hide();
    // });
    $("#remarkListDiv").on("mouseout", ".remarkDiv", function () {
        $(this).children("div").children("div").hide();
    });

    // $(".myHref").mouseover(function(){
    //     $(this).children("span").css("color","red");
    // });
    $("#remarkListDiv").on("mouseover", ".myHref", function () {
        $(this).children("span").css("color","red");
    });

    // $(".myHref").mouseout(function(){
    //     $(this).children("span").css("color","#E6E6E6");
    // });
    $("#remarkListDiv").on("mouseout", ".myHref", function () {
        $(this).children("span").css("color","#E6E6E6");
    });

    //给“保存”按钮添加单击事件
    $("#saveCreateActivityRemarkBtn").click(function () {
        //收集参数
        var activityId = $("#activityId").val();
        var noteContent = $.trim($("#remark").val());
        var sessionUsername = $("#sessionUsername").val();
        var activityName = $("#activityName").val();


        //表单验证
        if (noteContent == "" || noteContent == null) {
            alert("备注内容不能为空！");
            return;
        }
        //发送请求
        $.ajax({
            url: 'workbench/activity/saveCreateActivityRemark.do',
            data: {
                noteContent: noteContent,
                activityId: activityId
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //清空输入框
                    $("#remark").val("");
                    //刷新备注
                    var htmlStr = "";
                    htmlStr += "<div class=\"remarkDiv\" style=\"height: 60px;\">";
                    htmlStr += "<img title=\"" + sessionUsername + "\" src=\"image/user-thumbnail.png\" style=\"width: 30px; height:30px;\">";
                    htmlStr += "<div style=\"position: relative; top: -40px; left: 40px;\" >";
                    htmlStr += "<h5>" + data.retData.noteContent + "</h5>";
                    htmlStr += "<font color=\"gray\">市场活动</font> <font color=\"gray\">-</font> <b>" + activityName +"</b> ";
                    htmlStr += "<small style=\"color: gray;\">"+ data.retData.createTime+"由"+ sessionUsername +"创建</small>";
                    htmlStr += "<div style=\"position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;\">";
                    htmlStr += "<a class=\"myHref\" remarkId=\""+data.retData.id+"\"href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-edit\" style=\"font-size: 20px; color: #E6E6E6;\"></span></a>";
                    htmlStr += "&nbsp;&nbsp;&nbsp;&nbsp;";
                    htmlStr += "<a class=\"myHref\" remarkId=\""+data.retData.id+"\"href=\"javascript:void(0);\"><span class=\"glyphicon glyphicon-remove\" style=\"font-size: 20px; color: #E6E6E6;\"></span></a>";
                    htmlStr += "</div>";
                    htmlStr += "</div>";
                    htmlStr += "</div>";
                    $("#remarkDiv").before(htmlStr);
                } else {
                    //失败提示信息
                    alert(data.message);
                }
            }
        });
    });
});