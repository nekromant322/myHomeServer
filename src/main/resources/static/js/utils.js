 function hashCode(s) {
    return s.split("").reduce(function(a, b) {
        a = ((a << 5) - a) + b.charCodeAt(0);
        return a & a;
    }, 0);
}


function rgbForString(str) {
    let hash = hashCode(str);
    let red = (hash << 15) % 255
    let green = (hash << 20) % 255
    let blue = (hash << 25) % 255
    return "rgb(" + red + ","+green + "," + blue + ")"
}