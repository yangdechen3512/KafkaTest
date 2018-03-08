package com.quantongfu.kafkastream;

import com.quantongfu.processor.WordCountProcess;
import com.quantongfu.timesextractor.WordTimestampExtractor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

/**
 * @Author：telin
 * @Date：Created in 2018/3/8 17:54
 * @Describe：
 */
public class KafkaStreamTopology implements Runnable
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
				4000);
		config.put(StreamsConfig.KEY_SERDE_CLASS_CONFIG,
				Serdes.String().getClass());
		config.put(StreamsConfig.VALUE_SERDE_CLASS_CONFIG,
				Serdes.String().getClass());
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(StreamsConfig.TIMESTAMP_EXTRACTOR_CLASS_CONFIG,
				WordTimestampExtractor.class);
	}

	@Override public void run()
	{
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		topologyBuilder.addSource("SOURCE", new StringDeserializer(),
				new StringDeserializer(), "test2")
				.addProcessor("ProcessorTest", WordCountProcess::new, "SOURCE")
				.addStateStore(
						Stores.create("precesstor-test-store").withStringKeys()
								.withLongValues().inMemory().build(),
						"ProcessorTest")
				.addSink("SinkTest", "test", new StringSerializer(),
						new LongSerializer(), "ProcessorTest");
		KafkaStreams kafkaStreams = new KafkaStreams(topologyBuilder, config);
		try
		{
			kafkaStreams.start();
		}
		catch (Exception e)
		{
			kafkaStreams.close();
			kafkaStreams.cleanUp();
		}
	}

	public static void main(String[] args)
	{
		new Thread(new KafkaStreamTopology()).start();
	}
}
