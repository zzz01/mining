$(document).ready(function() {
    var issueName = mining.getParam('issueName');
    var creator = mining.getParam('user');
    var createStartTime = mining.getParam('createStartTime');
    var createEndTime = mining.getParam('createEndTime');
    var lastUpdateStartTime = mining.getParam('lastUpdateStartTime');
    var lastUpdateEndTime = mining.getParam('lastUpdateEndTime');
    if (issueName != null && issueName != undefined && issueName != "") {
        $('#searchIssueName').val(decodeURIComponent(issueName));
    }
    $('#searchCreateUser').val(creator);
    $('#searchCreateStartTime').val(createStartTime);
    $('#searchCreateEndTime').val(createEndTime);
    $('#searchModifyStartTime').val(lastUpdateStartTime);
    $('#searchModifyEndTime').val(lastUpdateEndTime);
    mining.init();
    var currentPage = mining.getParam('currentPage');
    if (currentPage != null) {
        mining.query.page = currentPage;
    }
    mining.query.getIssueList();
});
var mining = {
    url : '',
    init : function() {
        var url = window.location.href;
        var pos = url.indexOf('/', 7);
        if (pos > 0) {
            url = url.substring(0, pos);
        } else {
            pos = url.indexOf('/', 7);
            if (pos > 0) {
                url = url.substring(0, pos);
            }
        }
        mining.url = url;
    },
    getParam : function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg); // 匹配目标参数
        if (r != null)
            return unescape(r[2]);
        return null; // 返回参数值
    }
};
mining.query = {
    page : 1,
    before : function() {
        var page = mining.query.page - 1;
        mining.query.page = page < 1 ? 1 : page;
        $('#issuelist').html('');
        mining.query.getIssueList();
    },
    next : function() {
        mining.query.page = mining.query.page + 1;
        $('#issuelist').html('');
        mining.query.getIssueList();
    },
    toPage : function(page) {
        mining.query.page = page;
        $('#issuelist').html('');
        mining.query.getIssueList();
    },
    // 显示分页逻辑
    showPageUI : function(page) {
        var pageShow = 5;
        var beginPage = page.currentPage - (pageShow - 1) / 2;
        var endPage = page.currentPage + (pageShow - 1) / 2;
        if (beginPage < 1)
            beginPage = 1;
        if (endPage - beginPage < (pageShow - 1))
            endPage = beginPage + (pageShow - 1);
        if (endPage > page.pageTotal)
            endPage = page.pageTotal;
        if (endPage - beginPage < (pageShow - 1))
            beginPage = endPage - (pageShow - 1);
        if (beginPage < 1)
            beginPage = 1;
        var con = [];
        if (page.currentPage > 1) {
            con
                    .push('<li class="first-child"><a href="#" onclick="mining.query.toPage(1);">首页</a></li>');
            con
                    .push('<li><a href="#" onclick="mining.query.before();">上一页</a>');
        } else {
            con.push('<li class="first-child active"><span>首页</span></li>');
            con.push('<li class="active"><span>上一页</span>');
        }
        ;
        for (var i = beginPage; i <= endPage; i++) {
            if (i == page.currentPage) {
                con.push('<li class="active"><span>' + i + '</span>');
            } else {
                con.push('<li><a href="#" onclick="mining.query.toPage(' + i
                        + ');">' + i + '</a>');
            }
        }
        ;
        if (page.currentPage < page.pageTotal) {
            con.push('<li><a href="#" onclick="mining.query.next();">下一页</a>');
            con
                    .push('<li class="last-child"><a href="#" onclick="mining.query.toPage('
                            + page.pageTotal + ');">末页</a></li>');
        } else {
            con.push('<li class="active"><span>下一页</span>');
            con.push('<li class="last-child active"><span>末页</span></li>');
        }
        con.push('<li><span id="page_no">第' + page.currentPage + '页/共'
                + page.pageTotal + '页</span></li>');
        $('#pageUI').html(con.join(''));
    },
    getIssueList : function() {
        var search_issueName = $.trim($('#searchIssueName').val());
        var search_creator = $.trim($('#searchCreator').val());
        var search_createStartTime = $.trim($('#searchCreateStartTime').val());
        var search_createEndTime = $.trim($('#searchCreateEndTime').val());
        var search_lastUpdateStartTime = $.trim($('#searchLastUpdateStartTime')
                .val());
        var search_lastUpdateEndTime = $('#searchLastUpdateEndTime').val();
        var time1 = new Date(search_createStartTime).getTime();
        var time2 = new Date(search_createEndTime).getTime();
        var time3 = new Date(search_lastUpdateStartTime).getTime();
        var time4 = new Date(search_lastUpdateEndTime).getTime();
        // if ((!time1 && time2) || (time1 && !time2)) {
        // alert('必须同时选择申请时间始和申请时间终');
        // return;
        // }
        // var date = new Date();
        // var year = date.getFullYear();
        // var month = date.getMonth();
        // var day = date.getDay();
        // if(!time1){
        // search_createStartTime = new Date()
        // }
        if (time1 > time2) {
            alert('申请时间始小于申请时间终');
            return;
        }
        if (time3 > time4) {
            alert('审批时间始小于审批时间终');
            return;
        }
        var json = {
            pageSize : 10,
            pageNo : mining.query.page,
            issueName : search_issueName,
            user : search_creator,
            createStartTime : search_createStartTime === '' ? ''
                    : search_createStartTime + ' 00:00:00',
            createEndTime : search_createEndTime === '' ? ''
                    : search_createEndTime + ' 00:00:00',
            lastUpdateStartTime : search_lastUpdateStartTime === '' ? ''
                    : search_lastUpdateStartTime + ' 00:00:00',
            lastUpdateEndTime : search_lastUpdateEndTime === '' ? ''
                    : search_lastUpdateEndTime + ' 00:00:00',
        }
        $('#issuelist').html('');
        var url = mining.url + '/issue/queryOwnIssue';
        $
                .ajax({
                    type : 'post',
                    url : url,
                    dataType : 'json',
                    contentType : "application/json",
                    data : JSON.stringify(json),
                    error : function(data) {
                        if (data.responseJSON == undefined
                                || data.responseJSON.msg == undefined) {
                            alert('请求错误');
                        } else {
                            alert(data.responseJSON.msg);
                        }
                    },
                    success : function(data) {
                        if (data) {
                            if (data.status === 'OK') {
                                // if (data.result.page == null)
                                // data.result.page = {
                                // pageSize : 10,
                                // 'currentPage' : 1,
                                // 'pageTotal' : 10
                                // };
                                // mining.query.showPageUI(data.result.page);
                                if (data.result != null
                                        && data.result != undefined) {
                                    var html = '';
                                    for ( var index in data.result) {
                                        var obj = data.result[index];
                                        html += '<tr>' + '<td>'
                                                + (parseInt(index) + parseInt(1))
                                                + '</td>'
                                                + '<td>'
                                                + '<a href="test?issueid='
                                                + obj.issueId
                                                + '&currentPage='
                                                + mining.query.page
                                                /* 查询条件 */
                                                + '&issueName='
                                                + encodeURIComponent(encodeURIComponent(search_issueName))
                                                + '&user='
                                                + search_creator
                                                + '&createStartTime='
                                                + search_createStartTime
                                                + '&createEndTime='
                                                + search_createEndTime
                                                + '&lastUpdateStartTime='
                                                + search_lastUpdateStartTime
                                                + '&lastUpdateEndTime='
                                                + search_lastUpdateEndTime
                                                /* 查询条件 */
                                                + '">'
                                                + obj.issueName
                                                + '</a></td>'
                                                + '<td>'
                                                + obj.creator
                                                + '</td>'
                                                + '<td>'
                                                + dateFormat(obj.createTime)
                                                + '</td>'
                                                + '<td> '
                                                + obj.lastOperator
                                                + '</td>'
                                                + '<td> '
                                                + dateFormat(obj.lastUpdateTime)
                                                + '</td>' + '</tr>';
                                    }
                                    $('#issuelist').empty();
                                    $('#issuelist').html(html);
                                }
                            } else {
                                alert(data.msg);
                            }
                        }
                        // countHistory();
                    }
                });
    }
}

function parseOccurTime(time) {
    if (time && 14 === time.length) {
        return time.substr(0, 4) + "-" + time.substr(4, 2) + "-"
                + time.substr(6, 2) + " " + time.substr(8, 2) + ":"
                + time.substr(10, 2) + ":" + time.substr(12, 2);
    } else {
        return '--';
    }
}

function dateFormat(time) {
    if (time === null || time === undefined || time === '') {
        time = "0000-00-00 00:00:00";
    } else {
        time = new Date(time);
        alert(time);
        time = time.format('yyyy-MM-dd hh:mm:ss');
    }
    return time;
}
