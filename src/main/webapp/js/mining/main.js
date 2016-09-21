function upload() {
	$('#downbtn').hide();
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
					fillhead(result.head, '#table_scan_head');
					fillbody(result.body, '#scan_table_body');
					fillselect(result.head);
				}
			});
}

function fillhead(head, element) {
	var html;
	for (var i = 0; i < head.length; i++) {
		html += "<th>" + head[i] + "</th>";
	}
	$(element).html(html);
}
function fillbody(body, element) {
	var html;
	for (var i = 0; i < body.length; i++) {
		html += "<tr>";
		var line = body[i];
		for (var j = 0; j < line.length; j++) {
			html += "<td>" + line[j] + "</td>";
		}
		html += "</tr>"
	}
	$(element).html(html);
}

function fillselect(head) {
	var html = "";
	for (var i = 0; i < head.length; i++) {
		var line = "<option value=\"" + i + "\">" + head[i] + "</option>";
		html += line;
	}
	$('#targetIndex').html(html);
	$('#timeIndex').html(html);
	$('#mediaIndex').html(html);
	$('#infoTypeIndex').html(html);
	$('#emotionIndex').html(html);
}

function cluster() {
	var json = {
		timeIndex : document.getElementById("timeIndex").value,
		emotionIndex : document.getElementById("emotionIndex").value,
		infoTypeIndex : document.getElementById("infoTypeIndex").value,
		mediaIndex : document.getElementById("mediaIndex").value,
		interval : document.getElementById("interval").value,
		targetIndex : document.getElementById("targetIndex").value
	}

	$.ajax({
		url : '/data/statistic',
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		data : JSON.stringify(json),
		error : function(data) {
			if (data.responseJSON === undefined
					|| data.responseJSON.msg === undefined) {
				alert('请求出错' + data);
			} else {
				alert(data.responseJSON.msg);
			}
		},
		success : function(data) {
			if (data.status !== 'OK') {
				alert(data.result);
				return;
			}
			$('#downbtn').show();
			var result = data.result;
			fillhead(result.clusterResult10row.head, '#table_result_head');
			fillbody(result.clusterResult10row.body, '#result_table_body');
			
			fillhead(result.origAndCount10row.head, '#table_orig_head');
			fillbody(result.origAndCount10row.body, '#result_orig_body');
			
			paintline(result.emotion, '#line_emotion');
			paintline(result.infoType, '#line_infotype');
			paintline(result.media, '#line_media');
			paintline(result.mediaAttention, '#line_mediaAttention');
			paintline(result.netizenAttention, '#line_netizenAttention');

			paintcolumn(result.emotion, '#column_emotion');
			paintcolumn(result.infoType, '#column_infotype');
			paintcolumn(result.media, '#column_media');
			paintcolumn(result.mediaAttention, '#column_mediaAttention');
			paintcolumn(result.netizenAttention, '#column_netizenAttention');
			paintcolumn(result.mediaCount, '#column_mediaCount');
			paintcolumn(result.infoTyeCount, '#column_infoTypeCount');
			paintcolumn(result.emotionCount, '#column_emotionCount');

			paintpie1(result.mediaCount, '#pie_mediaCount');
			paintpie2(result.infoTyeCount, '#pie_infoTypeCount');
			paintpie1(result.emotionCount, '#pie_emotionCount');
		}
	});
}

function download() {
	// document.getElementById('miningForm').action = '/file/download';
	$('#miningForm').attr('action', '/file/download');
	$('#miningForm').submit();
}
