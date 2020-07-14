/***
 *  工具类
 */
var commonUtil = {};
(function() {
	'use strict';

	commonUtil.platform = "";
	/**
	 * 判断参数是否为空(null,undefined,"")
	 * @param arg
	 * return 为空返回true,否则返回false
	 */
	commonUtil.isEmpty = function (arg) {
		if (typeof (arg) === "string") {
			return !arg.trim();
		}
		if (Array.isArray(arg)) {
			return !(arg.length > 0);
		}
		//避免输入值为0时返回true
		if (typeof (arg) === "number") {
			return false;
		}
		return !arg;
	};

	/**
	 * 拼接处理请求地址的全路径
	 * @param url 请求地址的url或是uri
	 * @returns {*}
	 */
	commonUtil.getRequestUrl = function (url) {
		if (!url.startWith("/") && !url.startWith("http")) {
			url = "/" + url;
		}
		if (!url.startWith("http") && !url.startWith(CONSTANTS.BASE_PATH)) {
			url = CONSTANTS.BASE_PATH + url;
		}
		return url;
	};

	/**
	 * 页面跳转
	 * @param uri 以/开头
	 * @param blank 是否在新页面打开
	 */
	commonUtil.jump = function (uri, blank) {
		var url = commonUtil.getRequestUrl(uri);
		if (blank != undefined) {
			window.open(url);
		} else {
			window.location.href = url;
		}
	};

	/**
	 * 页面跳转加时间戳
	 * @param uri 以/开头
	 * @param blank 是否在新页面打开
	 */
	commonUtil.jumpDynamic = function (uri, blank) {
		var url = commonUtil.getRequestUrl(uri);
		if (url.indexOf("?") != -1) {
			url += '&timestamp=' + new Date().getTime();
		} else {
			url += '?timestamp=' + new Date().getTime();
		}
		if (blank != undefined) {
			window.open(url);
		} else {
			window.location.href = url;
		}
	};

	/**
	 * 获得URL中的参数
	 * @param name
	 * @returns {null}
	 */
	commonUtil.getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return unescape(r[2]);
		}
		return null;
	};

	/**
	 * 生成36位的UUID
	 * @returns {string}
	 */
	commonUtil.uuid = function () {
		return (_S4() + _S4() + "-" + _S4() + "-" + _S4() + "-" + _S4() + "-" + _S4() + _S4() + _S4());
	};

	/**
	 * 加载|刷新验证码
	 * @param obj    元素对象
	 * @param type 验证码类型 1：随机验证码 2：计算验证码 默认值-1
	 * @returns {String}
	 */
	commonUtil.loadValidateCode = function (obj, type) {
		if (type == null || type == "" || type == 'undefined') {
			type = "1";
		}
		if (obj instanceof jQuery) {
			obj.attr("src", CONSTANTS.BASE_PATH + "/code/validateCodeServlet?" + Math.random() + "&type=" + type);
		} else {
			obj.src = CONSTANTS.BASE_PATH + "/code/validateCodeServlet?" + Math.random() + "&type=" + type;
		}
	};

	/**
	 * 通过元素的获得值
	 * @param name 元素name
	 * @returns {*|jQuery}
	 */
	commonUtil.getValueByName = function (name) {
		return $("input[name='" + name + "']").val();
	};


	/**
	 * 判断source是否以str结尾
	 * @param source
	 * @param str
	 * @returns {boolean}
	 */
	commonUtil.endWith = function (source, str) {
		var reg = new RegExp(str + "$");
		return reg.test(source);
	};


	/**
	 * 获取url中的参数集合
	 * @param url
	 * @returns
	 */
	commonUtil.getRequestParamByUrl = function (url) {
		if (commonUtil.isEmpty(url)) {
			url = window.location.href; //获取url中"?"符后的字串
		}
		var theRequest = {};
		if (url.indexOf("?") != -1) {
			var strs = url.split("?")[1];
			strs = strs.split("&");
			for (var i = 0; i < strs.length; i++) {
				theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
			}
		}
		return theRequest;
	};

	/**
	 * 倒计时计数器
	 *
	 * @param destUrl 时间结束后的目的地址
	 * @param countDownId 显示倒计时的id 默认值：countDown
	 * @param num 初始值  默认值 3秒
	 */
	commonUtil.countDown = function (destUrl, countDownId, num) {
		countDownId = countDownId == undefined ? 'countDown' : countDownId;
		num = num == undefined ? 3 : num;
		var countDown = num;
		var countDownObj = window.setInterval(function () {
			countDown = countDown - 1;
			if (countDown == 0) {
				window.clearInterval(countDownObj);
				commonUtil.jump(destUrl);
			} else {
				$("#" + countDownId).text(countDown);
			}
		}, 1000);
	};

	/**
	 * 验证密码强度
	 * @param pwd
	 */
	commonUtil.pwdStrong = function (pwd) {
		var level = 1;
		if (/[1-9]/.test(pwd) || /[a-z]/.test(pwd) || (pwd + "").length < 8 || (/[a-z1-9]/.test(pwd) && (pwd + "").length < 8)) {
			level = 1;
		}
		if ((/[a-z1-9]/.test(pwd) && (pwd + "").length >= 8) || (/[A-Za-z1-9]/.test(pwd) && (pwd + "").length < 8)) {
			level = 2;
		}
		if ((/[A-Za-z1-9]/.test(pwd) && (pwd + "").length >= 8)) {
			level = 3;
		}
		containerUtil.set(CONSTANTS.PWD_LEVEL, level);
		return level;
	};

	/**
	 * 显示指定长度的数据信息
	 * @param text                文本内容
	 * @param maxLength            根据长度截取文本内容
	 * @returns
	 */
	commonUtil.getTextByLength = function (text, maxLength) {
		var _maxLength = 10;
		if (maxLength != undefined) {
			_maxLength = maxLength;
		}
		if (text == undefined) {
			return text;
		}
		if (text.length > _maxLength) {
			return "<span title='" + text + "'>" + text.substr(0, _maxLength) + "...</span>";
		}
		return text;
	};
	/**
	 * 对文字部分隐藏
	 * @param str 字符串
	 * @param frontLen 前几位保留
	 * @param endLen 后几位保留
	 * @private
	 */
	commonUtil.getTextPlusStar = function (frontLen, text, endLen) {
		if (commonUtil.isEmpty(text)) {
			return '';
		}
		var len = text.length - frontLen - endLen;
		var xing = '';
		for (var i = 0; i < len; i++) {
			xing += '*';
		}
		return text.substr(0, frontLen) + xing + text.substr(text.length - endLen);
	};
	/**
	 html中使用方法:
	 <div id="timer"  style="font-size:20px">
	 剩余天数：
	 <span id="timer_d">0</span>天
	 <span id="timer_h">0</span>时
	 <span id="timer_m">0</span>分
	 <span id="timer_s">0</span>秒
	 </div>
	 *  //时分秒倒计时方法
	 */
	commonUtil.timer = function timer(eleId, endTimer) {
		var element = document.getElementById(eleId);
		if (element) {
			var endTime = endTimer;
			// var endTimeMonth=endTime.getMonth()-1;
			//endTime.setMonth(endTimeMonth);
			var endDate = new Date();
			endDate.setTime(endTimer)
			var ts = endDate - new Date();
			if (ts > 0) {
				var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);
				var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);
				var mm = parseInt(ts / 1000 / 60 % 60, 10);
				var ss = parseInt(ts / 1000 % 60, 10);
				dd = dd < 10 ? ("0" + dd) : dd;   //天
				hh = hh < 10 ? ("0" + hh) : hh;   //时
				mm = mm < 10 ? ("0" + mm) : mm;   //分
				ss = ss < 10 ? ("0" + ss) : ss;   //秒
				document.getElementById("timer_d" + eleId).innerHTML = dd;
				document.getElementById("timer_h" + eleId).innerHTML = hh;
				document.getElementById("timer_m" + eleId).innerHTML = mm;
				document.getElementById("timer_s" + eleId).innerHTML = ss;
				setTimeout(function () {
					timer(eleId, endTimer);
				}, 1000);
			} else {
				document.getElementById("timer_d" + eleId).innerHTML = 0;
				document.getElementById("timer_h" + eleId).innerHTML = 0;
				document.getElementById("timer_m" + eleId).innerHTML = 0;
				document.getElementById("timer_s" + eleId).innerHTML = 0;
			}
		}

	}

	/**
	 * 将网页滚动到指定元素位置，比如页面表单标签校验较多时，方便用户定位到错误信息
	 * @param id 目标元素id
	 */
	commonUtil.moveHtml = function (id) {
		var scroll_offset = $("#" + id).offset().top;
		$("body,html").animate({
			scrollTop: scroll_offset
			//让body的scrollTop等于pos的top，就实现了滚动
		}, 500);
	};

	commonUtil.open = function (url) {
		window.open(url);
	};

	/**
	 *  跳转至 域名/项目根目录
	 */
	commonUtil.backIndex = function () {
		window.location.href = window.location.protocol + "//" + window.location.host + CONSTANTS.BASE_PATH;
	};
	//========== public method ============


	// ========= private method ============
	/**
	 * 生成4位随机数
	 * @returns {string}
	 * @private
	 */
	function _S4() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	}

	/**
	 * 显示所有class含所有loginShow的元素
	 * @param className
	 * @private
	 */
	function _showElement(className) {
		var obj = $("." + className);
		if (obj != undefined && obj != 'undefined') {
			obj.each(function () {
				$(this).removeClass("hide").addClass("show");
			});
		}
	}

	/**
	 * @param submitId标识
	 * @private
	 */
	commonUtil.keySubmit = function (submitId) {
		$("body").keydown(function (event) {
			if (event.keyCode === 13) {//keyCode=13是回车键
				$("#" + submitId).click();
			}
		});
	};
})();







