package com.quantongfu.kafkastream;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

import java.util.Arrays;
import java.util.Properties;

/**
 * @Author：telin
 * @Date：Created in 2018/3/8 11:03
 * @Describe：
 */
public class KafkaStreamTest implements Runnable
{
	public static Properties config;

	static
	{
		config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-test");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.88.201:9092,192.168.88.202:9092,192.168.88.203:9092");
		//添加到Windows维护管理器以确保数据不会从日志中过早删除。默认为1天
		config.put(
				StreamsConfig.WINDOW_STORE_CHANGE_LOG_ADDITIONAL_RETENTION_MS_CONFIG,
				3000);
		config.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG,
				Serdes.String().getClass());
		config.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG,
				Serdes.String().getClass());
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	}

	@Override public void run()
	{
		KStreamBuilder kStreamBuilder = new KStreamBuilder();
		KStream<String, String> textLines = kStreamBuilder
				.stream(Serdes.String(), Serdes.String(), "test2");

		textLines.flatMapValues(
				value -> Arrays.asList(value.toLowerCase().split(" ")))
				.map((key, value) -> new KeyValue<>(value, value)).groupByKey()
				.count("us").print();
		System.out.println("-----------2-----------");
		KafkaStreams ks = new KafkaStreams(kStreamBuilder, config);
		ks.start();
	}
}
