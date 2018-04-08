package com.sherl.tmall.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHelper {

	/**
	 * 上传单张图片
	 * 
	 * @param request
	 * @param path
	 *            文件保存的路径名
	 * @param filename
	 *            文件保存的文件名
	 * @param multipartFile
	 * @return 如果保存成功返回true,否者返回false
	 */
	public static boolean uploadImage(HttpServletRequest request, String path, String filename,
			MultipartFile multipartFile) {
		if (multipartFile == null) {
			System.out.println("未接受到上传数据！");
			return false;
		}
		if (!multipartFile.getOriginalFilename().endsWith(".jpg")) {
			System.out.println(multipartFile.getOriginalFilename() + ": 上传文件不是jpg格式");
			return false;
		}
		String floderPath = request.getServletContext().getRealPath(path);
		File floder = new File(floderPath);
		if (!floder.exists())
			floder.mkdir();
		File image = new File(floder, filename + ".jpg");
		try {
			multipartFile.transferTo(image);
		} catch (Exception e) {
			System.out.println("文件保存错误！");
			return false;
		}
		return true;
	}
}
