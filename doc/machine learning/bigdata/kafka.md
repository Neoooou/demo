Apache Kafka是分布式发布-订阅消息系统，快速、可扩展。

kafka依赖于**zookeeper**， 使用zookeeper进行集群管理，选举leader，leader负责数据的读写，followers则定期复制leader上的数据，
进行备份。一个典型的kafka集群中包含若干个producer，若干个broker（broker数量越多，集群吞吐率越高），若干个user group和zookeeper集群。

创建broker，会到zookeeper进行注册，实现在服务器的水平扩展，具体的通过注册watcher，获取partition的信息。

rebalance: 多个消费者订阅了一个Topic时，根据分区策略进行消费者订阅分区的重分配

## 相关概念

### 生产者

提供数据源生产的地方，对于同一个topic，生产者只能有一个，这样可以确保同一个topic数据来自同一个业务数据，支持多并发
创建producer时，向zookeeper进行注册watcher,获取partition信息，以便实现数据生产时的负载均衡

### 消费者

消费数据的客户端，对于用一个topic，可以有多个消费者，比如spark、storm等等

### Broker

消息中间处理节点，一个Kafka节点就是一个broker，多个Kafka节点可以组成一个Kafka集群，broker分布部署且相互独立，但是需要一个注册系统
能够将集群中的节点管理起来，每个broker启动时，都会到zookeeper上进行注册

### Topic

同一类消息的总称，Kafka集群能够同时负载多个topic分发

### Partition

topic物理上的分组， 一个topic可以分为多个Partition， 每个partition是一个有序的队列，同一个topic里面的数据是存放再不同的分区中，
一个topic可拥有多个partition， 但是一个partition必定只属于一个topic

### Replication

每个分区或者topic都是有副本的，副本的数量也是可以在创建topic的时候就指定好，保证数据的安全性，以及提供高并发读取效率

###  Segment

partition物理上由多个segment组成

### Offset

专为partition和user group，partition中的每个消息都有一个连续的序列号叫做offset，用于partition唯一标识一条消息

### user group

为了便于实现重复消费，如果consumerA 和 consumerB 同在一个UserGroup， 那么ConsumerA消费过的数据，ConsumerB将无法消费



## ACK

分布式kafka采用**多副本同步数据机制**来保证数据的不丢失。

- ACK设置为0，只要Producer把消息发送出去，不管有没有写入在PartitionLeader的磁盘上，都认为消息发送成功
- ACK设置为1，只要PartitionLeader接收到消息并且写入到磁盘，则认为发送成功。但是不确定其他follower有没有同步过去这条消息
- ACK设置为All，PartionLeader接收到消息且写入到磁盘，另外要求ISR（In-Sync-Replicas,也就是保持同步的副本，即与leader保持同步的folloers）列表里的followers都把消息同步过去嘞，才认为消息发送成功。另外若Partition只有leader，没有任何follower，若leader宕机嘞，则会导致数据丢失，所以ack=all则保证不了一个副本情况下的数据丢失，