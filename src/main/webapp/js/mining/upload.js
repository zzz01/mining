function upload() {
	$('#fileUpload').ajaxSubmit(
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
					var interval = data.interval;
					var emotion = data.emotion;
					var type = data.type;
					var resource = data.resource;
					var xaixs = interval.xaixs;
					var yaixs = interval.yaixs;
					paintline('line', '多悦小学事件', xaixs, '发布数量', yaixs);
					paintcolumn('column', '多悦小学事件', xaixs, '发布数量', yaixs);
					paintpie('pie','多悦小学事件','发布量',emotion);
					paintpie('type','多悦小学事件','发布量',type);
					paintpie('resource','多悦小学事件','发布量',resource);
//					paintline(xaixs,yaixs,'line','#line');
//					paintline(xaixs,yaixs,'bar','#bar');
//					paintline(xaixs,yaixs,'column','#column');
				}
			});
}
//
//function paintline(xaixs,yaixs,type,html) {
//	$(html).highcharts({
//		chart : {
//			type : type
//		},
//		title : {
//			text : '事件每日发布量走势图'
//		},
//		legend: {
//            layout: 'vertical',
//            align: 'right',
//            verticalAlign: 'middle',
//            borderWidth: 0
//        },
//		xAxis : {
//			categories : xaixs
//		},
//		yAxis : {
//			title : {
//				text : '每日发布量'
//			}
//		},
//		series : [ {
//			name : '网页数量',
//			data : yaixs
//		} ]
//	});
//}