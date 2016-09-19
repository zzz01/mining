function upload() {
	document.getElementById('fileUpload').action='/upload/scanfile';
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
					if (data.status === -1) {
						alert(data.result);
						return;
					}
					var result = data.result;
					fillhead(result.head);
					fillbody(result.body);
					fillselect(result.head);
				}
			});
}

function fillhead(head) {
	var html;
	for (var i = 0; i < head.length; i++) {
		html += "<th>" + head[i] + "</th>";
	}
	$('#table_scan_head').append(html);
}
function fillbody(body) {
	var html;
	for (var i = 0; i < body.length; i++) {
		html += "<tr>";
		var line = body[i];
		for (var j = 0; j < line.length; j++) {
			html += "<td>" + line[j] + "</td>";
		}
		html += "</tr>"
	}
	$('#scan_table_body').append(html);
}

function fillselect(head) {
	var html="";
	for (var i = 0; i < head.length; i++) {
		var line = "<option value=\"" + i +"\">" + head[i] + "</option>";
		html +=line;
	}
	$('#select_target').html(html);
	$('#select_time').html(html);
	$('#select_media').html(html);
	$('#select_infotype').html(html);
	$('#select_emotion').html(html);
}

function cluster() {
	document.getElementById('fileUpload').action='/data/statistic';
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
							
							paintcolumn(result.emotion, '#column_emotion');
							paintcolumn(result.infoType, '#column_infotype');
							paintcolumn(result.media, '#column_media');
							paintcolumn(result.mediaAttention, '#column_mediaAttention');
							paintcolumn(result.netizenAttention, '#column_netizenAttention');
							paintcolumn(result.mediaCount, '#column_mediaCount');
							paintcolumn(result.infoTyeCount,
							'#column_infoTypeCount');
							paintcolumn(result.emotionCount, '#column_emotionCount');
							
							
							paintpie(result.mediaCount, '#pie_mediaCount');
							paintpie(result.infoTyeCount,
									'#pie_infoTypeCount');
							paintpie(result.emotionCount, '#pie_emotionCount');
						}
					});
}
