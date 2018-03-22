package com.example;


import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.pagehelper.PageHelper;

@SpringBootApplication
@MapperScan("com.example.mapper")//mybaits要扫描的包
@Configuration
@ImportResource({"classpath:threadPool-config.xml"/*,"classpath:applicationContext-redis.xml"*/})
//@EnableScheduling
public class App /* extends SpringBootServletInitializer*/ {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	/*@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}*/
    /**
     * 
     * <pre>
     * 描述：支持fastjson
     * 作者：dongzhuo
     * 时间：2017年5月1日17:55:07
     * 任务号：
     * </pre>
     */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
	//编码设置
	/*@Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
     }*/
	@Bean
    public PageHelper pageHelper(){
	  System.out.println("MyBatisConfiguration.pageHelper()");  
      PageHelper pageHelper = new PageHelper();
      Properties properties = new Properties();
      properties.setProperty("offsetAsPageNum","true");
      properties.setProperty("rowBoundsWithCount","true");
      properties.setProperty("reasonable","true");
      properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
      pageHelper.setProperties(properties);
      return pageHelper;
   }

}
