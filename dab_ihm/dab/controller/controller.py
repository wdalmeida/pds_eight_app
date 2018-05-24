from flask import Flask, render_template, request, redirect, url_for,session,json
from .. import *
from ..service.home_service import home_service
from ..service.withdraw_service import withdraw_service
import requests
import datetime


@app.route('/', methods=['GET'])
def index():
    logging.debug("Controller - Home")
    wait = home_service(app.config['NFC_READER_IP'], app.config['NFC_READER_PORT'])
    wait.start()
    return render_template('index.html')


@app.route('/withdraw/<iban>', methods=['GET'])
def form_withdraw(iban):
    logging.debug("Controller - Form withdraw")
    logging.debug(iban)
    return render_template('withdraw.html', iban=iban)


@app.route("/withdraw/", methods=['POST'])
def withdraw():
    logging.debug("Controller - withdraw")
    amount = withdraw_service.amount_withdraw(request.form)
    iban = withdraw_service.get_iban(request.form)
    if not amount == None:
        logging.debug("Controller - customer withdraw %s", amount)
        logging.debug("Controller - customer withdraw %s", iban)

        r = requests.post(app.config['TRANSACTION_SERVER'] + "transaction/waiting/", data=json.dumps({
            "amount": amount,
            "valuedate": datetime.date.today(),
            "read": "0",
            "wording": "DAB",
            "description": "RETRAIT DAB ESIPE-CRETEIL",
            "iban": iban,
            "status": "waiting"
        }), headers={'Content-type': 'application/json'})
        return render_template('confirm.html')
    else:
        logging.debug("Operation cancel")
        return redirect(url_for("index"))
