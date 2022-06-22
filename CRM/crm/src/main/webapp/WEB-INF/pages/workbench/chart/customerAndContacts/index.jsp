<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String basePath=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jquery/echars/echarts.min.js"></script>
    <title>客户和联系人统计图</title>
    <script type="text/javascript">
        $(function () {
            //发送查询请求
            $.ajax({
                url: 'workbench/chart/customerAndContacts/queryCountContactsGroupByCustomer.do',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    //调用 echarts 工具函数
                    var myChart = echarts.init(document.getElementById('main'));
                    var option = {
                        title:{
                            text:'客户和联系人统计图'
                        },
                        tooltip:{
                            target:'axis'
                        },
                        toolbox:{
                            feature:{
                                dataView: {readOnly: false},
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        xAxis: {
                            type: 'category',
                            data: data.xList
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                data: data.yList,
                                type: 'line'
                            }
                        ]
                    };

                    myChart.setOption(option);
                }
            });
        });
    </script>
</head>
<body>
<div id="main" style="position: relative; top: 50px; left: 20px; width: 90%; height: 90%;"></div>

</body>
</html>
