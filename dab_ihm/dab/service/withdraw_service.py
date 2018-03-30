class withdraw_service():

    @staticmethod
    def amount_withdraw(data):
        amount=None
        if data.get('submit'):
            amount=data.get('submit')
        return amount



