package com.sfac.hk.common.controller;

import com.sfac.hk.common.vo.ImageType;
import com.sfac.hk.common.vo.Result;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description DictionaryController
 * @Author JiangHu
 * @Date 2023/2/15 11:16
 */
@RestController
@RequestMapping("/api/common")
public class DictionaryController {

	public static Map<String, Class> map = new HashMap<>() {{
		put("imageTypes", ImageType.class);
		put("resultCodes", Result.ResultCode.class);
	}};
//	static {
//		map.put("imageTypes", ImageType.class);
//		map.put("resultCodes", Result.ResultCode.class);
//	}

	/**
	 * 127.0.0.1/api/common//dictionary/imageTypes ---- get
	 */
	@GetMapping(value = "/dictionary/{key}")
	public List<Object> getDictionary(@PathVariable String key) {
		Class clazz = map.get(key);
		if (clazz != null) {
			try {
				Method method = clazz.getDeclaredMethod("values");
				Object[] objects = (Object[]) method.invoke(null);
				return Arrays.asList(objects);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
