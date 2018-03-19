from sqlalchemy import Column, String
from sqlalchemy.types import Integer
from sqlalchemy.orm import sessionmaker
from .. import *


class Customer(base):
    __tablename__ = 'customer'

    id = Column(Integer, primary_key=True)
    name = Column(String)

    def get_customers(self,db):
        Session = sessionmaker(db)
        session = Session()
        customers = session.query(Customer)
        for customer in customers:
            print(customer.id)
            print(customer.name)
            print("\n")

