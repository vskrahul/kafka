/**
 * 
 */
package com.github.vskrahul.kafka.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * @author Aditya Gupta
 *
 */
@Slf4j
public class JsonUtil {

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
	}

	public static <T> String toJsonString(T instance) {
		String json = null;
		try {
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			json = mapper.writeValueAsString(instance);
			log.debug("[method=toJson] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=toJson] [error={}]", e.getMessage());
		}
		return json;
	}
	
	public static <T> String toPrettyJsonString(T instance) {
		String json = null;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(instance);
			log.debug("[method=toJson] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=toJson] [error={}]", e.getMessage());
		}
		return json;
	}
	
	public static String toIntendedJson(String json) {
		String indentedJson = null;
		if(isEmpty(json))
			return null;
		try {
			indentedJson = mapper.writeValueAsString(mapper.readValue(json, Object.class));
			log.debug("[method=toJson] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=toJson] [error={}]", e.getMessage());
			indentedJson = json;
		}
		return indentedJson;
	}
	
	public static <T> T jsonStringToInstance(String json, Class<T> type) {
		T t = null;
		try {
			t = mapper.readValue(json, type);
			log.debug("[method=jsonStringToInstance] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=jsonStringToInstance] [error={}]", e.getMessage());
		}
		return t;
	}
	
	public static <T> T jsonStreamToInstance(InputStream json, Class<T> type) {
		T t = null;
		try {
			t = mapper.readValue(json, type);
			log.debug("[method=jsonStreamToInstance] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=jsonStreamToInstance] [error={}]", e.getMessage());
		}
		return t;
	}
	
	public static <T> T jsonFileToInstance(File json, Class<T> type) {
		T t = null;
		try {
			t = mapper.readValue(json, type);
			log.debug("[method=jsonFileToInstance] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=jsonFileToInstance] [error={}]", e.getMessage());
		}
		return t;
	}
	public static Map<String, Object> jsonStringToMap(String json) {
		Map<String, Object> t = null;
		try {
			t = mapper.readValue(json, Map.class);
			log.debug("[method=jsonStringToMap] [json={}]", json);
		} catch (Exception e) {
			log.error("[method=jsonStringToMap] [error={}]", e.getMessage());
		}
		return t;
	}

	private static boolean isEmpty(String value) {
		return value == null || value.isBlank();
	}
}
