import json
from kafka import KafkaProducer
from kafka.errors import KafkaError
from ..rest import *


def sendWaiting(transaction):
    producer = KafkaProducer(bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'],
                             value_serializer=lambda m: json.dumps(m).encode('utf-8'))
    logging.debug(transaction)
    future = producer.send('transactionWaiting', transaction)
    res = False
    try:
        record_metadata = future.get(timeout=10)
        logging.debug(record_metadata.topic)
        logging.debug(record_metadata.offset)
        res = True
    except KafkaError:
        logging.debug("error")
        pass
    return res


def sendApproved(transaction):
    producer = KafkaProducer(bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'],
                             value_serializer=lambda m: json.dumps(m).encode('utf-8'))
    logging.debug(transaction)
    future = producer.send('transactionApproved', transaction)
    res = False
    try:
        record_metadata = future.get(timeout=10)
        logging.debug(record_metadata.topic)
        logging.debug(record_metadata.offset)
        res = True
    except KafkaError:
        logging.debug("error")
        pass
    return res
