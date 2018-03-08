package com.quantongfu;

import com.quantongfu.kafkastream.KafkaStreamTest;

/**
 * @Author：telin
 * @Date：Created in 2018/3/6 10:12
 * @Describe：
 */
public class ApplicationStartup
{
	public static void main(String[] args) throws InterruptedException
	{
		new Thread(new KafkaStreamTest()).start();
//		KafkaConsumerTestTemp.kakfkaConsumer(
//				"10.0.4.23:9092,10.0.4.23:9093",
//				"greencity_ap_source");
	}
}
