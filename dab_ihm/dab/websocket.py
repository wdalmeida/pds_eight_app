from flask_socketio import send, emit
from . import *
import websocket


@socketio.on('client_connected')
def handle_client_connect_event(json):
    print('received json: {0}'.format(str(json)))


@socketio.on('card_read')
def handle_nfc_tag_event():
    with app.test_request_context():
       socketio.emit('card_read', {'url': url_for('loading')})
