let sockets = fetchSockets();
drawSockets(sockets)

function drawSockets(sockets) {

    for (let i = 0; i < sockets.length; i += 2) {


        let socket = "<div class=\"row\">\n" +
            "        <div class=\"col-6\">\n" +
            "            <div class=\"container\">\n" +
            "                <input type=\"checkbox\" name=\"toggle\" id=\"" + sockets[i].name + "\" class=\"toggle-button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"" + sockets[i].deviceId + "\"" + (sockets[i].deviceStatus === "ON" ? " checked" : "") + ">" +
            "                <label for=\"" + sockets[i].name + "\" class=\"text\">" + sockets[i].name + "</label>\n" +
            "            </div>\n" +
            "        </div>\n";
        if (i + 1 != sockets.length) {
            socket +=
                "        <div class=\"col-6\">\n" +
                "            <div class=\"container\">\n" +
                "                <input type=\"checkbox\" name=\"toggle\" id=\"" + sockets[i + 1].name + "\" class=\"toggle-button\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"" + sockets[i + 1].deviceId + "\"" + (sockets[i + 1].deviceStatus === "ON" ? " checked" : "") + ">" +
                "                <label for=\"" + sockets[i + 1].name + "\" class=\"text\">" + sockets[i + 1].name + "</label>\n" +
                "            </div>\n" +
                "        </div>\n";
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
