$(function(){
    //给“创建”按钮添加事件
    $("#createActivityBtn").click(function () {
        //刷新重置
        $("#creataActivityForm")[0].reset();

        //弹出创建窗口
        $("#createActivityModal").modal("show");

    });

    //给“保存”按钮添加事件
    $("#saveCreateActivityBtn").click(function () {
        //收集参数
        var owner = $("#create-marketActivityOwner").val();
        var name = $.trim($("#create-marketActivityName").val());
        var startDate = $("#create-startDate").val();
        var endDate = $("#create-endDate").val();
        var cost =$.trim($("#create-cost").val());
        var description = $.trim($("#create-description").val());

        //表单验证
        if (owner == "") {
            alert("所有者不能为空");
            return;
        }
        if (name == "") {
            alert("名称不能为空");
            return;
        }
        if (startDate != "" && endDate != "") {
            if (endDate < startDate) {
                alert("结束日期不能小于开始日期");
                return;
            }
        }

        var regExp = /^(([1-9]\d*)|0)$/;
        if (!regExp.test(cost)) {
            alert("成本只能为非负整数！");
            return;
        }

        //发送请求
        $.ajax({
            url: 'workbench/activity/saveCreateActivity.do',
            data:{
                owner : owner,
                name : name,
                startDate : startDate,
                endDate : endDate,
                cost : cost,
                description : description
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#createActivityModal").modal("hide");
                    //刷新市场活动列
                    queryActivityByConditionForPage(1, $("#pageDiv").bs_pagination('getOption', 'rowsPerPage'));

                } else {
                    alert(data.message);
                    //模态窗口不关闭
                    $("#createActivityModal").modal("show");
                }
            }
        });
    });

    //日历函数
    $(".mydate").datetimepicker({
        language : 'zh-CN',
        format : 'yyyy-mm-dd',
        minView : 'month',
        initialDate : new Date(),
        autoclose : true,
        todayBtn : true,
        clearBtn : true
    });

    //查询所有数据的第一页以及总条数
    queryActivityByConditionForPage(1, 10);

    //给“查询”按钮添加单击事件
    $("#queryActivityBtn").click(function () {
        queryActivityByConditionForPage(1, $("#pageDiv").bs_pagination('getOption', 'rowsPerPage'));
    });

    //给全选按钮添加点击事件
    $("#checkAll").click(function () {
        $("#tBody input[type='checkbox']").prop("checked", this.checked);
    });

    $("#tBody").on("click", "input[type='checkbox']", function () {
        //如果列表中的checkedbox全部选中，全选也选中
        if ($("#tBody input[type='checkbox']").size() == $("#tBody input[type='checkbox']:checked").size()){
            $("#checkAll").prop("checked", true);
        } else {
            $("#checkAll").prop("checked", false);
        }
    });

    //给“删除”按钮添加单击事件
    $("#deleteActivityBtn").click(function () {
        var checkedIds = $("#tBody input[type='checkbox']:checked");
        if (checkedIds.size() == 0) {
            alert("请选择要删除的市场活动！")
            return;
        }
        if (window.confirm("确定删除吗？")){
            var ids = "";
            $.each(checkedIds, function () {
                ids += "id="+this.value+"&";
            });
            ids = ids.substr(0, ids.length - 1);
            //alert(ids);
            $.ajax({
                url: 'workbench/activity/deleteActivityByIds.do',
                data: ids,
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    if (data.code == "1") {
                        //刷新市场活动列
                        queryActivityByConditionForPage(1, $("#pageDiv").bs_pagination('getOption', 'rowsPerPage'));
                    } else {
                        alert(data.message);
                    }
                }
            });
        }
    });

    //给“修改”按钮添加单击事件
    $("#editActivityBtn").click(function () {
        //收集参数
        var checkedIds = $("#tBody input[type='checkbox']:checked");
        if (checkedIds.size() == 0) {
            alert("请选择要修改的市场活动！");
            return;
        }
        if (checkedIds.size() > 1) {
            alert("每次只能修改一条市场活动！");
            return;
        }
        var id = checkedIds.val();
        //发送请求
        $.ajax({
            url:'workbench/activity/selectActivityById.do',
            data: {id: id},
            type:'post',
            dataType:'json',
            success: function (data) {
                $("#edit-id").val(data.id);
                $("#edit-marketActivityOwner").val(data.owner);
                $("#edit-marketActivityName").val(data.name);
                $("#edit-startTime").val(data.startDate);
                $("#edit-endTime").val(data.endDate);
                $("#edit-cost").val(data.cost);
                $("#edit-describe").val(data.description);
                //弹出模态窗口
                $("#editActivityModal").modal("show");
            }
        });
    });

    //给“保存”按钮添加点击事件
    $("#saveEditActivityBtn").click(function () {
        //收集参数
        var id = $("#edit-id").val();
        var owner = $("#edit-marketActivityOwner").val();
        var name = $.trim($("#edit-marketActivityName").val());
        var startDate = $("#edit-startTime").val();
        var endDate = $("#edit-endTime").val();
        var cost = $.trim($("#edit-cost").val());
        var description = $.trim($("#edit-describe").val());

        //表单验证
        if (owner == "") {
            alert("所有者不能为空");
            return;
        }
        if (name == "") {
            alert("名称不能为空");
            return;
        }
        if (startDate != "" && endDate != "") {
            if (endDate < startDate) {
                alert("结束日期不能小于开始日期");
                return;
            }
        }

        var regExp = /^(([1-9]\d*)|0)$/;
        if (!regExp.test(cost)) {
            alert("成本只能为非负整数！");
            return;
        }

        //发送请求
        $.ajax({
            url: 'workbench/activity/saveEditActivity.do',
            data: {
                id : id,
                owner : owner,
                name : name,
                startDate : startDate,
                endDate : endDate,
                cost : cost,
                description : description
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#editActivityModal").modal("hide");
                    //刷新市场活动，保持页号和每页条数不变
                    queryActivityByConditionForPage($("#pageDiv").bs_pagination('getOption', 'currentPage'), $("#pageDiv").bs_pagination('getOption', 'rowsPerPage'));
                } else {
                    alert(data.message);
                    //模态窗口不关闭
                    $("#editActivityModal").modal("show");
                }
            }
        });
    });

    //给“批量导出”按钮添加单击事件
    $("#exportActivityAllBtn").click(function () {
        //发送请求
        window.location.href = "workbench/activity/exportAllActivities.do";
    });

    //给“选择导出”按钮添加单击事件
    $("#exportActivityXzBtn").click(function () {
        //收集参数
        var checkedIds = $("#tBody input[type='checkbox']:checked");
        if (checkedIds.size() == 0) {
            alert("请选择要导出的市场活动！");
            return;
        }
        var ids = "";
        $.each(checkedIds, function () {
            ids += "id="+this.value+"&";
        });
        ids = ids.substr(0, ids.length - 1);

        //发送请求
        window.location.href = "workbench/activity/queryActivitiesByIds.do?" + ids;

    });

});

function queryActivityByConditionForPage(pageNo, pageSize) {
    //收集参数
    var name = $("#query-name").val();
    var owner = $("#query-owner").val();
    var startDate = $("#query-startDate").val();
    var endDate = $("#query-endDate").val();
    // var pageNo = 1;
    // var pageSize = 10;
    //发送请求
    $.ajax({
        url: 'workbench/activity/queryActivityByConditionForPage.do',
        data: {
            name:name,
            owner:owner,
            startDate:startDate,
            endDate:endDate,
            pageNo:pageNo,
            pageSize:pageSize
        },
        type :'post',
        dataType: 'json',
        success : function (data) {
            //显示总条数
            //$("#totalRowsB").text(data.totalRows);
            //显示市场活动列表
            var htmlStr = "";
            $.each(data.activityList, function (index, obj) {
                htmlStr+= "<tr class=\"active\">";
                htmlStr+= "<td><input type=\"checkbox\" value=\""+ obj.id +"\"/></td>";
                htmlStr+=
                    "<td><a style=\"text-decoration: none; cursor: pointer;\"onclick=\"window.location.href='detail.html';\">"+ obj.name +"</a></td>";
                htmlStr+= "<td>"+ obj.owner +"</td>";
                htmlStr+= "<td>"+ obj.startDate +"</td>";
                htmlStr+= "<td>"+ obj.endDate +"</td>";
                htmlStr+= "</tr>";
            });
            $("#tBody").html(htmlStr);

            //取消全选
            $("#checkAll").prop("checked", false);

            //调用分页插件，显示分页信息
            $("#pageDiv").bs_pagination({
                currentPage: pageNo,

                rowsPerPage: pageSize,
                totalRows: data.totalRows,
                totalPages: Math.ceil(data.totalRows / pageSize),

                visiblePageLinks: 5,

                showGoToPage: true,
                showRowsPerPage: true,
                showRowsInfo: true,

                onChangePage: function (event, pageObj) {
                    queryActivityByConditionForPage(pageObj.currentPage, pageObj.rowsPerPage)
                }
            });
        }
    });
}