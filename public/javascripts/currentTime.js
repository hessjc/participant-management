function display_c() {
    var refresh = 1000; // Refresh rate in milli seconds
    mytime = setTimeout('display_currentTime()', refresh);
}

function display_currentTime() {
    document.getElementById('currentTime').innerHTML = new Date().format("dddd, 'der' dd. mmmm HH:MM:ss");
    display_c();
}