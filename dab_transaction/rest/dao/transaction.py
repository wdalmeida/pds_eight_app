from sqlalchemy import Column, String, Numeric, Date, Boolean
from sqlalchemy.types import Integer
from sqlalchemy.orm import sessionmaker
from rest import *
from rest import base
from rest import db
from flask import json


class Transaction(base):
    __tablename__ = 'transaction'

    transaction_id = Column(Integer, primary_key=True)
    amount = Column(Numeric)
    date = Column(Date)
    red = Column(Boolean)
    wording = Column(String)
    description = Column(String)

    def __init__(self, data):
        self.__dict__.update(json.loads(data))

    def add_transaction(self):
        Session = sessionmaker(db)
        session = Session()
        session.add(self)
        session.commit()

