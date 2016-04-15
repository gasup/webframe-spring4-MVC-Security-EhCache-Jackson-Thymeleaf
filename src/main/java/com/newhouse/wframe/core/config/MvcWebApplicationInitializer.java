package com.newhouse.wframe.core.config;


import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{DatabaseConfig.class, WebSecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[]{MvcConfig.class};
	}

	@Override
	protected String[] getServletMappings() {

		return new String[]{"/"};
	}
	

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{
				//new DelegatingFilterProxy("springSecurityFilterChain"),
				new OpenEntityManagerInViewFilter()
		};  
	}
	
	
}
