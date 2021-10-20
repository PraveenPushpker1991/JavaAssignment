package com.testyantra.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testyantra.entity.CategoryAttributes;

import com.testyantra.service.ICategoryAttributesService;

@RestController
@RequestMapping("/attributebyid")
public class AttributeByIdController {

	@Autowired
	private ICategoryAttributesService service;

	/**
	 * 1. Read PathVariable id (as input use service layer to find one object based
	 * on Id. Return CategoryAttribute if exist else String (error message) as
	 * ResponseEntity<?>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getAttribute(@PathVariable Integer id) {
		ResponseEntity<?> resp = null;
		try {
			Optional<CategoryAttributes> opt = service.getAttributes(id);
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
}
