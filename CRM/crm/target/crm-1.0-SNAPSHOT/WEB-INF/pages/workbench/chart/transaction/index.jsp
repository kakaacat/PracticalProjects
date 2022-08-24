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
    <title>交易统计图表</title>
    <script type="text/javascript">
        $(function () {
            //发送查询请求
            $.ajax({
                url: 'workbench/chart/transaction/queryCountTranGroupByStage.do',
                type: 'post',
                dataType: 'json',
                success: function (data) {
                    //调用 echarts 工具函数
                    var myChart = echarts.init(document.getElementById('main'));
                    var option = {
                        title: {
                            text: '交易统计图表',
                            subtext: '交易表中各阶段的数量'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{a} <br/>{b} : {c}'
                        },
                        toolbox: {
                            feature: {
                                dataView: { readOnly: false },
                                restore: {},
                                saveAsImage: {}
                            }
                        },
                        series: [
                            {
                                name: '数据量',
                                type: 'funnel',
                                left: '10%',
                                width: '80%',
                                gap: 2,
                                label: {
                                    show: true,
                                    position: 'inside'
                                },
                                itemStyle: {
                                    borderColor: '#fff',
                                    borderWidth: 1
                                },
                                emphasis: {
                                    label: {
                                        fontSize: 20
                                    }
                                },
                                data: data
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
