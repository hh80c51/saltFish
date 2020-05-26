/**
 * Created by dzc on 17.11.23.
 * 常量类
 */

var CONSTANTS = {};
(function() {
    /**成功标识*/
    CONSTANTS.SUCCESS = 1000;
    /**用户未实名*/
    CONSTANTS.NO_IDENTIFY = 2001;
    /**登录超时*/
    CONSTANTS.SESSION_TIME_OUT = 2003;
    /**防重复提交token*/
    CONSTANTS.SUBMIT_TOKEN = "submitToken";

    /**
     * 项目根路径 在html使用${basePath},只有在被引用的面中才能使用，
     *  eg  a.html中通过<div class="include" file="${basePath}/b.html"></div>
     *      而且b.html中引入的静态资源、为防止出现404都可以加上${basePath} 如上面${basePath}/xxx.jpg
     */
    CONSTANTS.DEFAULT_BASE_PATH = "DEFAULT_BASE_PATH";

    /**项目根路径，有项目名称的时候必填,格式：http://127.0.0.1:9090/hkjf*/
    CONSTANTS.BASE_PATH = "${project_base_path}";
})();