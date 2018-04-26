package com.sherl.tmall.web.productImage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sherl.tmall.entity.ProductImage;
import com.sherl.tmall.service.ProductImageService;
import com.sherl.tmall.service.ProductService;
import com.sherl.tmall.util.FileUploadHelper;

@Controller
public class ProductImageController {

	@Autowired
	private ProductImageService imageService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/admin/{cid}/products/{pid}/image")
	public String list() {
		return "admin/productImage";
	}

	@RequestMapping(value = "/admin/{cid}/products/{pid}/singles", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductImage> listSingles(@PathVariable int pid) {
		return imageService.getProductSingleImage(pid);
	}

	@RequestMapping(value = "/admin/{cid}/products/{pid}/details", method = RequestMethod.GET)
	@ResponseBody
	public List<ProductImage> lsitDetails(@PathVariable int pid) {
		return imageService.getProductDetailsImage(pid);
	}

	// @RequestMapping(value = "/admin/{cid}/products/{pid}/details", method =
	// RequestMethod.POST)
	// @ResponseBody
	// public void addDetails(HttpServletRequest request, @RequestParam("image")
	// MultipartFile[] images,
	// @PathVariable int pid) {
	// for (MultipartFile image : images) {
	// ProductImage pi = new ProductImage();
	// pi.setProduct(productService.get(pid));
	// imageService.addDetailsImage(pi);
	// String path = "/resources/image/product/" + pid;
	// if (!FileUploadHelper.uploadImage(request, path, pi.getId() + "", image)) {
	// imageService.delete(pi.getId());
	// }
	// }
	// }

	@RequestMapping(value = "/admin/{cid}/products/{pid}/{type}", method = RequestMethod.POST)
	@ResponseBody
	public void addSingles(HttpServletRequest request, @RequestParam("image") MultipartFile[] images,
			@PathVariable int pid, @PathVariable String type) {
		for (MultipartFile image : images) {
			ProductImage pi = new ProductImage();
			pi.setProduct(productService.get(pid));
			if (type.equals("singles")) {
				imageService.addSingleImage(pi);
			} else {
				imageService.addDetailsImage(pi);
			}
			String path = "/resources/image/product/" + pid;
			if (!FileUploadHelper.uploadImage(request, path, pi.getId() + "", image)) {
				imageService.delete(pi.getId());
			}
		}
	}

	@RequestMapping(value = "/admin/{cid}/products/{pid}/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable int pid, @PathVariable int id) {
		String dir = "/resources/image/product/" + pid;
		try {
			Files.delete(Paths.get(dir, id + ".jpg"));
		} catch (NoSuchFileException e1) {
			imageService.delete(id);
			return;
		} catch (IOException e) {
			return;
		}
		imageService.delete(id);
	}
}
