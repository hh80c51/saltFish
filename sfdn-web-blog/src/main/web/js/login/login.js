var loginController = {};

/**
 * 登录
 */
loginController.login = function () {
    var name = $("#username").val();
    var password = $("#password").val();
    var reqData = {name: name, password: password};
    ajaxUtil.post("user/login.do", reqData, function (data) {
        if(data.resStatus == CONSTANTS.SUCCESS){
            console.info("请求成功！")
            commonUtil.jump("page/shop/index.html");
        }else{
            alert("用户不存在");
        }
    })
}