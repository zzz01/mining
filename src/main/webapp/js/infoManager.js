$(document).ready(
        function() {
            this.totalPages;
            var paginationDomTemp = "#paginationTemplate";
            var paginationTarget = "#contentSearchResultList";
            var $originLink = $("a[title='privateTopic']");
            var originPage = 1;
            $originLink.addClass('active');

            getPageContent( originPage ).done(function(){
                var that = this;
                var totalPages = that.totalPages;
                var visiblePages = utilGetVisiblePages( totalPages );
                $('#pagination').twbsPagination({
                    totalPages : totalPages, // need backend data totalPage
                    visiblePages : visiblePages, // set it.
                    onPageClick : function(event, page) {
                        // send page info here, something as ajax call
                        getPageContent(page);
                    },
                    first : '首页',
                    prev : '上一页',
                    next : '下一页',
                    last : '尾页'
                });
                eventBind();
                $(".form_datetime").datepicker();
            });

            function getDataOfPagination(data,page) {
                var page = page;
                var json = {};

                // data of pagination
                var resultList = []
                for(var index in data){
                    var obj = data[index];
                    var createTimeString = utilConvertTimeObject2String( obj.createTime);
                    var modifyTimeString = utilConvertTimeObject2String( obj.lastUpdateTime);
                    var resultElement = {
                            NO : page,
                            topicName : obj.issueName,
                            author : obj.creator,
                            createTime : createTimeString,
                            modifier : obj.lastOperator,
                            modifyTime : modifyTimeString
                    }
                    resultList.push(resultElement);
                }
                json.resultList = resultList;

                return json;
            }

            function utilGetVisiblePages( totalPages ){
                if( totalPages < 7 ){
                    return totalPages;
                } else {
                    return 7;
                }
            }

            function utilConvertTimeObject2String( docTime ){
                var year = 1900 + docTime.year;
                var month = docTime.month;
                var day = docTime.date;
                var hour = docTime.hours;
                var minute = docTime.minutes;
                var second = docTime.seconds;

                return year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + second;
            }

            function handleBarTemplate(tempDom, targetDom, context) {
                // 用jquery获取模板
                var source = $(tempDom).html();
                // 预编译模板
                var template = Handlebars.compile(source);

                // var html = template(context);
                // 输入模板
                $(targetDom).html(template(context));
            }

            function getPageContent(page) {
                var that = this;
                that.totalPages;
                that.$waitingMask = $(".waiting-mask");
                that.page = page;
                that.url = "http://218.199.92.27:8080/issue/queryOwnIssue";
                that.type = "POST";
                that.dataType = "json";
                that.contentType = "application/json";
                that.json = {
                    pageNo : page,
                    pageSize : 10,
                    issueName : $.trim($('#searchTopicName').val()),
                    user : $.trim($('#searchCreator').val()),
                    createStartTime : $.trim($('#searchStartTime').val()),
                    createEndTime : $.trim($('#searchEndTime').val()),
                    lastUpdateStartTime : $.trim($('#searchModifyStartTime').val()),
                    lastUpdateEndTime : $.trim($('#searchModifyEndTime').val()),
                };
                return $.ajax({
                    type : that.type,
                    url : that.url,
                    dateType : that.dateType,
                    contentType : that.contentType,
                    data : JSON.stringify(that.json),
                    beforeSend : function() {
                        that.$waitingMask.show();
                    },
                    success : function(data) {
                        that.totalPages = data.result.pageTotal;
                        if(data !== undefined && data !== ''){
                            if(data.status === 'OK'){
                                var resultList = getDataOfPagination(data.result.list,that.page);
                                handleBarTemplate(paginationDomTemp, paginationTarget,
                                        resultList);
                                that.$waitingMask.hide();
                            }else{
                                alert(data.result);
                            }
                        }
                    },
                    error : function() {
                        var resultList = getDataOfPagination(that.page);
                        handleBarTemplate(paginationDomTemp, paginationTarget,
                                resultList);
                        that.$waitingMask.hide();
                    }
                })
            }

            function eventBind() {
                var $navigationUl = $(".content-wrapper__nav__ul");
                $navigationUl.on("click", "a", onClickNavLink);
            }

            /**
             * left sidebar click event
             */
            function onClickNavLink(event) {
                var $waitingMask = $(".waiting-mask");
                var $oldActiveElement = $(".active");
                $waitingMask.show();
                var $this = $(event.currentTarget);
                var title = $this.text();
                var $target = $('.content-wrapper__content__info__child');
                var link = "url" + title;

                $target.text(title);
                $oldActiveElement.removeClass("active");
                $this.addClass("active");
                $waitingMask.hide();
            }
        });