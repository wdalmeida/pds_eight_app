from flask import Flask, render_template
import dab.dao.customer as dao_cust
from .. import *


@app.route('/index/')
def index():
    cust = dao_cust.Customer()
    cust.get_customers(db)
    return render_template('index.html')


@app.route('/')
def index2():
    cust = dao_cust.Customer()
    cust.get_customers(db)
    return 'hello world'


