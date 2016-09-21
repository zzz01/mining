function paintline(property, html) {
	$(html).highcharts({
		chart : {
			type : 'line'
		},
		title : {
			text : property.title,
			margin : 50,
			style : {
				fontWeight : 'bold',
				fontSize : '25px'
			}
		},
		xAxis : {
			categories : property.categories
		},
		yAxis : {
			title : {
				text : '数量'
			}
		},
		plotOptions : {
			line : {
				dataLabels : {
					enabled : true
				},
				enableMouseTracking : true
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
			text : property.title,
			margin : 50,
			style : {
				fontWeight : 'bold',
				fontSize : '25px'
			}
		},
		xAxis : {
			categories : property.categories
		},
		yAxis : {
			title : {
				text : '数量'
			}
		},
		plotOptions : {
			column : {
				dataLabels : {
					enabled : false
				},
				enableMouseTracking : true
			}
		},
		series : property.series
	});
}

function paintpie1(property, html) {
	$(html)
			.highcharts(
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie',

							options3d : {
								enabled : true,
								alpha : 45,
								beta : 0
							}
						},
						title : {
							text : property.title,
							margin : 50,
							style : {
								fontWeight : 'bold',
								fontSize : '25px'
							}
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

						// 黄、红、蓝、烟绿
						colors : [ "#FFFF66", "#FF6666", "#66CCFF", "#CCFF66" ],
						series : property.series
					});
}

function paintpie2(property, html) {
	$(html)
			.highcharts(
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie',

							options3d : {
								enabled : true,
								alpha : 45,
								beta : 0
							}
						},
						title : {
							text : property.title,
							margin : 50,
							style : {
								fontWeight : 'bold',
								fontSize : '25px'
							}
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
						// 黄、红、蓝、烟绿、粉红、紫、青色、灰色、紫红、深灰、某绿
						colors : [ "#FF6666", "#FFFF66", "#66CCFF", "#CCFF66",
								, "#FF99CC", "#333333", "#CCCC00", "#CC3366",
								"#FF99CC", "#996699", "#CCFFFF", "#CCFFFF" ],
						series : property.series
					});
}