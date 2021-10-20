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
import com.testyantra.service.ICategoryAttributesService;
import com.testyantra.service.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private ICategoryService service;
	@Autowired
	private ICategoryAttributesService ser;


	/**
	 * 1. This method takes Category object as input from JSON using
	 * 
	 * @RequestBody and returns ResponseEntity<T>. call service.createCategory(ob)
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createCategory(@RequestBody Category category) {
		ResponseEntity<String> resp = null;
		try {
			Category id = service.createCategory(category);
			resp = new ResponseEntity<String>(id + "-saved", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Save", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/***
	 * 2. This method reads data from DB using findAll() and returns List<Category>
	 * if data exist or String (not exist) as ResponseEntity using annotation
	 * 
	 * @ResponseBody
	 */
	@GetMapping("/allcategory")
	public ResponseEntity<?> getAllCategory() {
		ResponseEntity<?> resp = null;
		try {
			List<Category> list = service.getAllCategory();
			if (list != null && !list.isEmpty())
				resp = new ResponseEntity<List<Category>>(list, HttpStatus.OK);
			else
				resp = new ResponseEntity<String>("No Data Found", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 3. Read PathVariable id (as input use service layer to find one object based
	 * on Id. Return Category if exist else String (error message) as
	 * ResponseEntity<?>
	 */
	@GetMapping("/category/{id}")
	public ResponseEntity<?> getOneCategory(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Optional<Category> opt = service.getOneCategory(id);
			if (opt.isPresent())
				resp = new ResponseEntity<Category>(opt.get(), HttpStatus.OK);
			else
				resp = new ResponseEntity<String>("No Data Found", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Fetch Data", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * 4. Read pathVariable id check row exist or not if exist call service delete
	 * else return String error msg
	 */
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist(id);
			if (exist) {
				service.deleteCategory(id);
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
	 * 5. Read Input as JSON using
	 * 
	 * @RequestBody , check id exist or not if exist call service save method Return
	 *              ResponseEntity
	 */
	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist(category.getCategoryId());
			if (exist) {
				service.createCategory(category);
				resp = new ResponseEntity<String>(category.getCategoryId() + "-Updated", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(category.getCategoryId() + "-Not Exist", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Update", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

}
