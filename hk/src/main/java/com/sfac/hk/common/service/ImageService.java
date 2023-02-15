package com.sfac.hk.common.service;

import com.sfac.hk.common.vo.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * ImageService
 */
public interface ImageService {

	Result<String> uploadImage(MultipartFile mf, String imageTypeName);
}
