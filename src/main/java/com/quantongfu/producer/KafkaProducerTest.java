package com.quantongfu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author：telin
 * @Date：Created in 2018/2/28 11:40
 * @Describe：
 */
public class KafkaProducerTest
{
	public  final  static String topic = "test";

	public static void main(String[] args)
	{
		Properties pro = new Properties();

		pro.put("metadata.broker.list",
				"192.168.88.201:9092,192.168.88.202:9092,192.168.88.203:9092");
		pro.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.88.201:9092,192.168.88.202:9092,192.168.88.203:9092");
		//指定消息key序列化方式
		pro.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		//指定消息本身的序列化方式
		pro.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		System.out.println("开始生产消息...");

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(pro);
		int n = 0;
		while(true){
			for(int i = 1 + n * 10;i<=10 + n * 10;i++) {
				producer.send(new ProducerRecord<String, String>(topic, "key-"+i,"message-"+i));
			}
			n = n + 1;
			try{
				Thread.sleep(3000);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
}
