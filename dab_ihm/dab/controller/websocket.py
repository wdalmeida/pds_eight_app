from flask_socketio import send, emit
from .. import *


@socketio.on('card_read')
def handle_nfc_tag_event():
    with app.test_request_context():
       socketio.emit('card_read', {'url': url_for('loading')})
