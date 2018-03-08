package com.quantongfu.timesextractor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

/**
 * @Author：telin
 * @Date：Created in 2018/3/8 17:35
 * @Describe：
 */
public class WordTimestampExtractor implements TimestampExtractor
{
	@Override public long extract(ConsumerRecord<Object, Object> record)
	{
		return System.currentTimeMillis();
	}
}
