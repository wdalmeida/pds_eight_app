from flask import Flask, render_template,request,redirect,url_for
from .. import *
from ..service.home_service import home_service
from ..service.withdraw_service import withdraw_service
import requests

@app.route('/', methods= ['GET'])
def index():
    logging.debug("Controller - Home")
    wait=home_service(app.config['NFC_READER_IP'],app.config['NFC_READER_PORT'])
    wait.start()
    return render_template('index.html')

def auth():
    logging.debug("Authentification")
    r = requests.post(app.config['TRANSACTION_SERVER'] + "/auth/card/", data={
        "card_id": "2",
    }, headers={'Content-type': 'application/json'})
    if r==200:
        return True
    else:
        return None


@app.route('/withdraw/', methods= ['GET'])
def form_withdraw():
    logging.debug("Controller - Form withdraw")
    return render_template('withdraw.html')


@app.route("/withdraw/", methods= ['POST'])
def withdraw():
    logging.debug("Controller - withdraw")
    amount = withdraw_service.amount_withdraw(request.form)
    if not amount==None:
        logging.debug("Controller - customer withdraw %s",amount)
        r = requests.post(app.config['TRANSACTION_SERVER']+"/transaction/waiting/", data={
                                                        "transaction_id":"2",
                                                        "amount":"10.90",
                                                        "date":"2018/03/31",
                                                        "red":"0",
                                                        "wording":"Test",
                                                        "description":"Test 1"
                                                            },headers = {'Content-type': 'application/json'})
        return 'WITHDRAW {} # {}'.format(amount,r)
    else:
        logging.debug("Operation cancel")
        return redirect(url_for("index"))