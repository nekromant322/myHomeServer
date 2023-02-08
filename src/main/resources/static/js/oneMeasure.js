let ctx = document.getElementById('chart').getContext('2d');

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
var deviceName = urlParams.get('deviceName')
var period = "DAY"

var data = fetchData(deviceName, period);

let myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: data.map(x => x.dateTime),
        datasets: [{
            label: deviceName,
            data: data.map(x => x.value),
            fill: false,
            borderColor: 'rgb(75, 192, 192)',
            tension: 0.1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        title: {
            display: true,
            text: 'Датчик ' + deviceName
        }
    }
});


function fetchData(deviceName, period) {
    let data;
    $.ajax({
        method: 'GET',
        url: "/measure?deviceName=" + deviceName + "&period=" + period,
        async: false,
        success: function (response) {
            data = response;
        },
        error: function (error) {
            console.log(error);
        }
    });
    return data;
}

function updateChart() {
    myChart.data.datasets = statPerWeek.userStats;
    myChart.data.labels = statPerWeek.labels
    myChart.update();
}