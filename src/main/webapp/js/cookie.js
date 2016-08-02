/**
 *  @file Describe the file
 * 函数描述
 *
 * @param {string} name 传进来的名字
 * @param {string} value 传进来的值
 *     那就换行了.
 * @param {number=} millisecond 回调
 *
 */
function setCookie(name, value, millisecond) {
    if (millisecond > 0) {
        var exp = new Date();
        exp.setTime(exp.getTime() + millisecond);
        document.cookie = name + '=' + escape(value) + ';expires=' + exp.toGMTString();
    } else {
        document.cookie = name + '=' + escape(value);
    }
}


// 读取cookies
function getCookie(name) {
    var arr = 0;
    var reg = new RegExp('(^| )' + name + '=([^;]*)(;|$)');
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }

}

// 删除cookies
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getCookie(name);
    if (cval != null) {
        document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString();
    }

}