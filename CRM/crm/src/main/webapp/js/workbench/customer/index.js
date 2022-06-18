$(function(){

    // //定制字段
    // $("#definedColumns > li").click(function(e) {
    //     //防止下拉菜单消失
    //     e.stopPropagation();
    // });

    //查询所有数据的第一页以及总条数
    queryCustomerByConditionForPage(1, 10);


    //给“创建”按钮添加事件
    $("#createCustomerBtn").click(function () {
        //刷新重置
        $("#createCustomerForm")[0].reset();
        //弹出创建窗口
        $("#createCustomerModal").modal("show");
    });

    //给“保存”按钮添加点击事件
    $("#saveCreateCustomerBtn").click(function () {
        //收集参数
        var owner = $("#create-customerOwner").val();
        var name = $("#create-customerName").val();
        var website = $("#create-website").val();
        var phone = $("#create-phone").val();
        var description = $("#create-describe").val();
        var contactSummary = $("#create-contactSummary").val();
        var nextContactTime = $("#create-nextContactTime").val();
        var address = $("#create-address1").val();

        //表单验证
        if (owner == "") {
            alert("所有者不能为空！");
            return;
        }
        if (name == "") {
            alert("名称不能为空！")
            return;
        }
        var regExp = /\d{3}-\d{8}|\d{4}-\d{7}/;
        if (!regExp.test(phone)) {
            alert("公司座机不合法！");
            return;
        }

        //发送请求
        $.ajax({
            url: 'workbench/customer/saveCustomers.do',
            data: {
                owner: owner,
                name: name,
                website: website,
                phone: phone,
                description: description,
                contactSummary: contactSummary,
                nextContactTime: nextContactTime,
                address: address
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "1") {
                    //关闭模态窗口
                    $("#createCustomerModal").modal("hide");
                    //刷新列表
                    queryCustomerByConditionForPage(1, 10);
                } else{
                    //模态窗口不关闭
                    $("#createCustomerModal").modal("show");
                    alert(data.message);
                }
            }
        });
    });


    //给“查询”按钮添加单击事件
    $("#queryBtn").click(function () {
        queryCustomerByConditionForPage(1, $("#pageDiv").bs_pagination('getOption', 'rowsPerPage'));
    });

    //给全选按钮添加点击事件
    $("#checkAll").click(function () {
        $("#customer-tbody input[type='checkbox']").prop("checked", this.checked);
    });
    $("#customer-tbody").on("click", "input[type='checkbox']", function () {
        //如果列表中的checkedbox全部选中，全选也选中
        if ($("#customer-tbody input[type='checkbox']").size() == $("#customer-tbody input[type='checkbox']:checked").size()){
            $("#checkAll").prop("checked", true);
        } else {
            $("#checkAll").prop("checked", false);
        }
    });




});
//-----------------------------------------
function queryCustomerByConditionForPage(pageNo, pageSize){
    //收集参数
    var name = $("#query-name").val();
    var owner = $("#query-owner").val();
    var phone = $("#query-phone").val();
    var website = $("#query-website").val();
    //alert(name);
    //发送请求
    $.ajax({
        url: 'workbench/customer/queryCustomerForPage.do',
        data:{
            name: name,
            owner: owner,
            phone: phone,
            website: website,
            pageNo: pageNo,
            pageSize: pageSize
        },
        type: 'post',
        dataType: 'json',
        success:function (data) {
           //显示客户列表
            var htmlStr = "";
            $.each(data.customerList, function (index, obj) {
                htmlStr += "<tr class=\"active\">";
                htmlStr += "<td><input type=\"checkbox\" value=\""+ obj.id +"\"/></td>";
                htmlStr += "<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='detail.jsp';\">"+ obj.name +"</a></td>";
                htmlStr += "<td>"+ obj.owner +"</td>";
                htmlStr += "<td>"+ obj.phone +"</td>";
                htmlStr += "<td>"+ obj.website +"</td>";
                htmlStr += "</tr>";
            });
            $("#customer-tbody").html(htmlStr);

            //取消全选
            $("#checkAll").prop("checked", false);

            //调用分页插件,显示分页信息
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
                    queryCustomerByConditionForPage(pageObj.currentPage, pageObj.rowsPerPage);
                }
            });
        }
    });
}
