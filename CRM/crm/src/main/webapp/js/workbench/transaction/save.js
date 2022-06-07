$(function () {

    //给“市场活动原”添加单击事件
    $("#activitySourceBtn").click(function () {
        //打开模态窗口
        $("#findMarketActivity").modal("show");
    });
    //给”市场活动搜索“模态窗口中市场活动搜索框添加键盘抬起事件
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
    //给”市场活动搜索“模态窗口的“单选”按钮添加单击事件
    $("#tBodyTrans").on("click", "input[type='radio']", function () {
        //获取数据
        var id = this.value;
        var activityName = $(this).attr("activityName");
        //把数据写入隐藏域
        $("#activitySourceId").val(id);
        $("#create-activityId").val(activityName);
        //关闭模态窗口
        $("#findMarketActivity").modal("hide");
    });

    //给“搜索联系人”添加单击事件
    $("#contactsSearchBtn").click(function () {
        //打开模态窗口
        $("#findContacts").modal("show");
    });
    //给“搜索联系人”模态窗口搜索框添加键盘抬起事件
    $("#contactsSearchTxt").keyup(function () {
        //收集参数
        var name = this.value;
        //发送请求
        $.ajax({
            url: 'workbench/transaction/queryContactsByNameForCreateTran.do',
            data:{
                name: name,
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                var htmlStr = "";
                $.each(data, function (index, obj) {
                    htmlStr += "<tr>";
                    htmlStr += "<td><input value=\""+obj.id+"\" contactName=\""+obj.fullname+"\" type=\"radio\" name=\"activity\"/></td>";
                    htmlStr += "<td>"+obj.fullname+"</td>";
                    htmlStr += "<td>"+obj.email+"</td>";
                    htmlStr += "<td>"+obj.mphone+"</td>";
                    htmlStr += "</tr>";
                });
                $("#tBodycontactTran").html(htmlStr);
            }
        });
   });
    //给“搜索联系人”的“单选”按钮添加键盘单击事件
    $('#tBodycontactTran').on("click", "input[type='radio']", function () {
        //获取数据
        var id = this.value;
        var name = $(this).attr("contactName");
        //把数据写入隐藏域
        $("#contactsId").val(id);
        $("#create-contactsId").val(name);
        //关闭模态窗口
        $("#findContacts").modal("hide");
    });

    //给“阶段”输入框添加改变事件
    $("#create-stage").change(function () {
        //收集参数
        var stageValue = $("#create-stage option:selected").text();
        //验证
        if (stageValue == "" || stageValue == "---请选择---") {
            //alert(stageValue); //---请选择---
            //清空可能性输入框
            $("#create-possibility").val("");
            return;
        }
        //发送请求
        $.ajax({
            url: 'workbench/transaction/getPossibilityByStage.do',
            data: {
                stageValue: stageValue
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                //把获取到的可能性显示到输入框
                $("#create-possibility").val(data);
            }
        });
    });

    //客户名称自动补全
    $("#create-customerName").typeahead({
        source:function (jquery, process) {
            $.ajax({
                url: 'workbench/transaction/queryCustomerByName.do',
                data: {
                    name: jquery
                },
                type: 'post',
                dataType: 'json',
                success:function (data) {
                    process(data);
                }
            });
        }
    });

    //给‘保存’按钮添加单击事件
    $("#saveCreateTranBtn").click(function () {
        var owner = $("#create-owner").val();
        var money = $("#create-money").val();
        var name = $("#create-name").val();
        var expectedDate = $("#create-expectedDate").val();
        var customerName = $("#create-customerName").val();
        var stage = $("#create-stage").val();
        var type = $("#create-type").val();
        var source = $("#create-source").val();
        var activityId = $("#create-activityId").val();
        var contactsId = $("#create-contactsId").val();
        var description = $("#create-description").val();
        var contactsSummary = $("#create-contactsSummary").val();
        var nextContactTime = $("#create-nextContactTime").val();
    });


});