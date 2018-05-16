from flask_socketio import send, emit
from .. import *


@socketio.on('my event')
def handle_nfc_tag_event(data):
    with app.test_request_context():
        logging.debug("socket")
        socketio.emit('card_read', {'url': url_for('form_withdraw',iban=data)},namespace='/',broadcast=True)