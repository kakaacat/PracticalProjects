$(function () {
    $("#isCreateTransaction").click(function () {
        if (this.checked) {
            $("#create-transaction2").show(200);
        } else {
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

    //给“市场活动原”添加单击事件
    $("#activitySourceBtn").click(function () {
        //打开模态窗口
        $("#searchActivityModal").modal("show");
    });
    //给模态窗口中市场活动搜索框添加键盘抬起事件
    $("#searchActivityTxt").keyup(function () {
        //收集参数
        var clueId = $("#clueIdHiddenArea").val();
        var activityName = this.value;

        //发送请求
        $.ajax({
            url: 'workbench/clue/queryActivityForConvertByANameCId.do',
            data: {
                activityName: activityName,
                clueId: clueId
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var htmlStr = "";
                $.each(data, function (index, obj) {
                    htmlStr +="<tr>";
                    htmlStr +="<td><input value=\""+obj.id+"\" activityName=\""+obj.name+"\" type=\"radio\" name=\"activity\"/></td>";
                    htmlStr +="<td>"+obj.name+"</td>";
                    htmlStr +="<td>"+obj.startDate+"</td>";
                    htmlStr +="<td>"+obj.endDate+"</td>";
                    htmlStr +="<td>"+obj.owner+"</td>";
                    htmlStr +="</tr>";
                });
                $("#tbodyClueConvert").html(htmlStr);
            }
        });
    });
    //给模态窗口的“单选”按钮添加单击事件
    $("#tbodyClueConvert").on("click", "input[type='radio']", function () {
        //获取数据
        var id = this.value;
        var activityName = $(this).attr("activityName");
        //把数据写入隐藏域
        $("#activitySourceId").val(id);
        $("#activitySourceName").val(activityName);
        //关闭模态窗口
        $("#searchActivityModal").modal("hide");
    });

    //给"转换按钮"添加单击事件
    $("#saveConvertClueBtn").click(function () {
        //收集参数
        var clueId = $("#clueIdHiddenArea").val();
        var money = $.trim($("#amountOfMoney").val());
        var name = $("#tradeName").val();
        var expectedDate = $("#expectedClosingDate").val();
        var stage = $("#stage").val();
        var activityId = $("#activitySourceId").val();
        var isCreateTran = $("#isCreateTransaction").prop("checked");
        //表单验证
        var regExp = /^(([1-9]\d*)|0)$/;
        if (isCreateTran && !regExp.test(money)) {
            alert("金额只能为非负整数！");
            return;
        }
        //发送请求
        $.ajax({
            url: 'workbench/clue/convertClue.do',
            data:{
                clueId: clueId,
                money: money,
                name: name,
                expectedDate: expectedDate,
                stage: stage,
                activityId: activityId,
                isCreateTran: isCreateTran
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    window.location.href = "workbench/clue/index.do";
                } else {
                    alert(data.message);
                }
            }
        });
    });



});