$(document).ready(
        function() {
            var resultList;
            var paginationDomTemp = "#paginationTemplate";
            var paginationTarget = "#contentSearchResultList";
            var $originLink = $("a[title='privateTopic']");
            $originLink.addClass('active');

            $(function() {
                var obj = $('#pagination').twbsPagination({
                    totalPages : 35, // need backend data totalPage
                    visiblePages : 7, // set it.
                    onPageClick : function(event, page) {
                        // send page info here, something as ajax call
                        getPageContent(page);
                    },
                    first : '首页',
                    prev : '上一页',
                    next : '下一页',
                    last : '尾页'
                });

                resultList = mockDataOfPagination(1);
                handleBarTemplate(paginationDomTemp, paginationTarget,
                        resultList);
                eventBind();
            });

            $(".form_datetime").datepicker();

            function mockDataOfPagination(data,page) {
                var i;
                var page = page;
                var json = {};

                // mock data of pagination
                var resultList = []
                for(var index in data){
                    var obj = data[index];
                    var resultElement = {
                            NO : page,
                            topicName : obj.issueName,
                            author : obj.creator,
                            createTime : obj.createTime,
                            modifier : obj.lastOperator,
                            modifyTime : obj.lastUpdateTime
                    }
                    resultList.push(resultElement);
                }
                json.resultList = resultList;

                return json;
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
                that.$waitingMask = $(".waiting-mask");
                that.page = page;
                that.url = "http://localhost:8080/issue/queryOwnIssue";
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
                        if(data !== undefined && data !== ''){
                            if(data.status === 'OK'){
                                var resultList = mockDataOfPagination(data.result,that.page);
                                handleBarTemplate(paginationDomTemp, paginationTarget,
                                        resultList);
                                that.$waitingMask.hide();
                            }else{
                                alert(data.result);
                            }
                        }
                    },
                    error : function() {
                        var resultList = mockDataOfPagination(that.page);
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