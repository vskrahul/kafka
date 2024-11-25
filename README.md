# Kafka Producer and Consumer

## Start zookeeper

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

zookeeper.properties

```
clientPort=2181
dataDir=/tmp/zookeeper
admin.enableServer=true
admin.serverPort=8081

http://localhost:8081/commands
```

## start Broker (kafka server)

```
bin/kafka-server-start.sh config/server.properties
```

server.properties 

```
//Number of threads server uses for receiving/sending request to the network 
num.network.threads=3

//number of threads server uses for processing requests, which may include disk I/O
num.io.threads=8

//Topic replication factor
offsets.topic.replication.factor=3

//number of partitions
num.partitions=2

//zookeeper
zookeeper.connect=localhost:2181
```

### Producer Properties

create producer

```
bin/kafka-console-producer.sh 
	--bootstrap-server localhost:9091 
	--topic topic-1 
	--property parse.key=true 
	--property key.separator=, 
	--producer-property acks=all
```

```
//kafka server port
bootstrap.servers=localhost:9092

//how long send(..) will block for
max.block.ms=x //milliseconds

//max request size
max.request.size=1mb //max

//partitioning logic which spreads the load evenly 
partitioner.class=sticky //default

//size in bytes when batching multiple records sent to a partition
batch.size=xx

//size in butes, buffer records waiting to be sent to the server
buffer.memory=xx
```

## start consumer

```
bin/kafka-console-consumer.sh
  --bootstrap-server localhost:9092
  --topic topic-1
  --group consumer-group-b 
  --partition 0
  --from-beginning 
  --consumer-property xx:yy
  --consumer.config config_file
```



## Create topic

```
kafka-topics.sh 
	--bootstrap-server localhost:9091 
	--topic topic-1 
	--create 
	--partitions 3 
	--replication-factor 3
	--property partitioner.class=org.apache.kafka.clients.producer.RoundRobinPartitioner

```

## Troubleshoot

```
bin/kafka-consumer-groups.sh --bootstrap-server localhost:9091 --group group-1 --describe

bin/kafka-topics.sh --bootstrap-server localhost:9091 --list
```