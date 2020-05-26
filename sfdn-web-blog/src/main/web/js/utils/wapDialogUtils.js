var  wapDialogUtil = {};
(function () {

    /***
     * @Description Confirm提示框
     * @Method_Name  Confirm
     * @param title 标题
     * @param msg 显示的内容
     * @param okCallback 点击确定的回调函数
     * @param okBtnValue 正确按钮描述信息，默认值确定
     * @param cancelCallBack 点击确定的回调函数 取消后回调函数
     * @param cancelValue 取消按钮描述信息 默认值取消
     * Created by hehang
     */
    wapDialogUtil.confirm = function (title, msg, okCallback, okBtnValue, cancelCallBack, cancelValue) {
        _generateHtml(title, msg);
        _btnOk(okCallback, okBtnValue);
        _btnNo(cancelCallBack, cancelValue);
    };

    //生成html
    var _generateHtml = function (title, msg) {
        var _html = "";
        _html += "    <div class=\"towmask\" id=\"towapMask\">\n" +
            "        <div class=\"towPopupwindow\">\n" +
            "            <h3>" + title + "</h3>\n" +
            "            <div id=\"towtext\">" + msg + "</div>\n" +
            "            <div class=\"towbtn\">\n" +
            "                <div class=\"towno\">取消</div><div class=\"towyes\">确定</div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>"
        $("body").append(_html);
        _generateCss();
    }

    //生成css
    var  _generateCss = function () {
        $("#towapMask").css({width: '100%', height: '100%', position: 'fixed', top: '0', left: '0', backgroundColor: 'rgba(0,0,0,0.34)', zIndex: '99999999999', display: 'block'});
        $(".towPopupwindow").css({width: '5.8rem', fontSize: '0.26rem', lineHeight: '.32rem', borderRadius: '.24rem', overflow: 'hidden',backgroundColor: 'white', position: 'fixed', left: '0',
            right: '0',  margin: '0 auto', bottom: '50%', marginBottom: '-0.7rem', paddingBottom: '.65rem', paddingTop: '.65rem'});
        $("#towtext").css({borderBottom: 'none', color: '#333333', padding: '0.32rem 0.24rem',textAlign: 'center', lineHeight: '.4rem'})
        $(".towbtn").css({width: '100%', height: '.76rem', borderRadius: '1rem', fontSize: '.3rem', textAlign: 'center', lineHeight: '.8rem',
            paddingTop: '0.2rem',display: 'inline-block'})
        $(".towbtn .towyes").css({marginLeft: '.7rem', background: '#f39200', color: '#fff'})
    }

    //确定按钮事件
    var _btnOk = function (callback, okBtnValue) {
        if(okBtnValue != undefined && okBtnValue != ''){
            $(".towyes").html(okBtnValue);
        }
        $(".towyes").click(function () {
            if (typeof (callback) == 'function') {
                $("#towapMask").hide();
                callback.call(undefined);
            }
        });
    };
    //取消按钮事件
    var _btnNo = function (cancelCallBack, cancelValue) {
        if(cancelValue != undefined && cancelValue != ''){
            $(".towno").html(cancelValue);
        }
        $(".towno").click(function () {
            if (cancelCallBack != undefined && typeof (cancelCallBack) == 'function') {
                $("#towapMask").hide();
                cancelCallBack.call(undefined);
            }
        });
    }


})();