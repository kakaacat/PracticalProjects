
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
        //初始化
        $("#searchActivityTxt").val("");
        $("#bundActivityTbody").html("");

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

    //给“关联”按钮添加单击事件
    $("#saveBundActivityBtn").click(function () {
        //收集参数
        var clueId = $("#clueIdHid").val();
        var checkedId = $("#bundActivityTbody input[type='checkbox']:checked");
        //表单验证
        if (checkedId.size() == 0) {
            alert("请选择要关联的市场活动！");
            return;
        }
        //封装参数
        var ids = "";
        $.each(checkedId, function () {
            ids += "activityId=" + this.value + "&";
        });
        ids += "clueId="  + clueId;
        //发送请求
        $.ajax({
            url: 'workbench/clue/saveBund.do',
            data: ids,
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#bundModal").modal("hide");
                    //刷新列表
                    var htmlStr = "";
                    $.each(data.retData, function (index, obj) {
                        htmlStr+="<tr id=\"tr_"+obj.id+"\">";
                        htmlStr+="<td>"+obj.name+"</td>";
                        htmlStr+="<td>"+obj.startDate+"</td>";
                        htmlStr+="<td>"+obj.endDate+"</td>";
                        htmlStr+="<td>"+obj.owner+"</td>";
                        htmlStr+="<td><a href=\"javascript:void(0);\" actId=\""+obj.id+"\"  style=\"text-decoration: none;\"><span class=\"glyphicon glyphicon-remove\"></span>解除关联</a></td>";
                        htmlStr+="</tr>";
                    });
                    $("#relationedTbody").append(htmlStr);
                } else {
                    alert(data.message);
                    $("#bundModal").modal("show");
                }
            }
        });
    });

    //给“解除关联”按钮添加单击事件
    $("#relationedTbody").on("click", "a", function () {
        var clueId = $("#clueIdHid").val();
        var activityId = $(this).attr("actId");

        if (window.confirm("确定删除吗？")) {
            //发送请求
            $.ajax({
                url: 'workbench/clue/saveUnbund.do',
                data: {
                    activityId: activityId,
                    clueId: clueId
                },
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if (data.code == "1") {
                        $("#tr_" + activityId).remove();
                    } else {
                        alert(data.message);
                    }
                }
            });
        }
    });


});
