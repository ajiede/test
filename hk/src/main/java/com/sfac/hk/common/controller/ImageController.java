package com.sfac.hk.common.controller;

import com.sfac.hk.common.service.ImageService;
import com.sfac.hk.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description ImageController
 * @Author JiangHu
 * @Date 2023/2/15 14:22
 */
@RestController
@RequestMapping(value = "/api/common")
public class ImageController {

	@Autowired
	private ImageService imageService;

	/**
	 * 127.0.0.1/api/common//image/profile ---- post
	 */
	@PostMapping(value = "/image/{imageTypeName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public Result<String> uploadImage(
			@RequestParam MultipartFile mf,
			@PathVariable String imageTypeName) {
		return imageService.uploadImage(mf, imageTypeName);
	}
}
