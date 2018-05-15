from rest import *


def readToBoolean(read):
    if read == '0':
        read = False
    elif read == '1':
        read = True
    return read


def create_transaction(data):
    # trans = transaction.Transaction(data)
    logging.debug(type(data))
    logging.debug(data.get('read'))
    data['read'] = readToBoolean(data.get('read'))
    offset = sendWaiting(data)
    if offset is not False:
        logging.debug(offset)
        readWaiting(offset)
        sendApproved(data)
    return {
            'status': 200,
            'message': 'Message sent: ' + data }



