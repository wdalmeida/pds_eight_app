from flask import Flask, render_template,request,redirect,url_for
from .. import *
from ..service.home_service import home_service
from ..service.withdraw_service import withdraw_service


@app.route('/', methods= ['GET'])
def index():
    logging.debug("Controller - Home")
    wait=home_service(app.config['TRANSACTION_SERVER'],app.config['TRANSACTION_SERVER_PORT'])
    wait.start()
    return render_template('index.html')


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
        return 'WITHDRAW {}'.format(amount)
    else:
        logging.debug("Operation cancel")
        return redirect(url_for("index"))