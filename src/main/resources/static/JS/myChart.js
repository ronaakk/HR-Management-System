var niceChartData = decodeHtml(chartData);
// converting the string into a JS object 
var chartJsonArray = JSON.parse(niceChartData);

var ArrayLength = chartJsonArray.length;
var numericData = [];
var labelData = [];

for (var i = 0; i < ArrayLength; i++) {
	numericData[i] = chartJsonArray[i].stageCount;
	labelData[i] = chartJsonArray[i].label;
}


// Creating a pie chart 
new Chart(document.getElementById("myPieChart"), {
	type: 'pie',

	// Creating our data set
  	data: {
  	labels: labelData,
  	datasets: [{
    	label: 'My First Dataset',
    	data: numericData,
    	backgroundColor: ['rgb(255, 99, 132)', 'rgb(54, 162, 235)', 'rgb(255, 205, 86)'],
    	hoverOffset: 4
  		}]
  	},
  
  	// Configure options to go here
  	options: {
		plugins: {
			title: {
			display: true,
			text: 'Project Statuses'
			}	
		}
	}
  
});

// "[{"StageCount": 1, "label": "COMPLETED"},{"StageCount": 2, "label": "INPROGRESS"},{"StageCount": 1, "label": "NOTSTARTED"}]" 
// now it is much more readable in the console (whole thing is a string, as returned by this function)
function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}