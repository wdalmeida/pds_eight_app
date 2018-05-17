from sqlalchemy import Column, String, Date
from sqlalchemy.types import Integer
from sqlalchemy.orm import sessionmaker
from rest import *
from rest import base
from rest import db


class Card(base):
    __tablename__ = 'card'

    cardnumber = Column(String, primary_key=True)
    pin = Column(String)
    crypto = Column(String)
    date = Column(Date)


    def get_one_card(self,id):
        Session = sessionmaker(db)
        session = Session()
        card = session.query(Card.cardnumber).filter_by(cardnumber=id)
        logging.debug(card.cardnumber)
        logging.debug("\n")
