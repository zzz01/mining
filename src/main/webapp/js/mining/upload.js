function upload(){
	$.ajax({
		url:'/mining/test/upload',
		type:'post',
		fileElementId:'exampleInputFile',
		//data:$("#uploadForm").serialize(),
		dataType:'json',
		eorror:function(){
			alert("请求出错");
		},
		success:function(data){
			var table_body;
			$(data).each(function(key_row,row){
//				table_body+="<tr>"+
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.id+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.website+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.plate+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.title+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.poster+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.postTime.format('yyyy-MM-dd hh:mm:ss')+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.link+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.hitNum+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.replyNum+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.source+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.ip+"</td>" +
//				"<td style=\"text-align: center; vertical-align: middle;\">"+row.ipSite+"</td>" +
//				"</tr>";
				
				table_body+="<tr>"+
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[0]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[1]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[2]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[3]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[4]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[5].format('yyyy-MM-dd hh:mm:ss')+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[6]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[7]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[8]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[9]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[10]+"</td>" +
				"<td style=\"text-align: center; vertical-align: middle;\">"+row[11]+"</td>" +
				"</tr>";
			});
			$('#excel_table_body').append(table_body);
		}
	});
}