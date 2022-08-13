package iob.util;

import java.util.Map;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneralFieldConverter implements AttributeConverter<Map<String, Object>, String> {

	private ObjectMapper objectMapper;
	public GeneralFieldConverter()
	{
		objectMapper = new ObjectMapper();
	}
	@Override
	public String convertToDatabaseColumn(Map<String, Object> arg0) {
		try
		{
			return objectMapper.writeValueAsString(arg0);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> convertToEntityAttribute(String arg0) {
		try
		{
			return objectMapper.readValue(arg0, Map.class);
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}
