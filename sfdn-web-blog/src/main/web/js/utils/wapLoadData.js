/**
 * 初始加载数据加滚动加载数据
 *
 **/
var wapLoadData = {};
(function () {
    'use strict';
    //默认初始页面为1
    var _currentPage = 1;
    //默认下拉加载数据，无新数据加载时设置为false
    var _loadData = true;

    /**
     * 滚动加载数据
     * @param url       请求数据url
     * @param param     请求参数
     * @param tableId   渲染数据的tableId
     * @param renderDataFn   数据渲染的方法，数据不为空时执行
     * @param getResponseDataFn   自定义从返回数据格式中获取最终数据列表
     */
    wapLoadData.scrollLoadData = function (url,param,tableId,renderDataFn,getResponseDataFn) {
        var getData = function () {
            //设置查询当前页，设置为无尽模式，不需要查询总数，自定义每页条数如有需要可以扩展
            param.currentPage = _currentPage;
            param.infiniteMode = true;
            wapAjaxUtil.post(url,param,function (data) {
                //请求成功进行数据渲染
                if (data.resStatus == CONSTANTS.SUCCESS){
                    //返回的数据格式可能有多种，传递函数获取数据
                    var responseData = getResponseDataFn(data);
                    //数据不为空进行数据渲染
                    if (responseData != null && responseData.length > 0){
                        $("#loadMoreData").remove();
                        //下一次刷新更新下一页的数据
                        _currentPage++;
                        renderDataFn(responseData);
                        //第一次加载数据，如果数据是十条，则提示上滑加载更多数据
                        if (_currentPage == 2 && responseData.length == 10){
                            var boarddiv = "<div id='loadMoreData' style='width:100%;height:30px;line-height:30px;text-align: center;margin: 0 auto;position: absolute'>上滑加载更多数据</div>";
                            $("#" + tableId).append(boarddiv);
                        }
                        _loadData = true;
                    }else {
                        $("#loadMoreData").remove();
                        _loadData = false;
                        var boarddiv = "<div style='width:100%;height:30px;line-height:30px;text-align: center;margin: 0 auto;position: absolute'>已经加载完毕！</div>";
                        if(_currentPage == 0 || _currentPage== 1){
                            boarddiv = "<div class='nodateimg'></div>";
                        }
                        $("#" + tableId).append(boarddiv);
                    }
                }else {
                    _loadData = true;
                    alert(data.resMsg);
                }
            });
        };
        getData();
        $(window).scroll(function () {
            var scrollTop = parseFloat($(this).scrollTop());
            var scrollHeight = parseFloat($(document).height());
            var windowHeight = parseFloat($(this).height());
            //初始化页面即currentPage为1时加载数据或_loadData为true且滚动条至底部时加载数据
            if (_loadData && (scrollTop + windowHeight >= (scrollHeight-500))){
                _loadData = false;
                getData();
            }
        })
    };
    
})();
