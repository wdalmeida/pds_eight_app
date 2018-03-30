import socket
import threading
from dab.controller import websocket


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

