package com.websystique.springmvc.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
//as in Configuration
@EnableWebMvc
//@EnableWebMvc is equivalent to mvc:annotation-driven in XML.
@ComponentScan(basePackages = "com.websystique.springmvc")
//ComponentScan referes to package locations to find the associated beans.
public class AppConfig {
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
	/* In case of validation failure, default error messages are shown.
        To override those default by your own custom [internationalized] messages
        from an external Message bundle [.properties file], we need to configure 
        a ResourceBundleMessageSource. */
	@Bean
	public MessageSource messageSource() {
            /*Method messageSource is there for same purpose. Notice the parameter
            provided (messages) to basename method. Spring will search for a file 
            named messages.properties in application class path.*/
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}
}

