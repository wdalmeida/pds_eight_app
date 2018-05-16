class withdraw_service():

    @staticmethod
    def amount_withdraw(data):
        amount=None
        if data.get('submit'):
            amount=data.get('submit')
        return amount

    @staticmethod
    def get_iban(data):
        iban=None
        if data.get('iban'):
            iban = data.get('iban')
        return iban

