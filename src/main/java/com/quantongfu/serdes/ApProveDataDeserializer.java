package com.quantongfu.serdes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quantongfu.entity.ApProbeData;
import com.quantongfu.util.JacksonUtil;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class ApProveDataDeserializer implements Deserializer<List<ApProbeData>> {
	private String encoding = "UTF8";

	public static final JacksonUtil jacksonUtil = JacksonUtil.buildNormalBinder();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		String propertyName = isKey ? "key.deserializer.encoding" : "value.deserializer.encoding";
		Object encodingValue = configs.get(propertyName);
		if (encodingValue == null)
			encodingValue = configs.get("deserializer.encoding");
		if (encodingValue != null && encodingValue instanceof String)
			encoding = (String) encodingValue;

	}

	
	@Override
	public List<ApProbeData> deserialize(String topic, byte[] data) {
		try {
			if (data == null)
				return null;
			else {
				String jsondata = new String(data, encoding);
				TypeReference<List<ApProbeData>> ref = new TypeReference<List<ApProbeData>>(){}; 
				List<ApProbeData> list = (List<ApProbeData>) jacksonUtil.toObject(jsondata, ref);
				return list;
			}

		} catch (UnsupportedEncodingException e) {
			throw new SerializationException(
					"Error when deserializing byte[] to string due to unsupported encoding " + encoding);
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
