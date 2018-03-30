package com.example.todoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {
	@GetMapping
	public String home() {
		return "index";
	}
	@GetMapping(value="/edit")
	public String edit() {
		return "edit";
	}
	
	

}
