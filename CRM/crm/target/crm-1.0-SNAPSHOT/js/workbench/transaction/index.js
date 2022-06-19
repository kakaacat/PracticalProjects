$(function(){

    //给创建按钮添加单击事件
    $("#createTransBtn").click(function () {
        window.location.href = "workbench/transaction/toSave.do";
    });


    //查询所有数据的第一页以及总条数
    queryTranByConditionForPage(1, 10);

    //给“查询”按钮添加单击事件
    $("#queryBtn").click(function () {
        queryTranByConditionForPage(1, $("#pageDiv").bs_pagination('getOption', 'rowsPerPage'));
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


});
//------------------------------
function queryTranByConditionForPage(pageNo, pageSize) {
    //收集参数
    var owner = $.trim($("#query-owner").val());
    var name = $.trim($("#query-name").val());
    var customerId = $.trim($("#query-customerId").val());
    var contactsId = $.trim($("#query-contactsId").val());
    var stage = $("#query-stage option:selected").text();
    var type = $("#query-type option:selected").text();
    var source = $("#query-source option:selected").text();

    //发送请求
    $.ajax({
        url: 'workbench/transaction/queryTranByConditionForPage.do',
        data:{
            owner:owner,
            name:name,
            customerId:customerId,
            contactsId:contactsId,
            stage:stage,
            type:type,
            source:source,
            pageNo:pageNo,
            pageSize:pageSize
        },
        type:'post',
        dataType:'json',
        success:function (data) {
            //显示列表
            var htmlStr="";
            $.each(data.tranList, function (index, obj) {
                htmlStr+="<tr class=\"active\">";
                htmlStr+="<td><input type=\"checkbox\" value=\""+ obj.id +"\"/></td>";
                htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onclick=\"window.location.href='workbench/transaction/tranDetail.do?id=2c4ae2d22d2b48429fb54ebdbaf988c3';\">"+ obj.name +"</a></td>";
                htmlStr+="<td>"+ obj.customerId +"</td>";
                htmlStr+="<td>"+ obj.stage +"</td>";
                htmlStr+="<td>"+ obj.type +"</td>";
                htmlStr+="<td>"+ obj.owner +"</td>";
                htmlStr+="<td>"+ obj.source +"</td>";
                htmlStr+="<td>"+ obj.contactsId +"</td>";
                htmlStr+="</tr>";
            });
            $("#tBody").html(htmlStr);

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
                    queryTranByConditionForPage(pageObj.currentPage, pageObj.rowsPerPage);
                }
            });

        }
    });


}