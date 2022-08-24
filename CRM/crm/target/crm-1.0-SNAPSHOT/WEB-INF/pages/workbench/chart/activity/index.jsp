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
    <title>市场活动统计图表</title>
    <script type="text/javascript">
        $(function () {
            //发送查询请求
            $.ajax({
                url: 'workbench/chart/activity/queryCountActivityGroupByUser.do',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    //调用 echarts 工具函数
                    var myChart = echarts.init(document.getElementById('main'));
                    var option = {
                        title:{
                            text:'市场活动统计图',
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        toolbox: {
                            feature: {
                                dataView: { readOnly: false },
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        legend: {
                            top: '5%',
                            left: 'center'
                        },
                        series: [
                            {
                                name: '创建量',
                                type: 'pie',
                                radius: ['40%', '70%'],
                                avoidLabelOverlap: false,
                                itemStyle: {
                                    borderRadius: 10,
                                    borderColor: '#fff',
                                    borderWidth: 2
                                },
                                label: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    label: {
                                        show: true,
                                        fontSize: '40',
                                        fontWeight: 'bold'
                                    }
                                },
                                labelLine: {
                                    show: false
                                },
                                data: data,
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
