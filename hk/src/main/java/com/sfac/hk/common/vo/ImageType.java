package com.sfac.hk.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.SQLOutput;

/**
 * ImageType
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ImageType {
	PROFILE("profile", 300, 300, 1024),
	TEST("test", 300, 300, 1024)
	;
	public String name;
	public int width;
	public int heigh;
	public int size;

	ImageType(String name, int width, int heigh, int size) {
		this.name = name;
		this.width = width;
		this.heigh = heigh;
		this.size = size;
	}

	public static ImageType getImageTypeByName(String name) {
		for (ImageType item : ImageType.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return null;
	}
}
