import socket
import threading
import time


class read_nfc_tag(threading.Thread):

    def __init__(self,host,port):
        threading.Thread.__init__(self)
        self.host = host
        self.port = port
        self.sock = None

    def connect_to_ihm(self):
        print("%s --- %s", (self.host,self.port))
        result=False
        try:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.sock.connect((self.host, self.port))
            result=True
        except socket.error:
            print ("Couldnt connect with the socket-server")
        return result

    def disconnect_to_ihm(self):
        print("%s disconnecting %s", (self.host, self.port))
        try:
            self.sock.close()
            print ("Close")
        except socket.error:
            print ("No socket")

    def detect_tag(self):
        print("detect")
        while True:
            connect= self.connect_to_ihm()
            if not connect:
                time.sleep(5)
            else:
                self.sock.send(b"TEST TEST")
                self.disconnect_to_ihm()
                time.sleep(5)

    def run(self):
        self.detect_tag()



if __name__ == "__main__":
    newthread = read_nfc_tag( '127.0.0.1', 5001)
    newthread.start()

