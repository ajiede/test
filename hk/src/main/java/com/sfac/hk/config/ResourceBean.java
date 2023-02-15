package com.sfac.hk.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description ResourceBean
 * @Author JiangHu
 * @Date 2023/2/15 10:53
 */
@Component
@PropertySource(value = {"classpath:application.properties"})
public class ResourceBean {

	@Value("${resource.path}")
	private String path;
	@Value("${resource.path.pattern}")
	private String pathPattern;
	@Value("${resource.local.path.window}")
	private String windowLocalPath;
	@Value("${resource.local.path.linux}")
	private String linuxLocalPath;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPathPattern() {
		return pathPattern;
	}

	public void setPathPattern(String pathPattern) {
		this.pathPattern = pathPattern;
	}

	public String getWindowLocalPath() {
		return windowLocalPath;
	}

	public void setWindowLocalPath(String windowLocalPath) {
		this.windowLocalPath = windowLocalPath;
	}

	public String getLinuxLocalPath() {
		return linuxLocalPath;
	}

	public void setLinuxLocalPath(String linuxLocalPath) {
		this.linuxLocalPath = linuxLocalPath;
	}
}
