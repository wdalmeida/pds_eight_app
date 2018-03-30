from flask import Flask, render_template, url_for
from flask_socketio import SocketIO
import logging
import logging.config
from os import path


app = Flask(__name__)

#config file
app.config.from_object("config")

#Websocket
socketio=SocketIO(app, ping_timeout=1, logger=False, async_mode='threading')

#Logger
logging.config.fileConfig(app.config['LOG_CONF'])
logger = logging.getLogger(app.config['LOG_ENV'])


import dab.controller.controller
