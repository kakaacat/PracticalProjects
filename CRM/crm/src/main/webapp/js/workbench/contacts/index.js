$(function(){

    //定制字段
    $("#definedColumns > li").click(function(e) {
        //防止下拉菜单消失
        e.stopPropagation();
    });

    //给“创建”按钮添加事件
    $("#createContactsBtn").click(function () {
        //清空表单
        $("#createContactsForm")[0].reset();
        //打开模态窗口
        $("#createContactsModal").modal("show");
    });

    //客户名称自动补全
    $("#create-customerName").typeahead({
        source: function (jquery, process) {
            $.ajax({
                url: 'workbench/contacts/queryCustomerByName.do',
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

    //给“保存”按钮添加事件
    $("#saveCreateContactsBtn").click(function () {
        //收集参数
        var owner = $("#create-contactsOwner").val();
        var source = $("#create-clueSource").val();
        var fullname = $("#create-surname").val();
        var appellation = $("#create-call").val();
        var job = $("#create-job").val();
        var mphone = $("#create-mphone").val();
        var email = $("#create-email").val();
        //var customerId = $("#create-customerName").val();
        var customerId = "805d14c7ba724ad795155d3292207432";
        var description = $("#create-describe").val();
        var contactSummary = $("#create-contactSummary1").val();
        var nextContactTime = $("#create-nextContactTime1").val();
        var address = $("#edit-address1").val();

        //表单验证
        if (owner == "") {
            alert("所有者不能为空");
            return;
        }
        if (fullname == "") {
            alert("名称不能为空");
            return;
        }
        if (source == "" || source == "---请选择---") {
            alert("请选择来源！");
            return;
        }
        if (appellation == "" || appellation == "---请选择---") {
            alert("请选择称谓！");
            return;
        }
        var regExp = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
        if (!regExp.test(email)) {
            alert("邮箱不合法！");
            return;
        }
        var regExp = /^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\d{8}$/;
        if (!regExp.test(mphone)) {
            alert("手机号不合法！");
            return;
        }

        //发送请求
        $.ajax({
            url: 'workbench/contacts/saveContacts.do',
            data: {
                owner : owner,
                source : source,
                fullname : fullname,
                appellation : appellation,
                job : job,
                mphone : mphone,
                email : email,
                customerId : customerId,
                description : description,
                contactSummary : contactSummary,
                nextContactTime : nextContactTime,
                address : address
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#createContactsModal").modal("hide");
                    //刷新页面

                } else{
                    //模态窗口不关闭
                    $("#createContactsModal").modal("show");
                    alert(data.message);
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
    queryContactsForPage(1, 10);

    //给“查询”按钮添加单击事件
    $("#queryBtn").click(function () {
        queryContactsForPage(1, $("#pageDiv").bs_pagination("getOption", "rowsPerPage"));
    });


    
});

//------------------------
function queryContactsForPage(pageNo, pageSize) {
    //收集参数
    var owner = $.trim($("#query-owner").val());
    var fullname =  $.trim($("#query-fullname").val());
    var customerId =  $.trim($("#query-customerId").val());
    var source = $("#query-source option:selected").text();

    //发送请求
    $.ajax({
        url: 'workbench/contacts/queryContactsForPage.do',
        data:{
            owner:owner,
            fullname:fullname,
            customerId:customerId,
            source:source,
            pageNo:pageNo,
            pageSize:pageSize
        },
        type:'post',
        dataType:'json',
        success:function (data) {
            //显示列表
            var htmlStr = "";
            $.each(data.contactsList, function (index, obj) {
                htmlStr+="<tr class=\"active\">";
                htmlStr+="<td><input type=\"checkbox\" value=\""+ obj.id +"\" /></td>";
                htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.jsp';\">"+ obj.fullname +"</a></td>";
                htmlStr+="<td>"+ obj.customerId +"</td>";
                htmlStr+="<td>"+ obj.owner +"</td>";
                htmlStr+="<td>"+ obj.source +"</td>";
                htmlStr+="</tr>";
            });
            $("#tbody").html(htmlStr);

            //取消全选
            $("#checkAll").prop("checked", false);

            //调用分页插件
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
                    queryContactsForPage(pageObj.currentPage, pageObj.rowsPerPage);
                }
            });
        }
    });
}