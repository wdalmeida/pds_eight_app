from flask import Flask,request,jsonify,json
from rest import *
from rest.service.transaction_service import transaction_service as service
from message_producer.send_waiting_transaction import send as kafkaSend
from message_consumer.receive_waiting_transaction import read as kafkaRead


@app.route('/transaction/waiting/', methods= ['POST'])
def create_waiting_transaction():
    resp = None
    logging.debug("Controller - Home")
    logging.debug(request.data)
    logging.debug(request.get_json(force=True))
    logging.debug("Where does it fail ?")

    if request.json and request.headers['Content-Type'] == 'application/json':
        data = json.dumps(request.json)
        if kafkaSend(data):
            print (data.transaction_id)
            resp = kafkaRead(data.transaction_id)
        #resp = service.create_transaction(data,db)
    else:
        resp = unknown_ressource()
    return resp


@app.errorhandler(400)
def unknown_ressource(error=None):
    message = {
            'status': 400,
            'message': 'Bad Request for ressource: ' + request.url,
    }
    resp = jsonify(message)
    resp.status_code = 400

    return resp