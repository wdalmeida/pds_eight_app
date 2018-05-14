from rest import *
from message_producer import send_waiting_transaction as kafka


class transaction_service():

    @staticmethod
    def create_transaction(data,db):
        t= transaction.Transaction(data)
        if t.red == '0':
            t.red = False
        elif t.red == '1':
            t.red = True
        t.add_transaction(data)
        kafka.send(t)
        return "ok"



