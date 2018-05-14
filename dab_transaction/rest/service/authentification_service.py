from rest import *


class authentification():

    @staticmethod
    def authentification_card(id):
        logging.debug(type(id))
        if isinstance(id, str) and len(id) is not 16:
            acc = account.Account.get_account_by_card(id)
            if acc is not None:
                logging.debug(acc.iban)
                logging.debug(acc.balance)
        else:
            loggin.debug("Card is not a String or unequal to 27 characters")
            acc = None
        return acc



