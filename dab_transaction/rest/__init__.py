from flask import Flask, render_template, url_for
import logging
import logging.config
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base

app = Flask(__name__)

#config file
app.config.from_object("config")

#Database
db = create_engine(app.config['DB_SERVER'])
base = declarative_base()

#Logger
logging.config.fileConfig(app.config['LOG_CONF'])
logger = logging.getLogger(app.config['LOG_ENV'])


import rest.controller.controller
