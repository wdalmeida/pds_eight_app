import json
from kafka import KafkaProducer
from kafka.errors import KafkaError


def send(transaction):
    producer = KafkaProducer(bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'],
                             value_serializer=lambda m: json.dumps(m).encode('utf-8'))
    print(transaction)
    future = producer.send('test', transaction)
    #producer = KafkaProducer(bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'])
    #future = producer.send('test', b'raw_bytes')

    res = False
    try:
        record_metadata = future.get(timeout=10)
        print(record_metadata.topic)
        print(record_metadata.offset)
        res = True
    except KafkaError:
        print("error")
        pass
    return res
