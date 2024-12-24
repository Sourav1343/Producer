Apache kafka : communication system between two services by publising and subscribing topics

Sender(Producer) -> -> -> Publish -> -> ->Apache Kafka -> -> Subscribe 1 -> -> -> -> Recevier(Consumer 1)
															 Subscribe 2 -> -> -> -> Recevier(Consumer 2)
															 Subscribe 3 -> -> -> -> Recevier(Consumer 3)
															 Subscribe 4 -> -> -> -> Recevier(Consumer 4)
															 
															 
															 
Example : 

1. OLA Driver Location Update
2. ZOmato live Food tracking
3. Notification Systems

Throughput of a database : No of read and writes operation : DB has low throughput 

Kafka has very good throughput

WHy
1. High Throughput
2. Fault Tolerance (Replication)
3. Durable
4. Scalable

Kafka Architecture

Component
1. Producer : Write the messages on the kafka Server

2. Kafka ecoystem : -> (Kafka Cluster(Brokers 1(Topics(Partition 1 (offset)+
													   Partition 2 + ..n)) + 
													   Broker 2(Topics(Partition 1 
													   + Partition 2 + ..n) ...n) 
													   + Zookeeper(Manage state))
													   
Installtion of Kafka

1. Download Zip file from kafka offical website
2. Extract
3. Start Zookeeper   cmd : C:\Kafka> .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
4. Start Kafka Server cmd C:\Kafka>bin\windows\kafka-server-start.bat config\server.properties

Use Kafka with Console
1. Create new topics with kafka-topics


C:\Kafka>bin\windows\kafka-topics.bat --create --topic user-topic --bootstrap-server localhos
t:9092
Created topic user-topic.

List the Topic : C:\Kafka>bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092

2. Producer examples messages with kafka-console-producer

C:\Kafka>bin\windows\kafka-console-producer.bat --topic user-topic --bootstrap-server localho
st:9092

3. Consume the messages with kafka-console consumer



C:\Kafka>bin\windows\kafka-console-consumer.bat --topic user-topic --from-beginning --bootstrap-server localhost:9092

		JAVA CODE

1. Create a Producer Package with spring web and kafka dependencies.

2. Now Open Producer package in the IDE.

3. Create a kafkaConfig.java file 	

// New Info about the NewTopic class in Kafka admin library 
This Java class `NewTopic` is part of Apache Kafka's admin client library. It represents a new topic to be created in Kafka and encapsulates its properties and configuration. Below is a summary of its key features:

### Key Features:
1. **Properties of a Kafka Topic:**
   - **`name`:** The name of the topic to be created.
   - **`numPartitions`:** The number of partitions for the topic (optional).
   - **`replicationFactor`:** The replication factor for the topic (optional).
   - **`replicasAssignments`:** A mapping of partition IDs to their corresponding broker IDs for manual replica assignments (optional).
   - **`configs`:** A configuration map for the topic.

2. **Constructors:**
   - Create a topic with specific `numPartitions` and `replicationFactor`.
   - Create a topic with default broker configurations for partitions and replication factors.
   - Create a topic with custom replica assignments.

3. **Methods:**
   - Accessors (`name()`, `numPartitions()`, `replicationFactor()`, `replicasAssignments()`, `configs()`) to retrieve topic properties.
   - `configs(Map<String, String>)`: Allows setting topic-specific configurations.
   - `convertToCreatableTopic()`: Converts the `NewTopic` instance into a `CreatableTopic` object, which is used for Kafka's topic creation requests.
   - Overridden methods (`toString()`, `equals()`, `hashCode()`) for object representation, comparison, and hashing.

### Use Case:
This class is typically used in conjunction with the `AdminClient` to programmatically create topics in Kafka. It supports both default and custom configurations, including manual partition and replica assignments.

### Example Workflow:
1. Create an instance of `NewTopic` with the desired configurations.
2. Pass it to the `Admin#createTopics` method to initiate the topic creation process in Kafka.?///
////


Application.properties
# Kafka Producer Configuration
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

Output 

2024-12-24T17:45:19.213+05:30  INFO 18084 --- [Producer] [nio-8080-exec-2] o.a.k.c.t.i.KafkaMetricsCollector        : initializing Kafka metrics collector
2024-12-24T17:45:19.221+05:30  INFO 18084 --- [Producer] [nio-8080-exec-2] o.a.k.clients.producer.KafkaProducer     : [Producer clientId=Producer-producer-1] Instantiated an idempotent producer.
2024-12-24T17:45:19.237+05:30  INFO 18084 --- [Producer] [nio-8080-exec-2] o.a.kafka.common.utils.AppInfoParser     : Kafka version: 3.8.1
2024-12-24T17:45:19.237+05:30  INFO 18084 --- [Producer] [nio-8080-exec-2] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId: 70d6ff42debf7e17
2024-12-24T17:45:19.238+05:30  INFO 18084 --- [Producer] [nio-8080-exec-2] o.a.kafka.common.utils.AppInfoParser     : Kafka startTimeMs: 1735042519237
2024-12-24T17:45:19.245+05:30  INFO 18084 --- [Producer] [ucer-producer-1] org.apache.kafka.clients.Metadata        : [Producer clientId=Producer-producer-1] Cluster ID: 9ab06GcdQ56W2tEJ6RVmuQ
2024-12-24T17:45:19.246+05:30  INFO 18084 --- [Producer] [ucer-producer-1] o.a.k.c.p.internals.TransactionManager   : [Producer clientId=Producer-producer-1] ProducerId set to 2 with epoch 0
2024-12-24T17:45:19.256+05:30  INFO 18084 --- [Producer] [nio-8080-exec-2] com.producer.service.KafkaService        : Message Produced on Kafka Server ...Great


4. Open Consumer Package 

add this to appication.properties
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=my-consumer-group
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer 	


////////////////////////////////////////////////////////////////////////////////

Some userful information about the kafkaListner

This Java code defines the `@KafkaListener` annotation, which is part of the Spring Kafka framework. Here's a simplified breakdown:

1. **Purpose**:  
   The `@KafkaListener` annotation is used to mark methods or classes that should process messages from specific Kafka topics. It provides configurations for setting up Kafka message listeners.

2. **Key Features**:  
   - **Topics**: Specifies the Kafka topics to listen to using `topics()` or dynamic topic patterns using `topicPattern()`.  
   - **Consumer Configuration**: Allows overriding Kafka consumer properties like `groupId`, `clientIdPrefix`, and `concurrency`.  
   - **Error Handling**: Supports custom error handlers using the `errorHandler()` property.  
   - **Method Parameters**: Allows flexible method parameters, such as accessing message payload, headers, acknowledgment objects, etc.  
   - **Listener Container**: Configures or overrides the Kafka listener container factory through `containerFactory()`.  

3. **Usage Levels**:
   - **Method-Level**: Creates a separate listener container for each method annotated with `@KafkaListener`.
   - **Class-Level**: Handles all methods annotated with `@KafkaHandler` within a single container.

4. **Advanced Features**:  
   - Supports SpEL (Spring Expression Language) for dynamic configurations.  
   - Enables manual or automatic acknowledgment of Kafka messages.  
   - Offers `id` and `groupId` configurations for listener identification and consumer group assignments.

5. **Integration**:  
   - Typically used alongside `@EnableKafka` to activate Kafka listener processing.  
   - Relies on the `KafkaListenerAnnotationBeanPostProcessor` to handle the annotation.

In simple terms, `@KafkaListener` is a powerful tool in Spring for integrating Kafka consumers with your application, providing flexible and declarative configuration options.

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



