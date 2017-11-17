package com.project.awardParser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.awardParser.image.ImageFormatter;
import com.project.awardParser.model.Award;
import com.project.awardParser.model.AwardRepository;
import com.project.awardParser.model.CompletedImage;

@Controller 
@RequestMapping(path="/awards") 
public class AwardsController {

	@Autowired
	private AwardRepository awardRepo;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Award> getAllAwards() {
		System.out.println("Get All Awards");
		return awardRepo.findAll();
	}
	
	@PostMapping(path="/submitSelectedAwards")
	public @ResponseBody CompletedImage submitSelectedAwards(@RequestBody List<Award> selectedAwards) {
		System.out.println("submitSelectedAwards" + selectedAwards.get(0));
//		System.out.println(selectedAwards..getAwardName());
		CompletedImage ci = new CompletedImage();
		ci.setText("Completed Image");
		ci.setImage(ImageFormatter.createCombinedImage(selectedAwards));
		return ci;
	}
	
	
//	@GetMapping(path = "/idAndName") {
//
//		
//	}
}
