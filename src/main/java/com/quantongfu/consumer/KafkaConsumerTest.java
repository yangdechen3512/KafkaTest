package com.quantongfu.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @Author：telin
 * @Date：Created in 2018/2/28 11:41
 * @Describe：
 */
public class KafkaConsumerTest
{
	public static void main(String[] args)
	{
		Properties props = new Properties();

		props.put("bootstrap.servers",
				"192.168.88.201:9092,192.168.88.202:9092,192.168.88.203:9092");
		//每个消费者分配独立的组号
		props.put("group.id", "test4321");

		//如果value合法，则自动提交偏移量
		props.put("enable.auto.commit", "true");

		//设置多久一次更新被消费消息的偏移量
		props.put("auto.commit.interval.ms", "1000");

		//设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
		props.put("session.timeout.ms", "30000");

		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.LongDeserializer");

		KafkaConsumer<String, Long> consumer = new KafkaConsumer<>(props);

		consumer.subscribe(Collections.singletonList("test"));

		System.out.println("Subscribed to topic " + "test");
		int i = 0;

		while (true)
		{
			ConsumerRecords<String, Long> records = consumer.poll(100);
			for (ConsumerRecord<String, Long> record : records){
				System.out.println(record.key() + "---" + record.value());
			}
		}
	}
}
