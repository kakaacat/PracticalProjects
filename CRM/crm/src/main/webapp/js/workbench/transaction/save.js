$(function () {

    //给“市场活动原”添加单击事件
    $("#activitySourceBtn").click(function () {
        //打开模态窗口
        $("#findMarketActivity").modal("show");
    });
    //给模态窗口中市场活动搜索框添加键盘抬起事件
    $("#searchActivityTxt").keyup(function () {
        //收集参数
        var name = this.value;
        //发送请求
        $.ajax({
            url: 'workbench/transaction/queryActivityByNameForTrans.do',
            data: {
                name: name,
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var htmlStr = "";
                $.each(data, function (index, obj) {
                    htmlStr += "<tr>";
                    htmlStr += "<td><input value=\""+obj.id+"\" activityName=\""+obj.name+"\" type=\"radio\" name=\"activity\"/></td>";
                    htmlStr += "<td>"+obj.name+"</td>";
                    htmlStr += "<td>"+obj.startDate+"</td>";
                    htmlStr += "<td>"+obj.endDate+"</td>";
                    htmlStr += "<td>"+obj.owner+"</td>";
                    htmlStr += "</tr>";
                });
                $("#tBodyTrans").html(htmlStr);
            }
        });
    });




});