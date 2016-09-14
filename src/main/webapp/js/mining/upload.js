function upload() {
	$('#file').ajaxSubmit(
			{
				type : "POST",
				error : function(data) {
					if (data.responseJSON == undefined
							|| data.responseJSON.msg == undefined) {
						alert("请求出错");
					} else {
						alert(data.responseJSON.msg);
					}
				},
				success : function(data) {
					var xaixs = data.xaxis;
					var yaixs = data.yaxis;
					paintline(xaixs, yaixs, 'line', '#line');
					paintline(xaixs, yaixs, 'bar', '#bar');
					paintline(xaixs, yaixs, 'column', '#column');
					paintline(xaixs, yaixs, 'pie', '#pie');
				}
			});
}

function paintline(xaixs, yaixs, type, html) {
	$(html).highcharts({
		chart : {
			type : type
		},
		title : {
			text : '事件每日发布量走势图'
		},
		
		xAxis : {
			categories : xaixs
		},
		yAxis : {
			title : {
				text : '每日发布量'
			}
		},
		series : [ {
			name : '网页数量',
			data : yaixs
		}, {
			name : '网页数量',
			data : yaixs
		} ]
	});
}