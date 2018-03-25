from flask import Flask, render_template
import dab.dao.customer as dao_cust
from .. import *
from ..service.home_service import home_service as service


@app.route('/')
def index():
    cust = dao_cust.Customer()
    cust.get_customers(db)
    wait=service('127.0.0.1',5001,app)
    wait.start()
    return render_template('index.html')

@app.route('/loading/')
def loading():
    return render_template('loading.html')

