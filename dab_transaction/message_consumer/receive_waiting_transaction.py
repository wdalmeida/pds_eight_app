from kafka import KafkaConsumer
import json


def read(id):
    print("Start consumer")
    consumer = KafkaConsumer('test',
                                    group_id='test',
                                    bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'],
                                    auto_offset_reset='earliest',
                                    enable_auto_commit=True,
                                    consumer_timeout_ms=2000) # 2 seconds
    for message in consumer:
        print("message")
        print("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                             message.offset, message.key,
                                             message.value.decode('utf-8')))
        json_data = message.value.decode('utf-8')
        print(json_data)
        trans=json.loads(json_data)
        print("end of for")
        if trans['transaction_id'] == id:
            return  {
            'status': 200,
            'message': 'Message sent: ' + request.url,
    }