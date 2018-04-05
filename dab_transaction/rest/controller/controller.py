from flask import Flask, render_template,request,redirect,url_for, Response,jsonify,json
from rest import *
from rest.service.transaction_service import transaction_service as service

@app.route('/transaction/waiting', methods= ['POST'])
def create_waiting_transaction():
    logging.debug("Controller - Home")
    resp=None
    if (request.json and request.headers['Content-Type'] == 'application/json'):
        data =json.dumps(request.json)
        #add kafka producer
        #add kafka consumer
        resp = jsonify(data)
        resp.status_code = 200
        resp= service.create_transaction(data,db)
    else:
        resp= unknown_ressource()
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