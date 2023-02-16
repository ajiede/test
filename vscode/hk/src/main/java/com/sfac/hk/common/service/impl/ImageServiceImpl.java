package com.sfac.hk.common.service.impl;

import com.sfac.hk.common.service.ImageService;
import com.sfac.hk.common.vo.ImageType;
import com.sfac.hk.common.vo.Result;
import com.sfac.hk.config.ResourceBean;
import com.sfac.hk.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Description ImageService
 * @Author JiangHu
 * @Date 2023/2/15 14:24
 */
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ResourceBean resourceBean;

	@Override
	public Result<String> uploadImage(MultipartFile mf, String imageTypeName) {
		// 判断file是否为空
		if (mf.isEmpty()) {
			return Result.failed("图片为空，请选择图片。");
		}

		// 判断file后缀是否是图片
		if (!FileUtil.isImage(mf)) {
			return Result.failed("请上传图片。");
		}

		// 获取图片类型
		ImageType it = ImageType.getImageTypeByName(imageTypeName);
		if (it == null) {
			return Result.failed("图片类型错误。");
		}

		// TODO 判断图片的尺寸

		// 获得目标文件夹，如果不存在则创建
		// "D:/static/" + "profile";
		String os = System.getProperty("os.name");
		String destFolder = String.format("%s%s",
				os.toLowerCase().startsWith("win") ? resourceBean.getWindowLocalPath() :
						resourceBean.getLinuxLocalPath(),
				it.name
				);
		File distFolderFile = new File(destFolder);
		if (!distFolderFile.exists()) {
			distFolderFile.mkdirs();
		}

		// 获得目标文件绝对路径，根据系统来构建
		// "D:/static/" + "profile/" + "QQ截图20230215143519.jpg"
		String fileName = String.format("%s.%s",
				System.currentTimeMillis(),
				FileUtil.getFileType(mf.getOriginalFilename()));
		String destFile = String.format("%s/%s", destFolder, fileName);
		File df = new File(destFile);

		// 获得图片的访问路劲
		// "/static/" + "profile/" + "QQ截图20230215143519.jpg"
		String relatedPath = String.format("%s%s/%s",
				resourceBean.getPath(),
				it.name,
				fileName);

		// 写文件
		try {
			mf.transferTo(df);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 返回结果 result，其data属性为图片的访问地址
		return Result.ok(relatedPath);
	}
}
