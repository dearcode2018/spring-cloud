/**
  * @filename DefaultPropertySourceLoader.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @type DefaultPropertySourceLoader
 * @description 自定义属性源加载器
 * Copy From Spring SourceCode，若版本更新影响到使用，需重新修改
 * @author qianye.zheng
 */
public class DefaultPropertySourceLoader implements PropertySourceLoader
{

	private static final String XML_FILE_EXTENSION = ".xml";

	@Override
	public String[] getFileExtensions() {
		return new String[] { "properties", "xml" };
	}

	/**
	 * 
	 * @description 
	 * @param name
	 * @param resource
	 * @return
	 * @throws IOException
	 * @author qianye.zheng
	 */
	@Override
	public List<PropertySource<?>> load(String name, Resource resource)
			throws IOException {
		Map<String, ?> properties = loadProperties(resource);
		if (properties.isEmpty()) {
			return Collections.emptyList();
		}
		return Collections
				.singletonList(new OriginTrackedMapPropertySource(name, properties));
	}
	
	/**
	 * 
	 * @description 
	 * @param resource
	 * @return
	 * @throws IOException
	 * @author qianye.zheng
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, ?> loadProperties(Resource resource) throws IOException {
		String filename = resource.getFilename();
		if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
			Properties props = new Properties();						
			InputStream is = resource.getInputStream();
			// TODO 解决中文乱码问题
			InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
			try {
				if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
					props.loadFromXML(is);
				}
				else {
					props.load(reader);
				}
			}
			finally {
				reader.close();
				is.close();
			}			
			
			return (Map) props;
		}
		return new OriginTrackedPropertiesLoader(resource).load();
	}

}
