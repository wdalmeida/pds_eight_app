import socket
import threading
import time


class read_nfc_tag(threading.Thread):

    def __init__(self,host,port):
        threading.Thread.__init__(self)
        self.host = host
        self.port = port

    def detect_tag(self):
        print("%s --- %s", (self.host,self.port))
        while True:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.connect((self.host, self.port))
            print ("Connection on %s",(self.port))
            sock.send(b"TEST TEST")
            time.sleep(10)
            print ("Close")
            sock.close()

    def run(self):
        self.detect_tag()




newthread = read_nfc_tag( '127.0.0.1', 5001)
newthread.start()

