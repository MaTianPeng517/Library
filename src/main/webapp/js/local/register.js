$(function () {
    //鼠标失去光标事件
    $("#username").blur(function () {
        //获取到注册页面上的输入用户名称的文本框值
        var userName=$("#username").val();
        //获取到的值进行判断
        if(userName===""){
            $("#tips").html("用户名不能为空！！");
        }else{
        //不为空时，使用ajax提交数据
            $.ajax({
                url:"/login?methodName=validateName",
                data:{"userName":userName},
                dataType:"json",
                success:function (data) {
                    if(data){
                        $("#tips").html(data.message)
                    }else {
                        $("#tips").html("可以使用")
                    }
                }
            })
        }
    })
})