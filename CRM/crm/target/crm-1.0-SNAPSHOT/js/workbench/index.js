//页面加载完毕
$(function(){

    //导航中所有文本颜色为黑色
    $(".liClass > a").css("color" , "black");

    //默认选中导航菜单中的第一个菜单项
    $(".liClass:first").addClass("active");

    //第一个菜单项的文字变成白色
    $(".liClass:first > a").css("color" , "white");

    //给所有的菜单项注册鼠标单击事件
    $(".liClass").click(function(){
        //移除所有菜单项的激活状态
        $(".liClass").removeClass("active");
        //导航中所有文本颜色为黑色
        $(".liClass > a").css("color" , "black");
        //当前项目被选中
        $(this).addClass("active");
        //当前项目颜色变成白色
        $(this).children("a").css("color","white");
    });


    window.open("workbench/main/index.do","workareaFrame");

    //给确定按钮添加单击事件
    $("#logoutBtn").click(function () {
        //发送同步请求
        window.location.href="settings/qx/user/logout.do";
    });

});