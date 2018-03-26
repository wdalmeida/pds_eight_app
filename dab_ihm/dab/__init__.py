from flask import Flask, render_template, url_for
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from flask_socketio import SocketIO


app = Flask(__name__)
app.config.from_object("config")
socketio=SocketIO(app, ping_timeout=10, logger=True,async_mode='threading')
db = create_engine(app.config['DATABASE_URI'])
base = declarative_base()

import dab.controller.controller
