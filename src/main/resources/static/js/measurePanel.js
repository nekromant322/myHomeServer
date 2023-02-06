let ctx = document.getElementById('chart').getContext('2d');

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
var deviceName = urlParams.get('deviceName')
var period = "DAY"

// let data = fetchData(deviceName, period);

let myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: [0, 1, 2, 3 ,4],
        datasets: [{
            label: 'My First Dataset',
            data: [65, 59, 80, 81, 56, 55, 40],
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
        url: "/measure?deviceName" + deviceName,
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