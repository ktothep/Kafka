package com.karan.Kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kafka_Consumer_SeekAssign {
public static void main(String args[])
{
	final Logger log=LoggerFactory.getLogger(Kafka_Consumer.class);
	Properties properties=new Properties();
	properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
	properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
	properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
	properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"Eleventh");
	properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
	
	KafkaConsumer<String,String> consumerseek=new KafkaConsumer(properties);
	
	 TopicPartition partitiontoRead=new TopicPartition("JAVAP", 0);
	 
	 consumerseek.assign(Arrays.asList(partitiontoRead));
	 
	 consumerseek.seek(partitiontoRead,0);
	
	
	
	
	
	while(true)
	{
		ConsumerRecords<String,String> records= consumerseek.poll(Duration.ofMillis(100));
		for(ConsumerRecord record:records)
		{
			log.info("Value "+record.value());
			log.info("Partition"+record.partition());
		}
	}
	
	
}


}
