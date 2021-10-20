package com.testyantra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testyantra.entity.Product;
import com.testyantra.service.IProductService;

@RestController
@RequestMapping("/productbyid")
public class ProductByIdController {

	@Autowired
	private IProductService service;

	
	/**
	 * 3. Read PathVariable id (as input use service layer to find one object based
	 * on Id. Return Products Based On Id if exist else String (error message) as
	 * ResponseEntity<?>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getOneCategory(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Optional<Product> opt = service.getOneProduct(id);
			if (opt.isPresent())
				resp = new ResponseEntity<Product>(opt.get(), HttpStatus.OK);
			else
				resp = new ResponseEntity<String>("No Data Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
}
