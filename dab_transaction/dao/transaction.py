from sqlalchemy import Column, String, Numeric, Date, Boolean, ForeignKey
from sqlalchemy.types import Integer
from sqlalchemy.orm import sessionmaker,relationship
from rest import *
from rest import base
from rest import db
from flask import json
from .account import Account


class Transaction(base):
    __tablename__ = 'transaction'

    transactionid = Column(Integer, primary_key=True)
    amount = Column(Numeric)
    valuedate = Column(Date)
    read = Column(Boolean)
    wording = Column(String)
    description = Column(String)
    iban = Column(String, ForeignKey('account.iban'))
    account = relationship(Account)
    status = Column(String)

    def __init__(self, data):
        logging.debug("Creation transaction object")
        self.__dict__.update(data)

    def add_transaction(self):
        logging.debug("Add transaction into the database")
        Session = sessionmaker(db)
        session = Session()
        session.add(self)
        session.commit()

