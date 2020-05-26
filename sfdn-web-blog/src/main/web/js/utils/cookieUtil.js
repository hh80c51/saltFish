/**
 * Created by dzc on 17.11.27.
 * cookie工具类
 */

var cookieUtil = {};
(function () {
    'use strict';
    /**
     * 根据name获取cookie中的值
     * @param name   cookie中的key
     */
    cookieUtil.getCookie = function (name) {
        var arr,reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr = document.cookie.match(reg))
            return decodeURI(arr[2]);
        else
            return '';
    };

    /**
     * 根据name获取cookie中的值
     * @param name   cookie中的key
     */
    cookieUtil.getCookieWithoutDecode = function (name) {
        var arr,reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
        if(arr = document.cookie.match(reg))
            return arr[2];
        else
            return '';
    };
    
    cookieUtil.getCookieMap = function(){
        let cookiePattern = /^(\S+)=(\S+)$/;
        let cookieArray =  document.cookie.split("; ");
        let cookieMap = new Map();
        for(let item of cookieArray) {
            var resultArray = cookiePattern.exec(item);
            if (resultArray != null){
                cookieMap.set(resultArray[1], resultArray[2]);
            }
        }
        return cookieMap;
    };
    
    /**
     * 想浏览器中添加cookie
     * @param key
     * @param value
     * @param expires 过期时间， undefined：不设置， -1：默认过期时间30天
     */
    cookieUtil.setCookie = function(key, value, expires) {
        var expiresTmp = -1;
        if(expires != undefined){
            //time为-1代表设置默认时间
            var exp = new Date();
            if(expires == -1){
                exp.setTime(exp.getTime() + 30*24*60*60*1000);
            }else{
                exp.setTime(exp.getTime() + expires);
            }
            expiresTmp = exp.toGMTString();
        }
        if(expires > 0){
            document.cookie = key + "=" + encodeURI(value) + ";expires=" + expiresTmp + ";Path="+encodeURI("/");
        }else{
            document.cookie = key + "=" + encodeURI(value) + ";Path="+encodeURI("/");
        }
    };
    /**
     * 浏览器关闭，设置的cookie值被清空
     * @param name   cookie中的key
     * @param value  cookie中key对应的值
     */
    cookieUtil.setCookieWithoutExpires = function (name,value) {
        document.cookie = name + "=" + encodeURI(value);
    };
    /**
     * 将值放到cookie中，不对value进行编码处理
     * @param name   cookie中的key
     * @param value  cookie中key对应的值
     */
    cookieUtil.setCookieWithoutEncode = function (name,value) {
        document.cookie = name + "=" + value;
    };
    /**
     * 根据name删除cookie中的值
     * @param name   cookie中的key
     * @param path   cookie的路径
     */
    cookieUtil.delCookie = function (name,path) {
        var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        var value = cookieUtil.getCookie(name);
        if(value != '' && value != undefined && value != null){
            if (path != '' && path != null && path != undefined){
                document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toGMTString()+";Path="+encodeURI(path);
            }else {
                document.cookie = name + "=" + encodeURI(value) + ";expires=" + exp.toGMTString();
            }
        }
    };
    
}());