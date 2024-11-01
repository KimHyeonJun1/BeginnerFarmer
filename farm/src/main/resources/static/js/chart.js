/**
 * 시각화 차트
 */

Chart.defaults.font.size = 12; //폰트 사이즈
Chart.defaults.plugins.legend.position = 'bottom'; //범례위치 
Chart.defaults.layout.padding.top =  30; //위쪽 여백
Chart.register(ChartDataLabels); // Register the plugin to all charts:
Chart.defaults.set('plugins.datalabels', { // Change default options for ALL charts
  color: '#000',
  anchor: 'end',
  offset: -25,
  align: 'start',
});
const autocolors = window['chartjs-plugin-autocolors'];
Chart.register(autocolors);
const colors = ["#e31717","#f5e105","#1df505","#0585f5","#ad05f5","#f50595"];

/*
 1~10: 0	9: *0.1=0.9 -> 1
11~20: 1   20: *0.1=2.0 -> 2
21~30: 2   23: *0.1=2.3 -> 3
31~40: 3
41~50: 4   49: *0.1=4.9 -> 5 
51~  : 5
*/

/**
 * 현재 시간 기준으로 30분 단위로 최근 10개 시간을 계산합니다.
 * @returns {Array} 계산된 시간 배열 (형식: ["HH:MM", ...])
 */
function getRecentTimeLabels() {
    var now = new Date();
    var labels = [];

    // 현재 시간을 기준으로 30분 단위로 10개의 시간 계산
    for (let i = 0; i < 10; i++) {
        var pastDate = new Date(now.getTime() - ((9 - i) * 30) * 60000); // 30분 단위로 감소
        var hours = pastDate.getHours().toString().padStart(2, '0'); // 시간 포맷
        var minutes = Math.floor(pastDate.getMinutes() / 30) * 30; // 30분 단위로 계산
        var formattedMinutes = minutes.toString().padStart(2, '0'); // 분 포맷
        labels.push(`${hours}:${formattedMinutes}`); // "HH:MM" 형식으로 추가
    }

    return labels; // 배열의 순서를 변경하지 않음 (최신 데이터가 오른쪽에 위치)
}







function makeLegend(max){
	//54 *0.1=5.4 -> 6
	max = Math.ceil(max *0.1)-1;
	var tag = '';
	for(var i=0; i<=max; i++){
		tag += `<li class="d-flex align-items-center gap-1">
					<span></span>
					<label>${i*10+1}명~${(i+1)*10}명</label>
				</li>`
	}
	tag = `<ul class="d-flex justify-content-center my-2 mt-5 gap-3" id="legend">${tag}</ul>`
	$("#tab-content").after(tag)
	
	//각 범례에 맞는 색상 지정
	$("#legend span").each(function(idx){
		$(this).css("background-color", colors[idx])
	})
}

function top3Chart(info){
	
	info.type = info.unit == 'month' ? 'line' : 'bar'
	info.datasets = []
	for(var idx=0; idx<info.data.length; idx++){
		var department = {}
		department.label = info.name[idx];
		department.data = info.data[idx];
		department.backgroundColor = colors[idx]; //bar 차트인 경우
		department.borderColor = colors[idx]; // line 차트인 경우
		info.datasets.push( department )
	}
	new Chart( $("#chart"), {
		type: info.type,
		data: {
			labels: info.labels,
			datasets: info.datasets,
		},
		options: setOptions(`${info.unit=="year"? "년도": "월"}별 채용인원수`)
	})
}

function setOptions(title){
	
	return  {
		scales: {
		      y: {
		        title: {
		          color: '#000',
		          display: true,
		          text: title,
		        }
		      }
		    },
		
		plugins: {
			legend: { display: false, },
			datalabels: {
				formatter: function(value, item){
					return value==0 ?'': value;
				}
			}
		},
	}
}


function unitChart( info ){
	info.color = info.data.map(function(data){
		return colors[Math.ceil(data*0.1)-1 ]
	})
	
	new Chart( $("#chart"),{
		type: 'bar',
		data: {
			labels: info.labels,
			datasets: [{
				data: info.data,
				barPercentage: 0.5,
				backgroundColor: info.color
			}]
		},
		options: setOptions( (info.unit=='year' ? '년도별' : '월별') + ' 채용인원수')
		
	})	
	
	makeLegend(Math.max(...info.data))
}