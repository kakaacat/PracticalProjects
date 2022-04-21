
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

    //给“关联市场活动”按钮添加单击事件
    $("#bundActivityBtn").click(function () {

        $("#bundModal").modal("show");
    });

    //给“搜索市场活动”添加键盘抬起事件
    $("#searchActivityTxt").keyup(function () {
        var activityName = this.value;
        var clueId = $("#clueIdHid").val();
        $.ajax({
            url: 'workbench/clue/queryActByNameClueIdForDetail.do',
            data: {
                activityName: activityName,
                clueId: clueId
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var htmlStr = "";


                $.each(data, function (index, obj) {
                    htmlStr+="<tr>";
                    htmlStr+="<td><input type=\"checkbox\" value=\""+obj.id+"\"/></td>";
                    htmlStr+="<td>"+obj.name+"</td>";
                    htmlStr+="<td>"+obj.startDate+"</td>";
                    htmlStr+="<td>"+obj.endDate+"</td>";
                    htmlStr+="<td>"+obj.owner+"</td>";
                    htmlStr+="</tr>";
                });

                $("#bundActivityTbody").html(htmlStr);
            }
        });
    });
});
