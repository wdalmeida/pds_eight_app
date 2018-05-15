from kafka import KafkaConsumer
import json
from rest import *
from dao.transaction import Transaction
def readWaiting(id):
    logging.debug("Start consumer")
    consumer = KafkaConsumer('transactionWaiting',
                                    group_id='waiting',
                                    bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'],
                                    auto_offset_reset='latest',
                                    enable_auto_commit=True,
                                    consumer_timeout_ms=2000) # 2 seconds
    for message in consumer:
        logging.debug("message")
        logging.debug("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                             message.offset, message.key,
                                             message.value.decode('utf-8')))
        json_data = message.value.decode('utf-8')
        logging.debug(json_data)
        trans=json.loads(json_data)
        logging.debug("end of for")
        if message.offset == id:
            t= Transaction(trans)
            t.add_transaction()
            return  {
            'status': 200,
            'message': 'Message sent: ' + json_data }
