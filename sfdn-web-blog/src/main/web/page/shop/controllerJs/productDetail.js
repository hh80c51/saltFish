/**
 * 首页js
 * @type {{}}
 */
var productDetailController = {};
$(function () {
    productDetailController.addToCart = function (id) {
        ajaxUtil.post('/productController/addToCart.do', {"productId": id}, function (data) {
            if (data.resStatus == CONSTANTS.SUCCESS && data.resMsg.length > 0) {
                alert("has added to Cart !!!")
            }
        })
    }
})