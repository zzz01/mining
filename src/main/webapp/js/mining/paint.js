function paintline(html, title, categories, yTitle, series) {
	$('#' + html).highcharts(
			{
				chart : {
					type : 'line'
				},
				title : {
					text : title
				},
				xAxis : {
					categories : categories
				},
				yAxis : {
					title : {
						text : yTitle
					}
				},
				tooltip : {
					enabled : false,
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' + this.x
								+ ': ' + this.y;
					}
				},
				plotOptions : {
					line : {
						dataLabels : {
							enabled : true
						},
						enableMouseTracking : false
					}
				},
				series : series
			});
}

function paintcolumn(html, title, categories, yTitle, series) {
	$('#' + html)
			.highcharts(
					{
						chart : {
							type : 'column',
							margin : [ 50, 50, 100, 80 ]
						},
						title : {
							text : title
						},
						xAxis : {
							categories : categories,
							labels : {
								rotation : -45,
								align : 'right',
								style : {
									fontSize : '13px',
									fontFamily : 'Courier New'
								}
							}
						},
						yAxis : {
							min : 0,
							title : {
								text : yTitle
							}
						},
						legend : {
							enabled : false
						},
						series : series
					});
}

function paintpie(html,title,name,data){
	$('#'+html).highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: title
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: name,
            data: data
        }]
    });
}
