package com.example.Interceptors;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MySrpingMVCConfig extends WebMvcConfigurerAdapter{

	
	
	 @Bean
	  public AuthorizationInterceptor authorizationInterceptor() {
	    return new AuthorizationInterceptor();
	  }
    // 自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	System.out.println("哈哈Interceptor");
        registry.addInterceptor(authorizationInterceptor())
        .addPathPatterns("/*")
        .excludePathPatterns("/login/*");
        super.addInterceptors(registry);
    }
}
