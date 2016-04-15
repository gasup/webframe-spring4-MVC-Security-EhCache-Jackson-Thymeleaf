package com.newhouse.wframe.core.config;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;



@Configuration
//@EnableScheduling
//@EnableAspectJAutoProxy
@ComponentScan(basePackages="com.newhouse.wframe.core.service")
@EnableCaching
public class AppConfig extends CachingConfigurerSupport {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	
	@Override
	public CacheManager cacheManager() {
		
		net.sf.ehcache.CacheManager ehcacheCacheManager = null;
		EhCacheCacheManager cacheCacheManager = null;
		try {
			ehcacheCacheManager = new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
			cacheCacheManager = new EhCacheCacheManager(ehcacheCacheManager);
		}catch (IOException e) {
			logger.error("AppConfig error", e);
		}
		return cacheCacheManager;
        
	}

	  
	@Override
	public KeyGenerator keyGenerator() {
		 return new SimpleKeyGenerator();  
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return super.cacheResolver();
	}


	@Override
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {

			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				logger.error("handleCacheGetError", exception);
				
			}

			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				logger.error("handleCachePutError", exception);
				
			}

			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				logger.error("handleCacheEvictError", exception);
				
			}

			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				logger.error("handleCacheClearError", exception);
				
			}
			
		};

	}

}
