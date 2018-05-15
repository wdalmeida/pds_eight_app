from rest import *


class transaction_service():

    @staticmethod
    def readToBoolean(read):
        if read == '0':
            read = False
        elif read == '1':
            read = True
        return read

    @staticmethod
    def create_transaction(data):
        trans = transaction.Transaction(data)
        ttrans.read = readToBoolean(read)
        if sendWaiting(trans):
            logging.debug(data["transaction_id"])
            readWaiting(data["transaction_id"])
            sendApproved(data["transaction_id"])
        return True


