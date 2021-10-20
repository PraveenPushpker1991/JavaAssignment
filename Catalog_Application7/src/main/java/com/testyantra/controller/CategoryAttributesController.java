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

import com.testyantra.entity.CategoryAttributes;
import com.testyantra.service.ICategoryAttributesService;

@RestController
@RequestMapping("/attributes")
public class CategoryAttributesController {

	@Autowired
	private ICategoryAttributesService service;

	/**
	 * 1. This method takes CategoryAttribute object as input from JSON using
	 * 
	 * @RequestBody and returns ResponseEntity<T>. call service.createAttributes(ob)
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createCategoryAttributes(@RequestBody List<CategoryAttributes> categoryAttributes) {
		ResponseEntity<String> resp = null;
		try {
			List<CategoryAttributes> id = service.createCategoryAttributes(categoryAttributes);
			resp = new ResponseEntity<String>(id + "-saved", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Save", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}

	/***
	 * 2. This method reads data from DB using findAll() and returns List<Attributes>
	 * if data exist or String (not exist) as ResponseEntity using annotation
	 * 
	 * @ResponseBody
	 */
	@GetMapping("/allattributes")
	public ResponseEntity<?> getAllCategoryAttributes() {
		ResponseEntity<?> resp = null;
		try {
			List<CategoryAttributes> list = service.getAllCategoryAttributes();
			if (list != null && !list.isEmpty())
				resp = new ResponseEntity<List<CategoryAttributes>>(list, HttpStatus.OK);
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
	 * on Id. Return CategoryAttributes if exist else String (error message) as
	 * ResponseEntity<?>
	 */
	@GetMapping("/attribute/{id}")
	public ResponseEntity<?> getOneCategoryAttribute(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Optional<CategoryAttributes> opt = service.getOneCategoryAttributes(id);
			if (opt.isPresent())
				resp = new ResponseEntity<CategoryAttributes>(opt.get(), HttpStatus.OK);
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
	public ResponseEntity<String> deleteCategoryAttributes(@PathVariable Integer id) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist(id);
			if (exist) {
				service.deleteCategoryAttributes(id);
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
	public ResponseEntity<String> updateCategoryAttributes(@RequestBody CategoryAttributes categoryAttributes) {
		ResponseEntity<String> resp = null;
		try {
			boolean exist = service.isExist((categoryAttributes).getAttributeId());
			if (exist) {
				service.updateCategoryAttributes(categoryAttributes);
				resp = new ResponseEntity<String>((categoryAttributes).getAttributeId() + "-Updated", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>((categoryAttributes).getAttributeId() + "-Not Exist",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Unable to Update", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return resp;
	}
}
