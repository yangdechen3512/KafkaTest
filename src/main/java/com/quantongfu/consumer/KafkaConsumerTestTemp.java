package com.quantongfu.consumer;

import com.quantongfu.entity.ApProbeData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.quantongfu.util.DateUtil.yyyyMMddHHmmssFormat2;

/**
 * @Author：telin
 * @Date：Created in 2018/2/28 11:41
 * @Describe：
 */
public class KafkaConsumerTestTemp
{
//	public static void main(String[] args)
//	{
//		kakfkaConsumer(
//				"192.168.88.201:9092,192.168.88.202:9092,192.168.88.203:9092",
//				"test");
//	}

	public static void kakfkaConsumer(String bootstrap, String topic)
	{
		Properties props = new Properties();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
		//每个消费者分配独立的组号
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "test12345");

		//如果value合法，则自动提交偏移量
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");

		//设置多久一次更新被消费消息的偏移量
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

		//设置会话响应的时间，超过这个时间kafka可以选择放弃消费或者消费下一条消息
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"com.quantongfu.serdes.ApProveDataDeserializer");

		KafkaConsumer<String, List<ApProbeData>> consumer = new KafkaConsumer<>(
				props);

		consumer.subscribe(Collections.singletonList(topic));

		System.out.println("Subscribed to topic " + topic);

		while (true)
		{
			ConsumerRecords<String, List<ApProbeData>> records = consumer
					.poll(100);
			for (ConsumerRecord<String, List<ApProbeData>> record : records)
			{
				//				System.out.println(record.key() + "---" + record.value());
				List<ApProbeData> apProbeDataList = record.value();
				apProbeDataList.forEach(val ->
				{
					System.out.println(
							val.getApmac() + "|" + val.getUsermac() + "|"
									+ yyyyMMddHHmmssFormat2(
									val.getReportedTime() * 1000));
				});
			}
		}
	}
}
