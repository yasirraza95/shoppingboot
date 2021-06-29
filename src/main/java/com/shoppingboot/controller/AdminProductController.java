package com.shoppingboot.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.shoppingboot.model.Product;
import com.shoppingboot.service.ProductHibernateService;
import com.shoppingboot.service.ProductServiceImpl;

@Controller 
public class AdminProductController {
	
//	@Autowired
//	ProductServiceImpl productService;
	
	@Autowired
	ProductHibernateService productHService;

	@RequestMapping("/admin/addproduct")
	public ModelAndView viewProduct() {
        ModelAndView model = new ModelAndView("admin/addProduct");
        model.addObject("product", new Product());
        return model;
    }
	
	
	@RequestMapping(value="/admin/addproduct", method=RequestMethod.POST)
	public ModelAndView addProduct(@RequestParam("name") String name, @RequestParam("topProduct") String topProduct,
			@RequestParam("priceType") String priceType, @RequestParam("price") int price,
			@RequestParam("productType") String productType, 
			@RequestParam("image") MultipartFile file,HttpServletRequest req) {

		String fileName = file.getOriginalFilename();
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setTopProduct(topProduct);
		product.setPriceType(priceType);
		product.setProductType(productType);
		product.setImageName(fileName);
		
//		productService.insert(product);
		productHService.insert(product);
		

//        String path = req.getSession().getServletContext().getRealPath("resources/user/assets/images/"+fileName);
		String path = "F:/eclipse-workspace/ecommerce/src/main/webapp/resources/user/assets/images/"+fileName;
		
        System.out.println("path:"+path);
        File dir = new File(path,fileName);
        
        if(!dir.exists()) {
            dir.mkdirs();
        }
        
        try {
            file.transferTo(dir);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
        ModelAndView model = new ModelAndView("admin/addProduct");
        
        model.addObject("product", new Product());        
        return model;
    }
	
	@RequestMapping("/admin/editproduct")
	public ModelAndView editProduct(@RequestParam("id") int id) {
		ModelAndView model = new ModelAndView("admin/editProduct");
//		model.addObject("product", productService.getSingleProduct(id));
		model.addObject("product", productHService.getSingleProduct(id));
		return model;
	}
	
	@RequestMapping("/admin/adminproducts")
	public ModelAndView adminProducts(@RequestParam("type") String type) {
		ModelAndView model = new ModelAndView("admin/products");
//		model.addObject("products", productService.getProducts(type));
		model.addObject("product", productHService.getProducts(type));
		return model;
	
}
}