from sqlalchemy import Column, String
from sqlalchemy.types import Integer
from sqlalchemy.orm import sessionmaker
from rest import *
from rest import base
from rest import db


class Customer(base):
    __tablename__ = 'customer'

    id = Column(Integer, primary_key=True)
    name = Column(String)

    def get_customers(self):
        Session = sessionmaker(db)
        session = Session()
        customers = session.query(Customer)
        for customer in customers:
            print(customer.id)
            print(customer.name)
            print("\n")

    def add_customers(self):
        Session = sessionmaker(db)
        session = Session()
        cust = Customer(id=3, name='Invalid')
        session.add(cust)
        session.commit()

