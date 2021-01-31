/**
  * @filename CustomJsonComponent.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.configure;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @type CustomJsonComponent
 * @description 
 * @author qianye.zheng
 */
//@JsonComponent
public class CustomJsonComponent
{
	
	/**
	 * 
	 * @type Serializer
	 * @description 
	 * @author qianye.zheng
	 */
	public static final class Serializer extends JsonSerializer<Object>
	{

		/**
		 * @description 
		 * @param value
		 * @param gen
		 * @param serializers
		 * @throws IOException
		 * @author qianye.zheng
		 */
		@Override
		public void serialize(Object value, JsonGenerator gen,
				SerializerProvider serializers) throws IOException
		{
		}
	}
	
	/**
	 * 
	 * @type Deserializer
	 * @description 
	 * @author qianye.zheng
	 */
	public static final class Deserializer extends JsonDeserializer<String>
	{

		/**
		 * @description 
		 * @param p
		 * @param ctxt
		 * @return
		 * @throws IOException
		 * @throws JsonProcessingException
		 * @author qianye.zheng
		 */
		@Override
		public String deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException
		{
			return null;
		}
		
	}
	
}
