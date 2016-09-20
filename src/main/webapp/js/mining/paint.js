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
	$(html)
			.highcharts(
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie'
						},
						title : {
							text : property.title
						},
						xAxis : {
							categories : property.categories
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									format : '<b>{point.name}</b>: {point.percentage:.1f} %',
									style : {
										color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
												|| 'black'
									}
								}
							}
						},
						series : property.series
					});
}