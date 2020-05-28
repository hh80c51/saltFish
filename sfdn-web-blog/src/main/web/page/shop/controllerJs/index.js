/**
 * 首页js
 * @type {{}}
 */
var indexController = {};
$(function () {
    indexController.showProducts = function () {
        ajaxUtil.post('/productController/getIndexProductList.do', {}, function (data) {
            if (data.resStatus == CONSTANTS.SUCCESS && data.resMsg.length > 0) {
                var productList = data.resMsg;
                console.info(productList.length);
                if (productList != null && productList.length > 0) {
                    var productArea = '';
                    for (var i = productList.length - 1; i >= 0; i--) {
                        var product = productList[productList.length - i -1];
                        productArea += '<div class="product">';
                        productArea += '<div class="product_image"><img src="${project_base_path}'+ product.cover +'" alt=""></div>';
                            if(i == productList.length - 1){
                                productArea += '<div class="product_extra product_new"><a href="categories.html">New</a></div>';
                            }
                            if(i == productList.length - 2){
                                productArea += '<div class="product_extra product_sale"><a href="categories.html">Sale</a></div>';
                            }
                        productArea += '<div class="product_content">';
                        productArea += '<div class="product_title"><a href="product.html">'+ product.name +'</a></div>';
                        productArea += '<div class="product_price">$'+ product.price +'</div>';
                        productArea += '</div>';
                        productArea += '</div>';;
                    }
                }
                $(".product_grid").empty();
                $(".product_grid").append(productArea);
            } else {
                //无商品信息
            }
        }, null, false);
    }
});