import socket
import threading
import requests
import json
from dab.controller import websocket
from ..controller.controller import *
from .. import *

def auth(card):
    print("Authentification ")
    print(card.decode("utf-8"))
    card=card.decode("utf-8")
    card=card.replace('\n', '')
    json_data = json.dumps({ "card": card})
    logging.info(json_data)
    logging.info(app.config['TRANSACTION_SERVER'] + "auth/card/")
    r = requests.post(app.config['TRANSACTION_SERVER'] + "auth/card/", data=json_data, headers={'Content-type': 'application/json'})
    logging.info(r.status_code)
    if r.status_code == 200:
        logging.debug(r.json())
        info=r.json()
        logging.debug(info)
        with app.test_request_context():
            session['iban']=info.get('iban')
            session['balance']=info.get('balance')
        return True
    else:
        return False


class home_service(threading.Thread):

    def __init__(self,host,port):
        threading.Thread.__init__(self)
        self.host = host
        self.port = port
        self._stop_event = threading.Event()

    def stop(self):
        self._stop_event.set()

    def waiting_for_tag(self):
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        sock.bind((self.host, self.port))
        while True:
            sock.listen(5)
            client, address = sock.accept()
            print("%s connected",(address))
            response = client.recv(255)
            if response != "":
                data = auth(response)
                if data is True:
                    self.redirect()
                    client.close()
                    print (response)
                    sock.close()
                    print ("Close")
                    break
        self.stop()

    def run(self):
        print("running")
        self.waiting_for_tag()

    def redirect(self):
        print ("Should redirect")
        websocket.handle_nfc_tag_event()

