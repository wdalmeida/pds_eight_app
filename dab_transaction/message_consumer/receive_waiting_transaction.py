from kafka import KafkaConsumer
import json


print("Start consumer")
consumer = KafkaConsumer('test',
                             group_id='test',
                             bootstrap_servers=['10.10.1.110:9092','10.10.1.111:9092'])
print("Consumer")
for message in consumer:
    print("message")
    print("%s:%d:%d: key=%s value=%s" % (message.topic, message.partition,
                                         message.offset, message.key,
                                         message.value))
    print("end of for")