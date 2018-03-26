var socket = io.connect('http://' + document.domain + ':' + location.port);

socket.on('card_read', function(data) {
    window.location = data.url;
});