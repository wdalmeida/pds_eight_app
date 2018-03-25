import socket
import threading
import time
from flask import Flask,render_template
from .. import websocket

class home_service(threading.Thread):

    def __init__(self,host,port,app):
        threading.Thread.__init__(self)
        self.host = host
        self.port = port
        self.app=app


    def waiting_for_tag(self):
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.bind((self.host, self.port))

        while True:
            sock.listen(5)
            client, address = sock.accept()
            print("%s connected",(address))
            response = client.recv(255)
            if response != "":
                self.redirect()
                print (response)

        print ("Close")
        client.close()
        sock.close()

    def run(self):
        self.waiting_for_tag()

    def redirect(self):
        websocket.handle_nfc_tag_event()

