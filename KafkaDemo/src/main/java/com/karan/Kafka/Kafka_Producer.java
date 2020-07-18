package com.karan.Kafka;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Kafka_Producer {
	public static void main(String args[])
	{
            final Logger log=LoggerFactory.getLogger(Kafka_Producer.class);
			Properties properties=new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
			
			KafkaProducer<String,String> producer=new KafkaProducer<String, String>(properties);
			
			for(int i=0;i<10;i++)
			{
			ProducerRecord<String,String> record=new ProducerRecord("JAVAP","key"+i, "ABC"+i);
			
			 
			 producer.send(record,new Callback() {
				
				public void onCompletion(RecordMetadata metadata, Exception exception) {
				log.info(metadata.topic());
				log.info("Partition "+metadata.partition());
				;
				
				}
			});
			}
             producer.flush();
            producer.close();

			
			
	}

}
