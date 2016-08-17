$(document).ready(function() {
	alert("ddfa");
	// var cluster = request.getAttribute("clusterList");
	// alert(cluster);
	// var cluster= eval(${clusterList});
	// alert(cluster +"\t" +"1111");
});
$.getJSON("http://localhost:8080/mining/data/handle", {
	param : "clusterList"
}, function(data) {
	// 此处返回的data已经是json对象
	alert(data.length );
});
