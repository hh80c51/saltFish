var wapAjaxUtil = {};
(function () {

    /***
     * @Description AJAX的POST请求
     * @Method_Name  POST
     * @param url 提交到后台的URL
     * @param param 提交的数据
     * @param callback 成功返回的方法
     * @param dataType 数据类型 如果为空则默认为json格式
     * @param async 同步还是异步(默认true)，false为同步
     * Created by yanbinghuang
     */
    wapAjaxUtil.post = function (url, param, callback, dataType, async) {
        _sendAjax(url, "post", dataType, param, async, callback);
    };

    //调用AJAX
    var _sendAjax = function (url, type, dataType, param, async, callback) {
        $.ajax({
            type: type,
            url: commonUtil.getRequestUrl(url),
            data: validUtil.validNotEmpty(param) ? param : {},
            dataType: validUtil.validNotEmpty(dataType) ? dataType :"json" ,
            async:validUtil.validNotEmpty(async)? async :true ,
            cache: false,
            success: function(data){
                if(data.resStatus == CONSTANTS.SESSION_TIME_OUT){
                    //跳转至wap登录页
                    commonUtil.wapSessionTimeOut();
                }else if(data.resStatus == CONSTANTS.NO_IDENTIFY) {
                    //跳转至wap端实名页面
                    commonUtil.jump("wap/realName.html");
                }else{
                    if(validUtil.validNotEmpty(callback) && typeof callback == 'function'){
                        callback(data);
                    }
                }
            },
            error: function () {
                //window.location.href = "/error.html";
            }
        });
    };



    /***
     * @Description AJAX的POST请求 不包含实名跳转的情况
     * @Method_Name  POST
     * @param url 提交到后台的URL
     * @param param 提交的数据
     * @param callback 成功返回的方法
     * @param dataType 数据类型 如果为空则默认为json格式
     * @param async 同步还是异步(默认true)，false为同步
     * Created by yanbinghuang
     */
    wapAjaxUtil.wapPost = function (url, param, callback, dataType, async) {
        _postAjax(url, "post", dataType, param, async, callback);
    };

    //调用AJAX
    var _postAjax = function (url, type, dataType, param, async, callback) {
        $.ajax({
            type: type,
            url: commonUtil.getRequestUrl(url),
            data: validUtil.validNotEmpty(param) ? param : {},
            dataType: validUtil.validNotEmpty(dataType) ? dataType : "json",
            async: validUtil.validNotEmpty(async) ? async : true,
            cache: false,
            success: function (data) {
                if (validUtil.validNotEmpty(callback) && typeof callback == 'function') {
                    callback(data);
                }

            },
            error: function () {
                //window.location.href = "/error.html";
            }
        });
    };
})();