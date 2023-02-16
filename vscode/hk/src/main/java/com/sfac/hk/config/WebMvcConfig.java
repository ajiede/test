package com.sfac.hk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description WebMvcConfig
 * @Author JiangHu
 * @Date 2023/2/15 10:16
 */
@Configuration
@AutoConfigureAfter(value = {WebMvcAutoConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ResourceBean resourceBean;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			registry.addResourceHandler(resourceBean.getPathPattern())
					.addResourceLocations(
							ResourceUtils.FILE_URL_PREFIX + resourceBean.getWindowLocalPath());
		} else {
			registry.addResourceHandler(resourceBean.getPathPattern())
					.addResourceLocations(
							ResourceUtils.FILE_URL_PREFIX + resourceBean.getLinuxLocalPath());
		}
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
				.maxAge(3600)
				.allowedOriginPatterns("*");
	}
}
