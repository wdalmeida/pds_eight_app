from sqlalchemy import Column, String, Date, ForeignKey, Numeric
from sqlalchemy.types import Integer
from sqlalchemy.orm import sessionmaker, relationship
from rest import *
from rest import base
from rest import db
from .customer import Customer
from .card import Card



class Account(base):
    __tablename__ = 'account'

    idaccount = Column(Integer, primary_key=True)
    iban = Column(String)
    type = Column(String)
    balance = Column(Numeric)
    cardnumber = Column(Integer, ForeignKey('card.cardnumber'))
    customerid = Column(Integer, ForeignKey('customer.id'))
    customer = relationship(Customer)
    card = relationship(Card)


    def get_account_by_card(cardnumber):
        Session = sessionmaker(db)
        session = Session()
        account = session.query(Account.idaccount,Account.balance).filter_by(cardnumber=cardnumber).first()
        return account
