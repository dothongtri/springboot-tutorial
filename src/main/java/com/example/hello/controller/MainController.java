package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hello.dto.CreateEmployeeDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@Autowired
	private MessageSource messageSource;

	public static List<CreateEmployeeDTO> employees = new ArrayList<>(Arrays.asList(
			new CreateEmployeeDTO("John Doe", 30),
			new CreateEmployeeDTO("Jane Smith", 25),
			new CreateEmployeeDTO("Mike Johnson", 35)));

	@GetMapping(value = "/")
	public String home(HttpServletRequest request) {

		request.setAttribute("msg", messageSource.getMessage("my.greeting", null, null));
		return "index";
	}

	@GetMapping(value = "/employees")
	public String hello(HttpServletRequest request, Model model) {
		request.setAttribute("employees", employees);
		model.addAttribute("employee", new CreateEmployeeDTO("demo", 20));
		return "employee";
	}

	@PostMapping(value = "/employee1")
	public String addEmployee(HttpServletRequest request, @ModelAttribute("employee1") CreateEmployeeDTO employee1, BindingResult result) {
		employees.add(employee1);
		return "redirect:/employees";
	}
	
}
