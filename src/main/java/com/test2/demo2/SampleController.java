package com.test2.demo2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	private static int counter =0;
	
	@GetMapping("/bye")
	public String byeWorld() {
		return "Bye World :"+ counter++; 
	}
}
