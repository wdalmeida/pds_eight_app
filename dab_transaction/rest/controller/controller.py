from flask import Flask,request,jsonify,json
from rest import *
from rest.service.transaction_service import transaction_service as tservice
from rest.service.authentification_service import authentification as aservice
from message_producer.send_waiting_transaction import send as kafkaSend
from message_consumer.receive_waiting_transaction import read as kafkaRead
import simplejson as json2



@app.route('/transaction/waiting/', methods= ['POST'])
def create_waiting_transaction():
    resp = None
    logging.debug("Controller - Home")
    logging.debug(request.data)
    logging.debug(request.get_json(force=True))

    if request.json and request.headers['Content-Type'] == 'application/json':
        data = request.get_json()
        logging.debug(request.get_json())
        logging.debug(data)
        if kafkaSend(data):
            logging.debug(data["transaction_id"])
            resp = kafkaRead(data["transaction_id"])
        #resp = tservice.create_transaction(data,db)
    else:
        resp = unknown_ressource()
    return jsonify(resp)

@app.route('/auth/card/', methods= ['POST'])
def auth_client_by_smartcard():
    resp = None
    logging.debug("Controller - Authentification")
    logging.debug(request.data)
    logging.debug(request.get_json(force=True))

    if request.json and request.headers['Content-Type'] == 'application/json':
        data = request.get_json()
        logging.debug(request.get_json())
        logging.debug(data)
        res = aservice.authentification_card(data.get("card"))
        if res is not None:
            return json2.dumps(res, use_decimal=True)
        else:
            resp = unknown_ressource()
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