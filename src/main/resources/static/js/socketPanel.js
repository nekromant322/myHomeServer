// let sockets = fetchSockets();
// drawSockets(sockets);

refreshData();

// window.setInterval(refreshData, 5000);


function drawSockets(sockets) {
    sockets.sort((a, b) => a.name.localeCompare(b.name))
    for (let i = 0; i < sockets.length; i += 2) {
        let socket =
            `<div class="row">
                <div class="col-6">
                    <div class="container">
                        <input onclick="putSocket('${sockets[i].name}','${sockets[i].deviceStatus}','${sockets[i].deviceId}')" type="checkbox" name="toggle" id="${sockets[i].name}" class="toggle-button" data-toggle="tooltip" data-placement="top" title="${sockets[i].deviceId}"${sockets[i].deviceStatus === "ON" ? " checked" : ""}>                <label for="${sockets[i].name}" class="text">${sockets[i].name}</label>
                    </div>
                </div>
            `;
        if (i + 1 != sockets.length) {
            socket +=
                `<div class="col-6">
                 <div class="container">
                     <input onclick="putSocket('${sockets[i + 1].name}','${sockets[i + 1].deviceStatus}','${sockets[i + 1].deviceId}')" type="checkbox" name="toggle" id="${sockets[i + 1].name}" class="toggle-button" data-toggle="tooltip" data-placement="top" title="${sockets[i + 1].deviceId}"${sockets[i + 1].deviceStatus === "ON" ? " checked" : ""}>                <label for="${sockets[i + 1].name}" class="text">${sockets[i + 1].name}</label>
                 </div>
             </div>
            `;
        }
        socket += "        </div>\n";


        $("#socketContainer").append(socket);
    }


}

function fetchSockets() {
    let data;
    $.ajax({
        method: 'GET',
        url: "/socket",
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


function putSocket(name, currentdeviceStatus, deviceId) {
    let newSocketData = {
        name: name,
        deviceStatus: currentdeviceStatus === "ON" ? "OFF" : "ON",
        deviceId: deviceId
    }
    $.ajax({
        method: 'PUT',
        url: "/socket",
        async: false,
        contentType: 'application/json',
        data: JSON.stringify(newSocketData),
        success: function (response) {
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function dropSockets() {
    $(".row").remove();
}

function refreshData() {
    dropSockets();
    let sockets = fetchSockets();
    drawSockets(sockets)
}
