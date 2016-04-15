package com.newhouse.wframe.core.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.newhouse.wframe",
	excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern={
			"com.newhouse.wframe.core.config.MvcConfig",
			"com.newhouse.wframe.core.config.MvcConfig",
			"com.newhouse.wframe.core.config.DatabaseConfig"})
)

public class MvcConfig extends WebMvcConfigurerAdapter {


	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/loginView").setViewName("login");
		//registry.addViewController("/logout").setViewName("login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
		super.configureDefaultServletHandling(configurer);
	}
	
	

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		//registry.enableContentNegotiation(new MappingJackson2JsonView());
		super.configureViewResolvers(registry);
	}

	@Bean
	public ServletContextTemplateResolver templateResolver(){
		ServletContextTemplateResolver ctr = new ServletContextTemplateResolver();
		ctr.setPrefix("/WEB-INF/template/");
		ctr.setSuffix(".html");
		ctr.setCacheable(false);
		ctr.setCharacterEncoding("UTF-8");
		//ctr.setTemplateMode("HTML5");
		return ctr;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(){
		SpringTemplateEngine ste = new SpringTemplateEngine();
		ste.setTemplateResolver(templateResolver());
		return ste;
	}
	
	@Bean
	public ContentNegotiatingViewResolver contentNegotiatingViewResolver(){
		ContentNegotiatingViewResolver cvr = new ContentNegotiatingViewResolver();
		ViewResolver[] vrs =  {
			new BeanNameViewResolver(){{
				setOrder(1);
			}},			
			new ThymeleafViewResolver(){{
				setTemplateEngine(templateEngine());
				setCharacterEncoding("UTF-8");
				setOrder(2);
			}},
			new InternalResourceViewResolver(){{
				setPrefix("/WEB-INF/views");
				setSuffix(".jsp");
				setOrder(3);
			}}
		};
		View[] vs = {
			new	MappingJackson2JsonView()
		};
		cvr.setViewResolvers(Arrays.asList(vrs));
		cvr.setDefaultViews(Arrays.asList(vs));
		return cvr;
	}
}
