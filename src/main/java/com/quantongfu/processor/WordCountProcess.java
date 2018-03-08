package com.quantongfu.processor;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

/**
 * @Author：telin
 * @Date：Created in 2018/3/8 16:42
 * @Describe：
 */
public class WordCountProcess implements Processor<String, String>
{
	private ProcessorContext context;
	private KeyValueStore<String, Long> kvStore;

	@Override public void init(ProcessorContext context)
	{
		this.context = context;
		this.context.schedule(1000);
		this.kvStore = (KeyValueStore<String, Long>) context
				.getStateStore("precesstor-test-store");
	}

	@Override public void process(String key, String value)
	{
		String[] words = value.toLowerCase().split(" ");

		for (String word : words)
		{
			if (this.kvStore.get(word) == null)
			{
				this.kvStore.put(word, 1L);
			}
			else
			{
				this.kvStore.put(word, this.kvStore.get(word) + 1);
			}

		}
	}

	@Override public void punctuate(long timestamp)
	{
		KeyValueIterator<String, Long> iterator = this.kvStore.all();
		iterator.forEachRemaining(value ->
		{
			if(value != null){
				System.out.println("(" + value.key + "," + value.value + ")");
				context.forward(value.key, value.value);
			}else{
				System.out.println("无数据");
			}
		});
		iterator.close();
		context.commit();
	}

	@Override public void close()
	{
		this.kvStore.close();
	}
}
