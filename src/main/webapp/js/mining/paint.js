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

function paintcolumn(property, html) {
	$(html).highcharts({
		chart : {
			type : 'column'
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

function paintpie(property, html) {
	$(html).highcharts({
		chart : {
			type : 'pie'
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