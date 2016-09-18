function upload() {
	$('#fileUpload')
			.ajaxSubmit(
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
							// var xaixs = data.xaxis;
							// var yaixs = data.yaxis;
							// paintline(xaixs, yaixs, 'line', '#line');
							// paintline(xaixs, yaixs, 'bar', '#bar');
							// paintline(xaixs, yaixs, 'column', '#column');
							// // paintline(xaixs, yaixs, 'pie', '#pie');
							if (data.status !== 'OK') {
								alert(data.result);
								return;
							}
							var result = data.result;
							paintline(result.emotion, '#line_emotion');
							paintline(result.infoType, '#line_infotype');
							paintline(result.media, '#line_media');
							paintline(result.mediaAttention,
									'#line_mediaAttention');
							paintline(result.netizenAttention,
									'#line_netizenAttention');
						}
					});
}

function paintline(property, html) {
	$(html).highcharts({
		chart : {
			type : 'line'
		},
		title : {
			text : property.title
		},
		xAxis : {
			categories : property.categories
		},
		yAxis : {
			title : {
				text : '数量'
			}
		},
		series : property.series
	});
}