from flask import Flask, render_template
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base

app = Flask(__name__)
app.config.from_object("config")
db = create_engine(app.config['DATABASE_URI'])
base = declarative_base()

import dab.controller.controller
