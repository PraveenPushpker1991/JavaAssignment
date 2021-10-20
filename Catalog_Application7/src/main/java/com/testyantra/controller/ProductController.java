package com.testyantra.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testyantra.entity.Category;
import com.testyantra.entity.Product;
import com.testyantra.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService service;

	
	/**
	 * 1. This method takes Category object as input from JSON using
	 * 
	 * @RequestBody and returns ResponseEntity<T>. call service.createProduct(ob)
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		ResponseEntity<String> resp = null;
		try {
			Integer id = service.createProduct(product);
			resp = new ResponseEntity<String>(id + "-saved", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Save", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/***
	 * 2. This method reads data from DB using findAll() and returns List<Product>
	 * if data exist or String (not exist) as ResponseEntity using annotation
	 * 
	 * @ResponseBody
	 */
	@GetMapping("/allproducts")
	public ResponseEntity<?> getAllProduct() {
		ResponseEntity<?> resp = null;
		try {
			List<Product> list = service.getAllProduct();
			if (list != null && !list.isEmpty())
				resp = new ResponseEntity<List<Product>>(list, HttpStatus.OK);
			else
				resp = new ResponseEntity<String>("No Data Found", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 3. Read pathVariable id check row exist or not if exist call service delete
	 * else return String error msg
	 */
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist(id);
			if (exist) {
				service.deleteProduct(id);
				resp = new ResponseEntity<String>(id + "-removed", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(id + "-Not Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Delete", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 4. Read Input as JSON using
	 * 
	 * @RequestBody , check id exist or not if exist call service save method Return
	 *              ResponseEntity
	 */
	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist(product.getProductId());
			if (exist) {
				service.createProduct(product);
				resp = new ResponseEntity<String>(product.getProductId() + "-Updated", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(product.getProductId() + "-Not Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Update", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

}
