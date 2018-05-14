from rest import *

class authentification():

    @staticmethod
    def authentification_card(id):
        acc = account.Account.get_account_by_card(id)
        if acc is not None:
            logging.debug(acc.iban)
            logging.debug(acc.balance)
        return acc



